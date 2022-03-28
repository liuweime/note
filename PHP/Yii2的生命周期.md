# Yii2的生命周期



web/index.php

加载composer autoload

yii 的autoload

加载配置文件

DI容器

application 运行



application 构造函数中

- 设置生命状态

- 配置默认核心组件

- 注册错误检测程序

- 通过configure加载配置文件

- Servicelocator __set __get 方法通过DI容器加载 component 

- init 引导注册web bootstrap 组件

  

  

run 中：

- 解析url地址，参数，隐射路由信息，实例化控制器（）
- 返回处理信息