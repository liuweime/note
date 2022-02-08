# Kafka 学习 | 幂等和事务

### 幂等型生产者

设置`retries`后，自动重试消息发送会导致消息重复问题，在某些场景中是绝对不能允许的，此时需要启用 Kafka 幂等生产者发送消息，开启的方式是`enable.idempotence=true`

**这种幂等是如何实现的？**

启用幂等后，生产者在初始化时会被分配一个 ProducerId，该 ID 唯一且对客户端来说不可见；

同时对于 Topic 的每个 Partition 均维护一个 SequenceNumber，该值从 0 开始递增，在发送数据时生产者会将 ProducerId 和 SequenceNumber 附加在消息上，发送成功会对 SequenceNumber 递增；

发送失败仍附带未递增的 SequenceNumber，当 Broker 发现相同的字段值后，就可以知道消息重复了，就会将重复的消息丢弃。

由上幂等实现原理可知，该幂等仅对单分区有效，如果消息发送到多分区，幂等就会失效；同样由于初始化时会分配新的 ProducerId，因此多会话和进程重启均会导致幂等失效。

基于以上，Kafka 提供了事务。

### 事务型生产者

Kafka 的事务与数据库中的事务类似，保证了多次发送消息的原子性；启用事务需要配置两个参数：

- `enable.idempotence=true`
- 设置 Producer 参数 `transctional. id`，该 ID 要具有唯一性

设置 transctional. id 后，一个 transctional. id 会对应一个 ProducerId，为保证事务的隔离性，当持有同一个 transctional. id 的 Producer 开始工作时，以前同样持有的 Producer 会停止工作。隔离实现的原理大致如下：

- 设置 transctional. id 并初始化 Producer 后，会把 transctional. id 与 ProducerId 绑定，并返回一个 Broker 维护的 epoch 标识，该标志单调递增
- 当事务中有重复恢复的 Producer 加入，会根据  transctional. id 返回其绑定的 Producer 和 最新的 epoch 标识
- Kafka 会拒绝持有 epoch 小于 Broker 保存 epoch 的 Producer

对于消费者来说事务不能保证消息被原子消费，另外同样需要设置消费者的 `isolation.level=read_committed`来保证只取到成功提交事务的消息。



### 参考

[Kafka设计解析（八）Exactly Once语义与事务机制原理](http://www.jasongj.com/kafka/transaction/)

[Kafka幂等性原理及实现剖析](https://www.cnblogs.com/smartloli/p/11922639.html)

[大厂面试Kafka，一定会问到的幂等性](https://www.cnblogs.com/jingangtx/p/11330338.html)

[Kafka设计解析（三）恰好一次和事务消息](https://www.jianshu.com/p/f77ade3f41fd)

[Kafka(Go)教程(十)---Kafka 是如何实现精确一次（exactly once）语义的？](https://www.lixueduan.com/post/kafka/10-exactly-once-impl/)