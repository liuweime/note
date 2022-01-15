# Hash



* HSET

  > HSET key field value

  设置hash值，field 不存在则设置，返回1；否则，覆盖原值，返回0；

* HSETNX

  > HSETNX key field value

  与 `HSET`不同的是，如果 field 已经存在，不会覆盖原值。

* HGET

  > HGET key field

  获取指定 field 的值，如果 key 或 field 不存在，返回 nil，

* HEXISTS

  > HEXISTS key field

  判断 hash 中 field 是否存在，存在返回 1，否则返回0

* HDEL

  > HDEL key field ..   [支持多个]

  从 hash 中删除 field，不存在的 field 被忽略，返回被成功删除 field 的数量

* HLEN

  > HLEN key

  返回 hash 中 field 数量，key 不存在时返回 0

* HSTRLEN

  > HSTRLEN key field

  获取 hash 中 field 值的字符串长度，key 或 field 不存在返回0，如：

  ```shell
  127.0.0.1:6379> hset test one "hello"
  (integer) 1
  127.0.0.1:6379> hstrlen test one
  (integer) 5
  ```

* HINCRBY

  > HINCRBY key field increment

  对应 hash 的 field 值进行增\减（看增量是正数还是负数）；incement 必须是一个整型；仅能对数值型的值进行增减，如果 field 的值是字符串，会报错；不存在的 key 或 field ，其值会被先初始化为0；值的最大值应保持在64位有符号数字内；返回的是操作后的值

* HINCRBYFLOAT

  > HINCRBYFLOAT key field increment

  与 `HINCRBY`类似，不同的是允许传递一个浮点型数值

* HMSET

  > HMSET key field1 value1 field2 value2 ...

  支持对多个 filed 设置值，成功返回OK

* HMGET

  > HMGET key field1 field2 ...

  支持获取对多个 field 的值

* HKEYS

  > HKEYS key

  获取 hash 的所有键值

* HVALS

  > KVALS key

  获取 hash 所有域值

* HGETALL 

  > HGETALL key

  获取 hash 所有键值

  

## Hash 原理

redis 中的 hash 与 java中 hashmap 结构类似，redis 的 hash 会随着数据量的变化，使用不同的编码，当数据量比较小的时候，其使用的是 ziplist，当数据量增大时会由 ziplist 变成 hashtable 结构。

Ziplist 转为 hashtable 的条件是:

- Hash 中有 field 的值超过64字节
- Hash 中 field 的数量超过512个



