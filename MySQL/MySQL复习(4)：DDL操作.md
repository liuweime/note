---
create: 2020-04-13 晚
update: 2020-04-13 晚
tags: MySQL 复习 笔记
---

# MySQL 复习 4 | DDL 操作



## 一、 CREATE 

### 1.1 语法

**数据库创建**

> CREATE DATABASE [IF NOT EXISTS] \`database_name\` [CHARACTER SET charset_name COLLATE collation_name]
>
> eg: CREATE DATABASE \`t_blog\`;

中括号中的可以不填写，其中 character 和 collate 不填写，MySQL 会自动应用 db.opt 中定义的默认值（字符集默认值是 latin1，排序规则默认值是 latin1_swedish_ci）。

MySQL 中的字符集和排序规则设置较为复杂，这里暂不记录，会另开一篇。



**数据表创建**

> CREATE TABLE [IF NOT EXISTS] \`table_name\` (
>
> ​	col_name column_definition
>
> ) table_options

column_definition 包括：

- 字符类型：
  - 整型：tinyint、smallint、mediumint、int、bigint
  - 浮点型：float、double
  - 定点型：decimal
  - 字符串：char、varchar、tinytext、mediumtext、text、langtext、set、enum
  - 日期类型：date、time、datetime、timestamp、year
  - 二进制类型：bit、binary、varbinary、tinyblob、blob、mediumblob、langblob
- 是否允许 NULL：NOT NULL
- 字段默认值: 字段的默认值除了 datetime 和 timestamp 以外只能是字面量，不能是函数或表达式，datetime 和 timestamp 可以指定 CURRENT_TIMESTAMP 作为默认值，以下是各类型常用的默认值：
  - 整型：0
  - 字符串：empty string
  - 浮点类型：0.0
  - 定点类型：0.00
  - 日期类型：
- 字符集设置
- 排序规则设置
- 是否自动递增
- 

table_options 常见的包括表引擎、字符集设置、排序规则设置、注释。

**索引创建**



## 二、DROP





## 三、DROP



## 四、RENAME



## 五、TRUNCATE



