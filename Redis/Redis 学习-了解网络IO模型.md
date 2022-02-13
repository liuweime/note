# Redis 学习 | 了解网络IO模型

网络IO模型分成5中

- 阻塞型IO，即BIO
- 非阻塞性IO，即NIO
- 多路复用IO，常见的epoll
- 信号型IO
- 异步IO

### 网络IO连接过程

- 通过socket方法获取套接字（打开了一个网络连接端口）
- 绑定和监听这个套接字
- 调用accept方法并返回已连接上的套接字（将网卡中的信息copy到内核缓冲区）
- 调用recv方法读取连接的套接字中信息（将信息从内核缓冲区copy到用户缓冲区）
- 处理

由上过程，在accept和recv时会产生阻塞，根据对其处理的不同，就区分了阻塞/非阻塞、异步/同步

### 阻塞性IO

accept会产生阻塞本质就是等待数据copy到内核缓冲区完成，这期间如果数据不全或缓冲区满都会导致线程阻塞

同样将内核缓存数据copy到用户缓存区也存在阻塞。

因此，BIO在一次IO中仅能处理一个连接请求

### 非阻塞型IO

针对上面等待copy完成产生的阻塞，如果不用一直等待，而是copy完成时再来继续操作，就可以解决阻塞问题了。如何不等待而能知道copy完成呢，NIO采用的方法就是轮询，如果copy未完成，会稍等再次调用，直到返回，在每次轮询期间可以去处理其他IO请求。

该种方式虽然解决了阻塞问题，但显然会在本次IO和其他IO请求上下切换，导致CPU资源浪费。

### IO 多路复用

IO 多路复用提供了多种实现，包括select、poll、epoll

#### select



#### poll



#### epoll



### 参考链接

[深入理解 Linux 的 epoll 机制及 epoll 原理](https://xie.infoq.cn/article/5e7ae820ac641b9ff86df4789)

[从网络IO到IO多路复用](https://juejin.cn/post/6880527933778165774)

[阻塞IO和非阻塞IO](https://www.cnblogs.com/aeolian/p/15151037.html)

[阻塞io和非阻塞io在发生相应的系统调用的时候，操作系统到底发生了什么，让应用程序有不一样的反应？](https://www.zhihu.com/question/382972191/answer/1113293711)