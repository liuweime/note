# Sqlx 使用-连接数据库

### 连接方式

Sqlx 提供了多种连接数据库的方式，`Open`方法是基础连接方式，另外还有`MustOpen`、`Connect`、`MustConnect`，下面分别介绍一下它们的作用。

#### **Open**

`Open`调用的是`sql.Open`，返回的是`sqlx.DB`实例，该函数不会实际与数据库建立连接，而是将连接加入连接池中，它会在首次需要连接时才与数据库建立连接；如需要验证是否连接，可以调用`Ping`函数进行验证。

#### **MustOpen**

`MustOpen`实际上也是调用`Open`函数，不同的是加了对错误的判断，当产生错误时直接panic。

#### **Connect**

`Connect`是对`Open`和`Ping`函数的封装，它会在调用`Open`成功后，调用`Ping`验证是否连接成功，如果失败则调用`Close`方法将连接从连接池移除。

#### **MustConnect**

`MustConnect`与`MustOpen`类似，当`Connect`失败，直接panic。

基于以上，通常建立与数据库连接可以直接使用`Connect`或`MustConnect`，如下：

```golang
var DB *sqlx.DB
dsn := fmt.Sprintf("%s:%s@tcp(%s:%d)/%s?charset=utf8&multiStatements=true", "user", "password", "localhost", 3306, "test")
DB, err := sqlx.MustConnect("mysql", dsn)
```

### 连接参数设置

sqlx 还提供了一些函数用于接配置连接数等参数，下面是比较关键的连接池参数：

#### **SetMaxIdleConns**

该函数用于设置连接池最大空闲连接数，连接池默认空闲连接数是2，允许更多的空闲连接有助于提供性能，节省了重新建立连接的开销。

当然保持多大的空闲连接取决于实际应用，空闲连接本身会占用内存，如果你设置了一个较大的空闲连接数，而实际连接没有用到这么多，会导致大量空闲连接达到超时而不能使用，浪费了内存。

另外，空闲连接数设置主要不要超过最大连接数。最大空闲连接设置注意不要设置过小，也不要与最大连接数相差过大，可能会导致复用连接不足，而大量创建新连接，连接使用完毕后由于无法放回空闲连接池而关闭连接，频繁连接与关闭导致tcp 连接累积大量time_wait，time_wait 过多可能会导致服务器端口用尽，而影响其他服务连接。

#### **SetMaxOpenConns**

该函数用于设置连接池最大连接数，通常建议使用：CPU 核数 * 2 + 有效磁盘数

最大连接数与空闲连接数决定了是复用连接，还是新建连接，还是等待重试：

- 当工作连接 < 最大连接数
  - 当前空闲连接 < maxIdleConnCount，直接复用连接
  - 当前空闲连接 >= maxIdleConnCount，新增一条连接
- 工作 >= 最大连接数，连接达到上线，需要等待重试

#### **SetConnMaxLifetime**

该函数用于设置连接池连接最大可用时间，该项设置与数据库连接超时时间有关，必须比数据库超时时间要小，如果设置大于数据库超时时间，会出现数据库已关闭连接，而go中连接还未关闭，导致连接异常的情况。

#### **SetConnMaxIdleTime**

该函数用于设置连接池连接最大空闲时间，空闲连接空闲时间超过该设置，就会从连接池移除，即使没到最大连接数限制。

综上，一般连接数据库可以如下：

```golang
var DB *sqlx.DB
dsn := fmt.Sprintf("%s:%s@tcp(%s:%d)/%s?charset=utf8&multiStatements=true", "user", "password", "localhost", 3306, "test")
DB, err := sqlx.MustConnect("mysql", dsn)
DB.SetMaxOpenConns(64)
DB.SetMaxIdleConns(8)
DB.SetConnMaxLifetime(time.Hour)
```

> 参考链接
>
> [Golang 侧数据库连接池原理和参数调优_每一个不曾起舞的日子，都是对人生的辜负。-程序员宝宝 - 程序员宝宝 (cxybb.com)](https://www.cxybb.com/article/qq_39384184/103954821)
>
> [如何配置 sql.DB 的 SetMaxOpenConns SetMaxIdleConns 和 SetConnMaxLifetime | Go优质外文翻译 | Go 技术论坛 (learnku.com)](https://learnku.com/go/t/49809)
>
> [golang 连接池产生大量异常 TIME_WAIT 连接排查 - 知乎 (zhihu.com)](https://zhuanlan.zhihu.com/p/449777718)
>
> [Golang SQL连接池梳理 - 赐我白日梦 - 博客园 (cnblogs.com)](https://www.cnblogs.com/ZhuChangwu/p/13412853.html)
