<?php
include "connect.php";

// Check if all required POST parameters are set
if(isset($_POST['id'])) {
    // Retrieve POST data
    $id = $_POST['id'];

    // Construct the SQL query
    $query = 'DELETE FROM `sanphammoi` WHERE `id`='.$id;

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
