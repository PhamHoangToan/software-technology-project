<?php
include "connect.php";

// Validate and sanitize POST data
$page = isset($_POST['page']) ? intval($_POST['page']) : 1;
$loai = isset($_POST['loai']) ? intval($_POST['loai']) : 1;

$total = 5;
$pos = ($page - 1) * $total;

$query = 'SELECT * FROM `sanphammoi` WHERE `loai`=' . $loai . ' LIMIT ' . $pos . ',' . $total.'';

$data = mysqli_query($conn, $query);
$result = array();
while ($row = mysqli_fetch_assoc($data)) {
    $result[] = $row;
}

if (!empty($result)) {
    $arr = [
        'success' => true,
        'message' => "Thanh cong",
        'result' => $result
    ];
} else {
    $arr = [
        'success' => false,
        'message' => "Khong thanh cong",
        'result' => $result
    ];
}

// Encode the array into JSON format
echo json_encode($arr);
?>

