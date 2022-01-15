# PHP的错误和异常处理



## 基础

### 错误级别

常见的错误级别是 E_NOTICE，E_WARNING，E_ERROR，E_ALL ，E_PARSE，这几个级别通常控制的是用户代码导致的错误；

用户也可以通过 trigger_error 函数主动产生一个错误信息，对应的错误级别是 E_USER_NOTICE, E_USER_WARING, E_USER_ERROR、E_USER_DEPRECATED；

E_CORE_ERROR、E_CORE_WARNING、E_COMPILE_WARNING、E_COMPILE_ERROR 通常还用PHP引擎内核导致的错误；

E_DEPRECATED 是个特殊的错误，当使用在未来版本可能被移除的函数时报出的错误。



### 错误捕获

 PHP7之前错误捕获有多个手段，使用`try ... catch`捕获异常，使用`set_error_handle`捕获错误信息，使用`set_exction_handle`捕获未在catch中的异常，使用`register_shutdown_function`和`error_get_last`拦截到前面都未捕获到的错误信息。

这里简单解释一下，错误是如何一级级被拦截而不直接展示到用户界面的。

对于set_error_handle，该方法不能捕获所有错误，其中 E_ERROR、E_PARSE、E_CORE_*、E_COMPLETE__\*不会被捕获



## PHP7以前











## PHP7

