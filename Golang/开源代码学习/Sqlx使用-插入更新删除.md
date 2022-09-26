# Sqlx 使用-插入更新删除



sqlx 提供了多个函数用于执行插入更新删除，分别是：`Exec`、`ExecContext`、`MustExec`、`NamedExec`，还可以使用`Prepare`后，用返回的`Stmt.Exec`

### ExecContext

ExecContext 是基础函数，Exec、MustExec、NamedExec 均基于该函数。该函数需要传入 context，当特殊需求时，可以使用该函数，例如通过context传递traceid进行日志采集

```go
sql := "insert into test(name, age) value (?, ?)"
r, e := db.ExecContent(ctx, sql, "ming", 21)
if e != nil {
  return 0, e
}
id, e := r.LastInsertId
if e != nil {
  return 0, e
}
return id, e
```


### Exec

Exec 就是对 ExecContext 的封装，传递了一个空的上下文环境，使用的时候只需要传递sql和参数即可。

### MustExec

MustExec 是对 Exec 的封装，判断 Exec 返回，如果执行有异常，直接panic

### NamedExec

NamedExec 底层也是执行 Exec，在执行之前对参数做了一个绑定

```go
m := map[string]interface{"name": "ming", "age": 15}
r, e := db.NamedExec("insert into test(name, age) value (:name, :age)", m)
// ...
```

### Prepare、Stmt.Exec

使用 Prepare 预处理SQL语句，并利用返回的`*Stmt`调用`Exec`执行SQL。通常推荐仅预处理一次并执行N次SQL时使用。使用该种方式会产生n+1次tcp请求(n是sql执行次数，Prepare执行会产生一次tcp)

因为Prepare产生了连接，所以需要显示关闭，否则该连接无法被释放

```go
sql := "insert into test(name, age) value (?, ?)"
st, err := db.Prepare(sql)
if err != nil {
  return err
}
// 注意处理完成后关闭预处理连接
defer st.Close()
r, err := st.Exec("ming", 21)
// ...
```

### 如何批量插入

批量插入是很普遍的需求，





> 参考链接
>
> [Golang Mysql笔记（三）--- Prepared剖析 - 简书 (jianshu.com)](https://www.jianshu.com/p/ee0d2e7bef54)
