<?php
include "connect.php";

// Check if all required POST parameters are set
if(isset($_POST['tensp'], $_POST['gia'], $_POST['hinhanh'], $_POST['mota'], $_POST['loai'])) {
    // Retrieve POST data
    $tensp = $_POST['tensp'];
    $gia = $_POST['gia'];
    $hinhanh = $_POST['hinhanh'];
    $mota = $_POST['mota'];
    $loai = $_POST['loai'];

    // Construct the SQL query
    $query = 'INSERT INTO `sanphammoi`(`tensp`, `giasp`, `hinhanh`, `mota`, `loai`) VALUES ("'.$tensp.'",'.$gia.',"'.$hinhanh.'","'.$mota.'",'.$loai.')';

    // Execute the query
    $data = mysqli_query($conn, $query);

    // Check if insertion was successful
    if($data == true) {
        $arr = [
            'success' => true,
            'message' => "Thành công",
        ];
    } else {
        $arr = [
            'success' => false,
            'message' => "Không thành công",
        ];
    }

    // Output the result as JSON
    print_r(json_encode($arr));
} else {
    // Handle the case where one or more POST parameters are missing
    echo "Some POST parameters are missing";
}
?>
