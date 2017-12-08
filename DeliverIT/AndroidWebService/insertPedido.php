<?php

require 'connection.php';
inserePedido();

function inserePedido()
{
    global $connect;
    
    $idcliente = $_POST["idcliente"];
    $idpedidoitem = $_POST["idpedidoitem"];
                
    $query = "INSERT INTO MOVPEDIDO (idcliente,idpedidoitem) values ('$idcliente','$idpedidoitem')";
    
    mysqli_query($connect, $query) or die (mysql_error($connect));
    mysqli_close($connect);
}
?>