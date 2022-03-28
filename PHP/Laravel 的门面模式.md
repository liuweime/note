# Laravel 的门面模式





Laravel 中有个非常好用的功能，或者说特性，被翻译成门面。例如，我们使用缓存，直接调用`Cache::get($key)`就可以调用缓存的 get 方法了。那么它是如何实现的呢？

首先，我们新增一个门面，通过新增的流程来理解门面的工作方式。

第一步，创建一个应用类，例如 PostManager 用于帖子管理；

第二步，给这个应用类添加一个门面类

```
class Post extends Facade 
{
    protected static function getFacadeAccessor()
    {
        return 'post';
    }
}
```

第三步，添加一个 Post 服务提供者，在register方法中，注册应用类到服务容器中

```
public function register() 
{
  $this->app->singleton('post', function () {
  	return new PostManager();
  });
}
```

第四步，将服务提供者添加到配置注册、将门面添加到别名数组中

好了，现在你可以用`Post::first()`获取第一个帖子了。

原理

1、Facade 类中有PHP的魔术方法 __callstatic ，当静态调用不存在的方法时，会进入该魔术方法

2、魔术方法中会从服务容器中取得 `getFacadeAccessor`返回的应用类

因此，getFacadeAccessor 方法中返回的是注册服务容器中的名字