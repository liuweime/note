# MVCC 的快照读原理

## Undo Log 简单介绍

事务涉及到数据改动就会记录 **undo log**，它和 **redo log** 保证了事务的原子性和持久性。**undo log** 记录的是修改前数据，MVCC 通过读取 **undo log** 版本链来保证多个事务之间数据的隔离。

**undo log** 被回滚段管理，共有 128 个回滚段，0 号被系统保留，1~32 个回滚段分配给临时表使用，33~128 被正常表使用。每个回滚段可以分配 1024 个 slot，每个slot 可以存储一个 **undo log**，所以通常事务并发允许最大为 **(128-32)*1024** 个

**undo log** 分成两种插入和更新，插入生成的 **undo log** 在事务提交后会被删除，删除操作会被添加删除标记，被记做更新 **undo log**。

与数据表记录类似，**undo log** 也存在 **db_roll_ptr** 和 **db_trx_id**，**db_trx_id** 保存的是数据改动并事务提交的事务ID，**db_roll_ptr** 是一个链表，指向的是上一个版本的 **undo log**，组成了一个版本链。

## Read View 简单介绍

**read view** 是 MVCC 实现的关键之一，RR 级别下事务开启后的第一个读操作会创建 read view ，其中包括了当前最新活跃事务ID **low_limit_id**、当前最久活跃事务ID **up_limit_id**、活跃状态的读写事务数组 **trx_ids**。

## MVCC 数据可见性判断

通过对比列中的 **db_trx_id** 和 read view 的 **low_limit_id**、**up_limit_id** 来判断数据对当前事务的可见性。

- db_trx_id < up_limit_id：说明了列记录的事务ID已经不是活跃事务了，所以该列数据对当前事务可见；
- db_trx_id >= low_limit_id：说明列记录的事务ID对于当前事务来说属于活跃事务(=low_limit_id: A 事务在 B事务未提交时开启；> low_limit_id：A 事务开启后 B 事务开启并与A事务之前提交)，此时该列数据对当前事务不可见；
- up_limit_id <= db_trx_id < low_limit_id：此时需要判断该事务ID是否在活跃读写事务数组中，如果在那么该列数据对当前事务不可见(A、B、C 在 D 事务前开启，且 B 事务在 D 开启前提交了，那么 D 的read view 列中的db_trx_id 就处于up、low之前)。

所有判断不可见的列数据会对根据链表 **db_roll_ptr** 读取旧版本的数据，直到获取到可见的数据。

## 参考

[InnoDB的read view，回滚段和purge过程简介]([http://mysql.taobao.org/monthly/2018/03/01/](http://mysql.taobao.org/monthly/2018/03/01/))
[InnoDB多版本(MVCC)实现简要分析](http://hedengcheng.com/?p=148#_Toc322691905)

