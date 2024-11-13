<?php
include "connect.php";

// Check if all required POST parameters are set
if(isset($_POST['email'], $_POST['pass'])) {
    // Retrieve POST data
    $email = $_POST['email'];
    $pass = $_POST['pass'];
   

    $query = 'SELECT * FROM `user` WHERE `email`="'.$email.'" AND `pass`="'.$pass.'"';
   	$data = mysqli_query($conn, $query);

    $result=array();
	while($row=mysqli_fetch_assoc($data)){
		$result[]=($row);
		//code
	}
	if(!empty($result)){
		$arr=[
			'success'=>true,
			'message'=>"thanh cong",
			'result'=>$result
		];
	}else{
		$arr=[
			'success'=>false,
			'message'=>"khong thanh cong",
			'result'=>$result
		];
	}
	print_r(json_encode($arr));
} else {
    // Handle the case where one or more POST parameters are missing
    echo "Some POST parameters are missing";
}
?>
