<?php

defined('PWD') || exit('Forbid');

class CustomRedis
{

    public static $redis;

    public static function connect()
    {
        if (false === (self::$redis instanceof Redis)) {

            $config = require ROOT_PATH . DS . 'src' . DS . 'config.php';
            self::$redis = new \Redis();
            self::$redis->connect($config['redis']['host'], $config['redis']['port']);
        }

        return self::$redis;
    }

}
