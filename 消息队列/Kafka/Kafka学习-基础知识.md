# Kafka学习 | 基础知识

### kafka 的一些基本概念

#### Broker

每一个Kafka节点都是一个Broker，多个Broker节点组成一个Kafka集群

#### Topic

消息的对外地址，无论是生产者，还是消费者，对接的就是Topic。每个消息的发布或订阅都需要指定一个Topic；逻辑上，可以认为它就是一个消息队列中queue。

#### Partition

相对于Topic是逻辑概念，Partition就是物理概念，消息的实际存储放在Partition中。每个Partition都是一个有序队列，消息会被append到Partition中。每个Topic都可以设置多个Partition，消息来了：

- 指定了Partition，将消息发送到指定的Partition中
- 消息的Key不为空，基于Key的hash值来选择Partition
- 消息的Key是空的，也未指定Partition，那么轮询的方式选择一个Partition

上面说到，Topic是逻辑概念，因此一个Topic下的Partition可以分布到不同的Broker中，根据分区分配算法进行分配。

#### Segment

每个 Partition 实际上是由多个 Segment 组成，就相当于一个文件被平均分割成多个文件段，每个文件中的消息数据量不一致，但一定是有序的。每个 Segment 通常由索引和数据文件组成，一一对应，用`.index`和`.log`后缀。每个文件的命名从 0 开始，下一个文件的文件名是上一个文件的最后一个消息的offset值。

#### Offset

每个消息在 Topic 中都是有序且不可被修改的，每个消息都有编号，这个编号就是 offset，用于唯一标识该消息。消息在 Kafka 中被消费是不会被从队列中移除的，Broker 也不会标记哪些消息被消费了（这也是Kafka高性能的原因之一），消费者就需要通过 offset 来判断消费位置，保证不会漏、重复消费；因此这个消费的 offset 需要保存下来，通常由消费者控制（Customer offset），把这个 offset 记录到一个 Topic （__consumer_offsets）中。消费者也可以将该 offset 设置一个较小值，重复消费一些信息。

#### Customer Group

多个消费者实例共同组成一个组来消费订阅Topic下的所有分区，而每个分区只能被消费者中的一个消费者消费。一个消费者组中有多个消费者，同时消费一个Topic可以大大加快消费端的吞吐量。

而其中的消费者可以是一个线程，也可以是一个进程。

如果只有一个 Customer Group，订阅所有分区，那么就是常说的点对点消息队列模型（每个分区仅能被一个消费者消费）

如果有多个 Customer Group，订阅所有分区，那么就是发布订单模型（分区可以被其他 Group 中的消费者消费）

由于每个分区仅可以被一个组内的 Customer 消费，因此在设置消费者实例时通常要不大于总分区数

#### Rebalance

Kafka会检测消费者组中挂掉的消费者实例，并将其负责的分区分配给其他消费者，这就是重平衡。

#### 消息模型

Kafka 中存在两种消息模型，点对点模型和发布-订阅模型。

**点对点消费模型**：一个生产者产生的消息只能被一个消费者消费

**发布-订阅消息模型**：与点对点不同，发布-订阅模式生产者可以有多个，消费者也可以有多个，产生的消息放入消息容器中，需要消费的消费者订阅该容器即可。

#### AR、ISR、OSR

在一些分享Kafka知识的文章中总是会看到 AR、ISR 等英文缩写词，了解这些缩写词真实含义才能更好的汲取到营养。

- AR，assigned repllicas 的缩写，Kafka 中分区中的所有副本统称为 AR
- ISR，in sync repllicas 的缩写，我们知道消息总会先发送给 leader，之后 follow 才会从 leader 中拉取消息，这个拉取是异步，通常会有一定时间的延迟，同步延迟在允许的范围内的副本就被称为 ISR
- OSR，out sync repllicas 的缩写，如果出现延迟超过允许范围的副本，那么就将这些副本称为 OSR

AR = ISR + OSR，正常情况下，OSR 应该等于 0












































