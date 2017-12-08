<?php

require 'connection.php';
selectProdutoPorCategoria();

function selectProdutoPorCategoria()
{
    global $connect;
    
    $categoria = $_GET["cat"];
    $query = "SELECT * FROM CAD_PRODUTO WHERE CODCATEGORIA IN (SELECT CODCATEGORIA FROM CAD_CATEGORIA WHERE NOMECATEGORIA = '$categoria')";
    
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