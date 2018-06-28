<?php

require './src/init.php';

if (!isset($_SESSION['user_login'])) {

    echo '<a href="index.php">用户未登录，点击连接返回首页</a>';
    exit();
}

unset($_SESSION['user_login']);

echo '<a href="index.php">用户已注销，点击连接返回首页</a>';
