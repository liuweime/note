# 分布式系统中SESSION如何进行共享


session即会话，在计算机网络访问中起到重要作用，通常使用session保存用户登录及配置信息，通过获取session状态来判断用户是否登录．

一般的，一个会话产生，除了保存的session数据，还会生成session_id，且将session_id存储在cookie中．服务端凭借读取cookie中的session_id来获取session数据．

因此，诞生了**禁用cookie后session还能用吗**这种经典的面试题，当然这种问题对于了解session机制的同学来说简直手到擒来．那么，这个问题问得深入一点，**分布式情景下session如何进行共享？**对于这个问题，有一定经验的同学表示也不难，例如利用`redis`,`memcache`甚至持久到数据库中．

下面，就来说一下如何利用`redis`进行session共享．当然，无论`redis`,`memcache`还是数据库，方式都基本一致．解决两个问题

- session_id存储
- session数据存储

基本的思路就是，
- 利用`redis`存储`session_id`，不自动获取服务端本身产生的`session_id`，除非`redis`中失效．
- 获取`redis`中的session_id，数据同样存储到`redis`

PHP中提供了三种方式可以修改session存储方式：

- 直接修改`php.ini`文件，修改`save_handler=redis`，`save_path=redis连接地址`；
- 使用`ini_set`函数，同样修改上面两个配置；
- 实现`SessionHandlerInterface`接口，并使用`session_set_save_handler`进行注册．

不推荐直接修改php配置文件达到目的，所以这里推荐第三种方法．

方法三只是使PHP保存session数据的方式由文件变为redis，session_id仍会存储到cookie中，甚至在cli模式下，每次访问都会产生新的session_id导致上次存储的session变为脏数据．所以，还需要实现session_id存储到redis．整个实现方式如下：

> 实现`SessionHandlerInterface`接口，并使用进行注册
> session处理类`CustomSessionHandle`中`read`,`write`等方法不直接取传参来的session_id，而是从session中获取
```php
// CustomSessionHandle 中
$sessionId = $redis->get($sessionKey);
if (empty($sessionId)) {
    $sessionId = $option['prefix'] . $session_id;
}
```

```
CustomSessionHandle implements SessionHandlerInterface 
{
	// ....
}
$handler = new CustomSessionHandle($option);
session_set_save_handler($handler, true);
```

> 
`session_start`后获取`session_id`存入redis中，设定超时时间：
```php
session_start();
$sessionKey = 'custom:session_id';
$sessionId = $redis->get($sessionKey);
if (empty($sessionId)) {
	$sessionId = $option['prefix'] . session_id();
	$redis->setex($sessionKey, 24*60*60, $sessionId);
}
```

附上源代码：
[custom-session](https://github.com/liuweime/mynote/tree/master/PHP/src/custom-session)
