# Kafka 学习 | 如何合理配置Kafka



1、处于方便统一管理，要将自动创建 Topic 关闭

```
// server.properties
auto.create.topics.enable=false
```



2、未避免丢失消息，请将允许 OSR 参与选举的配置关闭

```
// server.properties
unclean.leader.election.enable=false
```



3、从性能方面考虑，没有必要定期重新选举 leader ，要将这一参数关闭

```
// server.properties
auto.leader.rebalance.enable=false
```



4、Topic 的 partitions 起到负载均衡的作用，但并不是分区越多越好，实际上更多的分区会需要更新的文件句柄，导致系统可用性降低（broker 故障恢复更慢），带来更高的消息延迟，因此选择合适的分区很重要，通常选择分区数量的时候需要考虑：

- 生产者的吞吐量
- 消费者的吞吐量
- Broker 的已有分区个数（通常单个 Broker 的分区数量应该保持在 2000 以内）
- 系统磁盘空间

可以通过生产者吞吐量/消费者吞吐量，大致估算所需分区大小

```
// server.properties
num.partitions=生产者吞吐量/消费者吞吐量
```



5、虽然Kafka 集群高可用，但如果未设置好 Topic 的副本参数，也会导致 Topic 不可用，在创建 Topic 可以指定副本数量`replication.factor`，同时需要在配置中修改默认的副本数量参数为常用的数量，一般设置为 3

```
// server.properties
default.replication.factor=3  // 该参数默认值是1
```



6、系统默认配置`acks=all`，一般也推荐配置`acks=all`，设置后 ISR 中所有的 follow 确认后，才认为消息已提交，是消息不丢失的主要保证

```
// producer.properties
acks=all
```



7、第6点设置`acks=all`后，也不能保证消息不丢失，当 ISR 中仅剩 leader ，就会与`acks=1`类似，当 leader 宕机，就可能消息丢失；因此，需要和 `min.insync.replicas` 配合，该参数设置必须要求`acks=all`，当消息发送时，如果系统中 ISR 数量小于`min.insync.replicas`配置的值，就会报错。通常该参数设置要比副本数量少

```
// server.properties
min.insync.replicas=replication.factor-1
```



> 未完待续



### 参考链接

[Kafka源码分析（七）消息发送可靠性——min.insync.replicas](https://www.jianshu.com/p/1de494673b89)

[如何为Kafka集群选择合适的Partitions数量](https://www.cnblogs.com/fanguangdexiaoyuer/p/6066820.html)

[Kafka的Topic的partitions数目设置最佳实践](https://www.jianshu.com/p/8c07039437d0)

















