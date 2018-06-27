# Swoole学习｜WebSocket的简单使用

`swoole`是一个php拓展，是一个高性能的异步网络通信引擎．使用`swoole`能做什么？做一个TCP/UDP服务器，作为Web服务器，Websocket服务器，做一个异步任务队列，它的毫秒级定时器可以用来取代crontab完成定时任务．

通过官方的文档，可以快速开发一个简单的网页聊天室．这里稍微对官方例子做一下简单拓展，实现一个web聊天室，这个聊天室希望有的功能是：

- 登录/注册
- 公共聊天室
- 支持建立房间
- 支持建立群组
- 支持私聊
- 支持表情发送
- 支持聊天记录保存
- 更多功能可能不再添加......


官网给的案例很简单，实例化一个websocket服务器，添加监控回调函数，启动服务器，几行代码启动了一个异步非阻塞多进程的WebSocket服务．当然，官网给出的代码不能完成上面的功能．

**准备工作**

**环境配置**

- php7及以上
- swoole
- mysql
- redis

**依赖**

- predis/predis
- illuminate/database
- fzaninotto/faker

**开发工具**

- phpstorm


**数据库设计**

要支持登录注册，聊天记录就需要将聊天记录持久化，就需要使用到数据库．

- user
- chat-record
- chat-room

### 0.0 利用redis实现session共享

聊天室分为客户端和服务端，如果需要使用到用户系统，那么传统的session机制就无法起作用了，因为服务端运行在CLI中．
所以，使得session能够共享．这里采用redis来完成共享．

查找资料，有三种方式可以改变传统机制，让redis存储session：

- 修改php.ini
- 使用ini_set函数
- 使用`session_set_save_handler`

修改php.ini会影响到全局，不是非常好，这里采用第三种方式．

也顺便给出第二种方式：
```php
ini_set("session.save_handler", "redis");
ini_set("session.save_path", "tcp://127.0.0.1:6379");
```

第三种方式需要实现`SessionHandlerInterface`接口，并使用`session_set_save_handler`进行注册．这里注意的是，使用这种方式http访问，PHP仍会将`session_id`存入`cookie`中，所以此时`cli`方式仍不能正常使用session，我们需要做一下改动，将`session_id`存入`redis`中．


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

定义session处理类`CustomSessionHandle`，其中`read`,`write`等方法不直接取传参来的session_id，而是从session中获取
```php
$sessionId = $redis->get($sessionKey);
if (empty($sessionId)) {
    $sessionId = $option['prefix'] . $session_id;
}
```

注意在session_start之前注册session处理类：
```
$handler = new CustomSessionHandle($option);
session_set_save_handler($handler, true);
```

### 1.0 登录/注册

比较简单，省略（懒癌发作）

### 1.1 建立一个公共聊天室

- 数据库记录一个聊天室，类型是公共
- 用户连接后直接进入公共聊天室

### 1.2 创建一个房间

### 1.3 拉人进入群组

### 1.4 如何进行私聊

### 1.5 总结
