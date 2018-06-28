# 一个简单的session共享demo

分布式环境下，使用**redis**进行session共享的一个简单实现，未进行大量测试，这里仅作学习使用．

依赖：
- php7
- redis

### 结构介绍

```
- custom-session         
  - src
    - config.php 　　　　　　　　　　　　　配置文件
    - CustomRedis.php 　　　　　　　　　　Redis操作类
    - CustomSessionHandle.php 　　　　　自定义session处理类
    - init.php                        初始化文件
  - index.php                         入口文件
  - login.php                         登录(模拟)
  - logout.php                        注销(模拟)
```
