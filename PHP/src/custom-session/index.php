<?php

require './src/init.php';

echo 'hello world' , '<hr>';


if (isset($_SESSION['user_login']) && $_SESSION['user_login'] === 1) {
    echo '<p><a href="./logout.php">注销</a></p>';
} else {
    echo '<p><a href="./login.php">登录</a></p>';
}
