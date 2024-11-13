<?php
include "connect.php";

// Check if all required POST parameters are set
if(isset($_POST['email'], $_POST['pass'], $_POST['username'], $_POST['mobile'])) {
    // Retrieve POST data
    $email = $_POST['email'];
    $pass = $_POST['pass'];
    $username = $_POST['username'];
    $mobile = $_POST['mobile'];

    // Check if email already exists in the database
    $query = 'SELECT * FROM `user` WHERE `email`="'.$email.'"';
    $data = mysqli_query($conn, $query);
    $numrow = mysqli_num_rows($data);

    // If email exists, return appropriate message
    if($numrow > 0) {
        $arr = [
            'success' => true,
            'message' => "Email đã tồn tại",
        ];
    } else {
        // If email doesn't exist, insert new user data into the database
        $query = 'INSERT INTO `user`(`email`, `pass`, `username`, `mobile`) VALUES ("'.$email.'","'.$pass.'","'.$username.'","'.$mobile.'")';
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
    }

    // Output the result as JSON
    print_r(json_encode($arr));
} else {
    // Handle the case where one or more POST parameters are missing
    echo "Some POST parameters are missing";
}
?>
