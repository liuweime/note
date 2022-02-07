# Kafka 学习 | 如何保证消息不丢失

Kafka 不能无限度的保证消息不丢失，在以下两个前提保证下，Kafka 能够保证不丢失消息

- 1、生产者的消息是已提交的消息，即消息被写入日志告知生产者已成功提交
- 2、至少一个 Broker 活着

以下是常见的消息丢失场景

### 生产者使用了异步不带回调通知的 API

如果使用生产者 API 调用的是 `producer.send(msg)`，该 API 在调用后会立即返回，但这时不一定消息发送成功，当出现网络抖动或消息大小超过 Broker 限制时，此时 Kafka 不认为消息时已提交的，因此不保证消息不丢失；

建议使用`producer.send(msg, callback)`，该 API 通过回调告知生产者消息是否发送成功，在 Kafka 0.11.0.0 版本后提供了消息发送幂等传递，可以在回调告知发送失败后进行有限度的重试发送。

### 消费者端保存的消费者位移异常

同样消费者消费不当也会导致消息丢失（当然这种丢失不是永久性的，因为消息已经持久化在 Broker 了）。

我们知道消费者消费后，消费位移会保存，如果位移保存与实际消费位置不同，就会导致消息丢失。

如果读取到消息未消费完毕，就提交位移，就可能导致位移与消费位置不一致，通常选择先消费再更新位移（如果消费时移除导致未提交位移，会带来重新消费问题）

上面说的是手动提交位移，如果开启了`enable.auto.commit=true`，那么 Kafka 会根据`auto.commit.interval.ms`配置的时间间隔定时提交消费者位移，如果拉取到消息，位移自动提交，但消费失败，就会导致该消息消费丢失，通常建议关闭自动提交，使用手动提交位移的方式。

### 启用了unclean leader election

如果`unclean.leader.election.enable=true`，就会允许消息较少的副本也可以参与 leader 选举，如果该副本被选为 leader，就会导致消息的丢失。

### 如何保证消息不丢失

- 不使用`producer.send(msg)`API
- 设置`acks=all`：所有 leader 和 follwer 均确认，才认为是已提交
- 设置`retries=Integer.MAX_VALUE`：自动重试，指定返回确认
- `unclean.leader.election.enable=false`：不允许消息较少的副本参与 leader 选举
- 创建Topic时，指定`replication.factor>=3`，设置 Topic 有多个副本
- 设置`min.insync.replicas=replication.factor-1`，该参数在`acks=all`时起效，至少写入多少副本才会任务是已提交
- 设置`enable.auto.commit=false`确认消费完成，才提交消费位移







