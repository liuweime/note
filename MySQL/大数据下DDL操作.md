---
flag: green
---
# 大数据量表线上DDL操作方法总结

公司有DBA，不需要我实际操作，写这篇日志权做学习之用吧。

首先，需要知道的是 DDL 操作代价高昂，数据量十几万乃至几十万可能看不到多大的影响，当数据量上升到千万的数据，无论是添加索引还是新加字段，都会造成 DML 操作堵塞，极其影响用户体验。

原因是 DDL 操作会使 MySQL 对表进行加锁（X 锁），导致表只读。加锁是为了保证数据一致性，下面列一下直接DDL操作时，MySQL 做了哪些工作：

> 1、原表添加排它锁；
> 2、生成临时表，该表结构为 DDL 操作后的结构；
> 3、原表中的数据同步到临时表中；
> 4、同步完成，对临时表加排它锁，删除原表；
> 5、临时表重命名，并释放锁。

由此可看，随着数据量增长，DDL 操作越慢，DML 操作堵塞时间越长。

如果去除复制表的过程，速度不就显著上升了吗。确实，在 MySQL 5.5 以后有了一个新的特性，叫做`Fast Index Creation`。描述就是，删除/添加二级索引，不会复制表。当然这种特性仅用于修改索引上，而且修改主键索引，仍会有复制表操作，详情请看 [InnoDB Fast Index Creation](https://dev.mysql.com/doc/refman/5.5/en/innodb-create-index.html)。

`Fast Index Creation` 大大加快了修改索引的速度，但是也只对索引有效，所以，在 MySQL5.6 中对该特性进行了扩充，叫做 `Online DDL`，可以对添加列、删除列、修改列类型、列重命名、设置默认值等 DDL 操作有作用。

根据官网描述，添加/删除索引、设置默认值、修改列等操作不会复制表，添加/删除列、设置列属性NULL
或NOT NULL、添加主键等操作仍会复制表，但是可以在 DDL 时声明`ALGORITHM=INPLACE`提升性能。更为详细的操作情况 [Online DDL Operations](https://dev.mysql.com/doc/refman/5.6/en/innodb-online-ddl-operations.html)。

已上是不做其他任何工具或脚本情况下，利用 MySQL 本身特性进行 DDL 操作。事实上，没有`Online DDL`DBA通常采用下面方法来对大数据表进行DDL操作。

1、临时表+触发器

因为，MySQL 复制表之前为保证数据一致性，会将表进行锁住，导致无法DML操作，从而影响服务正常使用。所以可以手动生成临时表，进行数据复制，不加排它锁，因此不影响服务使用，而其中产生的数据一致性问题可以使用触发器来完成，流程如下：

> 1、生成临时表，该表结构为 DDL 操作后的结构;
> 2、在原表中创建触发器3个触发器分别对应insert,update,delete操作；
> 3、复制数据到临时表（分块复制到临时表）；
> 4、重命名临时表
> 5、删除原表

将上述过程整理为一个可重复使用的工具，那就是 DBA 更为常用的DDL工具了，不同公司可能会使用各自的工具，这里介绍一个工具`pt-online-schema-change`，也是要说的方法二。

2、pt-online-schema-change

这是 percona 公司推出的一个针对 MySQL 在线 DDL 的一个工具，大体工作流程和方法一差不多，[# pt-online-schema-change使用说明、限制与比较](http://seanlook.com/2016/05/27/mysql-pt-online-schema-change/) 有更为详细的介绍，我也是看这篇文章知道这个工具的。

3、主从库滚动式更新

这也是一部分公司使用的方法。停止某一台主服务器，进行更新，其他主服务器提供支持，在对从服务器更新，依次进行，直到所有服务器更新完毕。这种滚动更新的弊端就是慢，且只能在低流量访问的时候进行，不能影响到用户访问。

我第一份工作所在的公司就是使用这种方法，记得刚刚去公司报道，技术部只有前端在，后端人员加了一夜班，为了滚动更新时服务出现问题第一时间可以修复。

以上就是我了解的线上DDL操作的方法，事实上我从未从事过DBA相关工作，纸上谈兵罢了。



**2019-06-28 日更新**

上面说的是 MySQL 5.* 版本下的 DDL 操作，在 MySQL 8 添加了一个新的特性叫做`instant add column`，从英文意思也能知道在特性是什么。该特性可以在**不重建表**的情况下**快速**添加列。更详细的内容见 [8.0 online ddl operations](https://dev.mysql.com/doc/refman/8.0/en/innodb-online-ddl-operations.html)

该特性据说可以亿级数据1s添加新列，而且是由国内大厂鹅厂提交的（应该是鹅厂 TMySQL 的特性）。

那么，采用了什么方法提升了如此高的速度呢？该特性将 `instant add column` 前后做了一定的区分：

- 加列时将记录原列数量、新列定义、新列默认值，但是**不修改原表结构**；
- 查询时，除了返回旧数据的内容外还是自动加入新的列的默认值，追加在原列数据之后，即伪造了一个新列；
- 插入时，给新插入数据加入instant 标志位和列数信息，根据这两点 InnoDB 可以判断是新数据还是旧数据，便于返回结果；
- 更新时，如果是更新原数据列，与原理更新保持一致，如果更新是新加列，会先删除原列在作为新数据插入

由上描述可以看出该新特性依赖于8.*版本新的数据字典，该特性添加列也不是毫无成本（修改数据字典需要加锁）。

同时由于是"伪列"的原因，添加新列不支持指定列的位置，仅能在列尾添加，还需要注意：

- 不支持压缩表
- 不支持全文索引的表（FULLTEXT）
- 不支持临时表
- 使用 instant 的表不兼容旧版本 MySQL
- 所加列必须非自增

使用方式是正常的 DDL 语句后添加`ALGORITHM=INSTANT`, 如下：

```sql
ALTER TABLE `test` ADD COLUMN c INT, ADD COLUMN d INT, ALGORITHM=INSTANT;
```

以上均查询自网络文章、官网文档，请见参考文档

## 参考文档

[MySQL在线加字段实现原理](<https://got.qq.com/webplat/info/news_version3/8616/8622/8625/8628/m7025/201407/271174.shtml>)
[在？上次你问对大型表的DDL操作，我找到好方法了](https://dbaplus.cn/news-11-2552-1.html)
[MySQL 为表添加列 是怎么”立刻”完成的](https://opensource.actionsky.com/20190620-mysql-add-column/)
[MySQL 8.0: InnoDB now supports Instant ADD COLUMN](https://mysqlserverteam.com/mysql-8-0-innodb-now-supports-instant-add-column/)
[Online DDL Operations](https://dev.mysql.com/doc/refman/8.0/en/innodb-online-ddl-operations.html)
[MySQL8.0 - 新特性 - Instant Add Column](https://yq.aliyun.com/articles/670691)


