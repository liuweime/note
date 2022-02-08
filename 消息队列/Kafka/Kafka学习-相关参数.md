# Kafka 学习 | 相关参数



### log.dirs | 数据目录

> log.dirs=/usr/local/var/lib/kafka-logs

参数配置文件名：`server.properties`

log.dirs 是Kafka的数据目录，可以`,`分割的形式设置多个目录。

这样设置后，日志文件可以存储在不同硬盘中，可以提升读写性能，并且可以进行故障转移。

而日志在不同磁盘写入时会判断磁盘中日志文件大小，选择较小的写入。


### zookeeper.connect | zookeeper连接

> zookeeper.connect=host:port

参数配置文件名：`server.properties`

zookeeper.connect 配置的是连接 zookeeper 的地址，同样可以使用`,`分割配置多个地址

### listeners | 监听器

> listeners=协议://host:port
> eg: 
> listeners=PLAINTEXT://lw.test.me:9092

参数配置文件名：`server.properties`

用于指定使用什么协议、主机、端口访问kafka服务，如例子中写的，`plaintext`意思是明文传输，你也可以使用`SSL`来加密传输，也可以自定义协议名字，kafka默认支持`plaintext、ssl、sasl_plaintext、sasl_ssl` ，如果使用自定义协议，必须在`listener.security.protocol.map`中指定。

另外，主机名不建议使用IP配置，使用IP配置可能会出现不可连接的问题。

### auto.create.topics.enable | 是否允许自动创建Topic

默认值是True，如果在`server.properties`中配置了该参数，且设置为`true`，当生产者向一个未创建的Topic发送消息时，就会自动创建一个`num.partitions=1`的Topic。建议设置为false

### unclean.leader.election.enable | 是否开启 unclean leader 选举

kafka中分区有多个副本可用，在多个副本中选出一个leader对外提供服务，如果leader挂了，就在其他副本中重新选举；而参与选举的副本必须是保存数据较多的。

如果将该参数设置为 true，那么数据较少的副本也可以参与选举。这样做的后果就是可能会出现数据丢失，所以一般将该参数设置为false。

### auto.leader.rebalance.enable | 是否允许定期重新leader选举

如果启用该参数，即使当前的leader可用，仍可以在一定时候重新选举leader。切换leader一般是0收益的，因此建议设置为false。

### log.retention.{hour|minutes|ms} | 消息数据保存时长

这个参数就是控制一个消息保存多长时间。可以同时设置hour、minutes、ms，优先级 ms > minutes > hour。如果需要持久存储数据，就需要将时间设置的足够长。

### log.retention.bytes | 消息存储磁盘容量大小

这个参数默认设置为-1，那么就可以存储数据直到达到磁盘最大容量。

### message.max.bytes | 一条消息的最大数据大小

默认值是1048588，可以调整大一些，对性能没有什么影响。

### enable.auto.comment | 是否启动自动提交位移

> enable.auto.comment=true

配置文件名`producer.properties`

是否启用自动提交(消费)位移，启用后根据`auto.commit.interval.ms`配置的时间间隔定时提交消费者位移。

### auto.commit.interval.ms | 自动提交位移时间间隔

> auto.commit.interval.ms=5000

配置文件名`producer.properties`

启用`enable.auto.comment=true`后，该参数起作用，按照配置的时间间隔提交位移

### acks 

> acks=all

配置文件名`producer.properties`

该参数用于判断消息是否已提交，有三种值可以设置，0、1、all

- 如果设置为`all`，那么必须所有 broker 均确认后，才认为消息已提交
- 设置为0，不需要任何 Broker 确认，消息一发送就认为提交，不建议使用该参数，无法保证消息真实落地
- 设置为1，那么仅需要 leader 确认，就认为消息已提交



### enable.idempotence | 是否启用幂等性生产者

> enable.idempotence=true

配置文件名`producer.properties`

该参数用于配置是否启用幂等 Producer，启用后生产者发送的消息具有幂等性，重复发送 Kafka 会自动去重。

这种幂等只针对于单个分区












