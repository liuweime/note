---
create: 2020-04-14 晚
update: 2020-04-14 晚
tags: MySQL 复习 笔记
---

# MySQL 复习 5 | DML 操作



## 一、INSERT

### 1.1 语法

> INSERT INTO table_name [(column_name...)] VALUE|VALUES (value...) [ON DUPLICATE KEY UPDATE arguments]

```sql
-- 标准语法
INSERT INFO `test` (`name`, `age`) VALUE ("ming", 26);
-- 非标准语法 省略了字段名，该方式必须保证值和表中字段一一匹配
INSERT INFO `test` VALUE (1, "ming", 26, "男");
-- 非标准语法
INSERT INFO `test` (`name`, `age`) VALUES ("ming", 26),("hong", 26),("dong", 26);
```

values 后跟多个数据是属于 MySQL 特有的语法，在其他 DB 中没有该语法，因此虽然该方式性能很高，但通常不推荐在代码中使用。

设置唯一索引后，出现重复值后，如果希望更新该条重复而不是执行失败的话，可以使用 ON DUPLICATE KEY UPDATE，如：

```mysql
INSERT INFO `test` (`name`, `age`) VALUE ("ming", 26) ON DUPLICATE KEY UPDATE age = 30;

UPDATE `test` SET age = 30 WHERE name = "ming"
```

上面两个语句的效果相似，不同的是 INSERT 会导致被自增列自动递增。与之功能类似的是 REPLACE 语句，该语句在遇到已存在的重复数据时，会删除原语句而插入新的数据，因此会得到新的主键值。

### 1.2 释疑

#### INSERT 的执行过程

没看源码，先看两篇源码分析的文章

[一条insert语句的执行过程](http://mysql.taobao.org/monthly/2017/09/10/)

[一条insert语句的执行过程](https://blog.csdn.net/bohu83/article/details/82903976)



#### INSERT INFO ... VALUES 性能较高的原理

简单来说，

- 一条SQL语句减少网络IO耗时
- 一条SQL执行，SQL 分析操作减少、多次SQL的事务变成了一次，binlog减少，降低了对磁盘的读写频率

[MySQL insert性能优化](https://my.oschina.net/u/2300159/blog/703433)

