<?php

$host='https://rudy.zzz.com.ua/mysql/index.php'; 
$database='Document'; 
$user='xoloc';
$pswd='22404673Qq';
 
$dbh = mysql_connect($host, $user, $pswd) or die("Не могу соединиться с MySQL.");
mysql_select_db($database) or die("Не могу подключиться к базе.");
$query = "SELECT * FROM `docs`";
$res = mysql_query($query);
while($row = mysql_fetch_array($res))
{
echo "Номер: ".$row['ID']."<br>\n";

echo "Силка: ".$row['Links']."<br><hr>\n";
}
?>
mydiploma.zzz.com.ua