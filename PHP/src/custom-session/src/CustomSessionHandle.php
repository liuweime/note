<?php

defined('PWD') || exit('Forbid');

class CustomSessionHandle implements \SessionHandlerInterface
{
    private $option = [];

    private $handler;

    public function __construct(array $option)
    {
        // 检测是否设置了session失效时间
        if (empty($option['max_lifetime'])) {
            $option['max_lifetime'] = ini_get('session.gc_maxlifetime');
        }

        $this->option = $option;
    }

    public function open($save_path, $session_name) : bool
    {
        $this->handler = CustomRedis::connect();

        return true;
    }

    public function read($session_id) : string
    {
        $sessionId = $this->handler->get($this->option['session_id_key']);
        if (empty($sessionId)) {
            $sessionId = $this->option['prefix'] . $session_id;
        }

        $result = $this->handler->get($sessionId);

        return empty($result) ? '' : $result;
    }

    public function write($session_id, $session_data) : bool
    {
        $sessionId = $this->handler->get($this->option['session_id_key']);
        if (empty($sessionId)) {
            $sessionId = $this->option['prefix'] . $session_id;
        }

        $result = $this->handler->setEx($sessionId, $this->option['max_lifetime'], $session_data);
        return empty($result) ? false : true;
    }

    public function destroy($session_id) : bool
    {
        $sessionId = $this->option['prefix'] . $session_id;
        $result = $this->handler->delete($sessionId);

        return  empty($result) ? false : true;
    }

    public function close() : bool
    {
        // code...
        $this->handler->close();

        return true;
    }

    public function gc($maxlifetime) : bool
    {
        // code...
        return false;
    }
}
