<?php

require './src/init.php';

if (!isset($_SESSION['user_login'])) {
    $_SESSION['user_login'] = 1;
}
echo '<a href="index.php">用户已登录，点击连接返回首页</a>';
