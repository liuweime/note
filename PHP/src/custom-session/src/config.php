<?php

return [
    /**
     * redis配置
     */
     'redis' => [
         'host' => '127.0.0.1',
         'port' => 6379
     ],

    /**
     * session配置
     */
     'session' => [
         'max_lifetime' => 24 * 60 * 60,
         'prefix' => 'liu:',
         'session_id_key' => 'custom:session_id'
     ],
];
