<?php

define('ROOT_PATH', dirname(__DIR__));
define('DS', DIRECTORY_SEPARATOR);
define('PWD', true);



require ROOT_PATH . DS . 'src' . DS . 'CustomRedis.php';
require ROOT_PATH . DS . 'src' . DS .  'CustomSessionHandle.php';
$option = require_once ROOT_PATH . DS  . 'src' . DS .  'config.php';


$handler = new CustomSessionHandle($option['session']);
session_set_save_handler($handler, true);
session_start();

$redis = CustomRedis::connect();
$sessionId = $redis->get($option['session']['session_id_key']);
if (empty($sessionId)) {
    $sessionId = $option['session']['prefix'] . session_id();
    $redis->setEx($option['session']['session_id_key'], 24*60*60, $sessionId);
}
