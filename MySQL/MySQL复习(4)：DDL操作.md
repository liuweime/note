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
- 是否允许 NULL：NOT NULL ;text 类型不支持设置 not null
- 字段默认值: 字段的默认值除了 datetime 和 timestamp 以外只能是字面量，不能是函数或表达式，datetime 和 timestamp 可以指定 CURRENT_TIMESTAMP 作为默认值；如果设置 not null 后，未设置默认值，严格模式下，未指定字段值得 SQL 会报错，非严格模式下，会赋予隐式默认值，以下是各类型的隐式默认值：
  - 整型：0，如果设置了自增属性，默认值是自增后的值
  - 字符串：empty string；enum 默认值是第一个枚举值
  - 浮点类型：0
  - 定点类型：0
  - 日期类型：datetime 和 timestamp 使用CURRENT_TIMESTAMP 
  - 二进制类型：0; BLOB不支持默认值设置
- 字符集设置
- 排序规则设置
- 是否自动递增

table_options 常见的包括表引擎、字符集设置、排序规则设置、注释。

下面是一个包含了常用类型的数据表 SQL：

```sql
CREATE TABLE `test` (
	`id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
	`test_float` FLOAT(10, 2) NOT NULL DEFAULT 0.00 COMMENT 'float',
	`test_double` DOUBLE(10, 2) NOT NULL DEFAULT 0.00 COMMENT 'double',
	`test_decimal` DECIMAL(10, 2) NOT NULL DEFAULT '0.00' COMMENT 'decimal',
	`test_char` CHAR(10) NOT NULL DEFAULT '' COMMENT 'char',
	`test_varchar` VARCHAR(10) NOT NULL DEFAULT '' COMMENT 'varchar',
	`test_text` TEXT COMMENT 'text',
	`test_enum` ENUM("one", "two", "three") NOT NULL DEFAULT "one"  COMMENT 'enum',
	`test_set` SET("one", "two", "three") NOT NULL DEFAULT "one"  COMMENT 'set',
	`test_date` DATE NOT NULL DEFAULT '2020-04-14' COMMENT 'date',
	`test_time` TIME NOT NULL DEFAULT '18:20:10' COMMENT 'time',
	`test_datetime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'datetime',
	`test_timestamp` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'timestamp',
	`test_year` YEAR NOT NULL DEFAULT '2020' COMMENT 'year',
	`test_bit` BIT(8) NOT NULL DEFAULT 0 COMMENT 'bit',
	PRIMARY KEY (`id`)
) ENGINE INNODB CHARSET utf8mb4 COLLATE utf8mb4_general_ci COMMENT 'test';
```



**索引创建**



## 二、DROP





## 三、DROP



## 四、RENAME



## 五、TRUNCATE



