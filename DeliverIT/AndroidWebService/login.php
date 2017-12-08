<?php

require 'connection.php';
logar();

function logar()
{
    global $connect;
    
    $email = $_GET["email"];
    $senha = $_GET["senha"];
                
    $query = "SELECT * FROM CAD_CLIENTE WHERE EMAILCLIENTE = '$email' AND SENHACLIENTE ='$senha'";
    
    $result = mysqli_query($connect, $query);
    $number_of_rows = mysqli_num_rows($result);
    
    $temp_array = array();
    
    if($number_of_rows > 0){
        while ($row = mysqli_fetch_assoc($result)){
            $temp_array[] = $row;
        }
    }
    
    header('Content-Type: application/json');
    echo json_encode(array("cad_cliente"=>$temp_array));
    mysqli_close($connect);
    
}

?>