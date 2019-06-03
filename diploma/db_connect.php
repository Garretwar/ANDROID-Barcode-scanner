<?php

/**
 * Класс для подключения к БД
 */
class DB_CONNECT 
{
 
    // Конструктор
    function __construct() 
	{
        // Соеденение с БД
        $this->connect();
    }
 
    // дуструктор
    function __destruct() 
	{
        // закрываем соеденение с БД
        $this->close();
    }
 
    /**
     * Функция соеденения с БД
     */
    function connect() 
	{
        // импортируем переменные для подклчения к БД
        require_once __DIR__ . '/1.php';
 
        // Подключаемся к БД
        $con = mysql_connect(host, user, pswd) or die(mysql_error());
 
        // выбираем БД
        $db = mysql_select_db(database) or die(mysql_error()) or die(mysql_error());
 
        // возвращаем соеденение
        return $con;
    }
 
    /**
     * Функция закрытия соеденения
     */
    function close() 
	{
        // Закрываем соеденение с БД
        mysql_close();
    }
 
}
 

?>