# Kafka 学习 | 分区策略

无论生产者还是消费者，均需要处理消息分配的问题，选择合适的分区分配策略是非常重要的。

### 生产者的分区分配策略

Kafka 的分区分配策略在`producer.properties`中的`partitioner.class`配置，目前版本`3.1.0`支持三种策略和自定义策略

**org.apache.kafka.clients.producer.internals.DefaultPartitioner**

默认的分区分配策略 ，它的分配规则是：

- 如果指定了分区，发送到指定的分区中
- 如果未指定分区，但指定了 key ，会根据 key 的 hash 值选择一个分区（key.hashCode() % 分区数）
- 如果未指定分区，也未指定 key ，那么会随机选择一个分区，并且在这个分区的 batch 满之前或 batch 的发送间隔时间到之前一直选择该分区（这种分配方式被称为黏性分区）

**org.apache.kafka.clients.producer.RoundRobinPartitioner**

轮询分区策略，启用该种分区策略后，消息会每次发送给不同分区，直到分区用尽后重新开始轮询；该种策略下指定 key 是无效的。

**org.apache.kafka.clients.producer.UniformStickyPartitioner**

该种分区策略在选择一个分区后，在 batch 满或 batch 发送间隔到之前会一直选择该分区。

同样，你可以继承并实现`org.apache.kafka.clients.producer.Partitioner`接口来编写自己的分区分配策略。

### 消费者的分区分配策略

消费者组中有多个消费者，消费者组订阅 Topic 中也有多个分区，消费者从哪个分区中消费也涉及到分区分配问题。

在配置文件`customer.properties`中的`partition.assignment.strategy`配置分配策略，支持四种分区分配策略，RangeAssignor、RoundRobinAssignor、StickyAssignor、CooperativeStickyAssignor：

**RangeAssignor**

RangeAssignor 分配策略基于每个 Topic ，所以该种分配方式是不均匀分配。分配规则如下

对于每一个 Topic 

- 1、将 Topic 下分区和订阅该分区的消费者按照字典顺序排列
- 2、设 Topic 下分区数量为 P，消费者数量 C，由公式 P/C 得到结果 R，由公式 P%C 的结果 M
- 3、轮询中每个消费者至少分配 R 个分区，前 M 个消费者至多再分配一个分区

举一个例子，假设 T1、T2、T3 分别有3、3、2个分区，被 C1、C2 消费者订阅

```text
-> T1
R = P/C = 3/2 = 1  // C1、C2 至少获取一个分区
M = P%C = 3%2 = 1	 // 前1个分区再获取一个分区，即 C1 再获取一个分区
-> T2 与 T1 类似
-> T3
R = 2/2 = 1		// C1、C2 至少获取一个分区
M = 2%2 = 0		// 前0分区再获取一个分区
最终得到的分配结果
C1 -> T1P0 T1P1 T2P0 T2P1 T3P0
C1 -> T1P2 T2P2 T3P1
```

**RoundRobinAssignor**

RoundRobinAssignor 就是轮询算法，该算法首先将所有Topic下分区和订阅的消费者按照hash值排序，然后轮询分配给订阅的消费者，如下，假设 T1、T2 分表有两个分区被 C1、C2 两个消费者订阅

```text
C1 -> T1P0 T2P0
C2 -> T1P1 T2P1
```

如果T1、T2、T3下有1、2、3个分区，T1 被 C1、C2、C3 订阅，T2 被 C1、C2 订阅，T3 被 C3 订阅，那么

```text
C1 -> T1P0
C2 -> T2P0 
C3 -> T2P1 T3P0 T3P1 T3P2
```

显然，如果订阅的 Topic 不同，会导致分配不均匀

**StickyAssignor**

StickyAssignor 是黏性分配策略，前两种分配策略都存在分配不均匀的情况，同时在发生重平衡时会导致上次分配的结果被重新分配给了其他消费者，造成资源的浪费。StickyAssignor 就是需要保证分配均匀同时尽量保证现有的分配结果。

**CooperativeStickyAssignor**

无论使用上面哪种分配策略，发生重平衡时都需要停止消费实例，等待平衡完成，如果重平衡时间较长，带来的消费延迟是十分严重的。最新加入的 **CooperativeStickyAssignor** 策略能保证消费进行中进行重平衡。

在需要平衡时，各个消费者将自身信息发送组长，由组长决定如何哪些分区需要更改所有权，那些需要移动分区的消费者会被暂停执行分配，其他消费者继续工作

与生产者分区分配一样，你可以继承并实现`AbstractPartitionAssignor`接口来自定义分配策略



### 参考链接

[Kafka配置](https://kafka.apache.org/documentation/#configuration)

[kafka -5个开发者需要知道的使用技巧](https://www.cnblogs.com/foolaround/articles/14676070.html)

[Kafka消费者分区分配策略详解](https://www.modb.pro/db/174527)

[Kafka消费者分区分配策略](https://lilinchao.com/archives/1542.html)















