<?php

define('hostname', '127.0.0.1');
define('user', 'root');
define('password', '123456');
define('databaseName', 'deliverit');

$connect = mysqli_connect(hostname, user, password, databaseName);

?>