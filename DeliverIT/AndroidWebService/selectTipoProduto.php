<?php

require 'connection.php';
selectTipoProduto();

function selectTipoProduto()
{
    global $connect;
    
    $query = "SELECT * FROM CAD_CATEGORIA";
    
    $result = mysqli_query($connect, $query);
    $number_of_rows = mysqli_num_rows($result);
    
    $temp_array = array();
    
    if($number_of_rows > 0){
        while ($row = mysqli_fetch_assoc($result)){
            $temp_array[] = $row;
        }
    }
    
    
    header('Content-Type: application/json');
    echo json_encode(array("cad_categoria"=>$temp_array));
    mysqli_close($connect);
}

?>