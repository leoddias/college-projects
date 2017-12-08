<?php
    require 'connection.php';
    insereCliente();

function insereCliente()
{
    global $connect;
    
    $nomeCliente = $_GET["nome"];
    $emailCliente = $_GET["email"];
    $senhaCliente = $_GET["senha"];
    $enderecoCliente = $_GET["local"];
    $telefoneCliente = $_GET["telefone"];
                
    $query = "INSERT INTO CAD_CLIENTE (nomeCliente,emailCliente,senhaCliente,enderecoCliente,telefoneCliente) "
            . "values ('$nomeCliente','$emailCliente','$senhaCliente','$enderecoCliente','$telefoneCliente')";
    
    mysqli_query($connect, $query) or die (mysql_error($connect));
    mysqli_close($connect);
            
}

?>