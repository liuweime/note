# AMQP API



## GO-AMQP

#### Dial

连接方法，接受一个符合规则的amqp连接地址。例：

>
> amqp://user:password@host:port/vhost

返回值除了error，返回连接对象

```
conn, err := amqp.Dial("amqp://user:password@host:port/vhost")
```



#### DialTLS

还可以使用`DialTLS`连接rabbitmq服务器，连接将使用SSL/TLS。使用`DialTLS`，接受的amqp连接地址必须是`amqps://scheme`



