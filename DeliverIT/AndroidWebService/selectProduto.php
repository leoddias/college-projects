<?php

require 'connection.php';
selectProduto();

function selectProduto()
{
    global $connect;
    
    $query = "SELECT * FROM CAD_PRODUTO";
    
    $result = mysqli_query($connect, $query);
    $number_of_rows = mysqli_num_rows($result);
    
    $temp_array = array();
    
    if($number_of_rows > 0){
        while ($row = mysqli_fetch_assoc($result)){
            $temp_array[] = $row;
        }
    }
    
    
    header('Content-Type: application/json');
    echo json_encode(array("cad_produto"=>$temp_array));
    mysqli_close($connect);
}

?>