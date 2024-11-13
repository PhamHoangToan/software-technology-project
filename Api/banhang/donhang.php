<?php
include "connect.php";

// Check if all required POST parameters are set
if(isset($_POST['sdt'], $_POST['email'], $_POST['tongtien'], $_POST['iduser'],$_POST['diachi'],$_POST['soluong'],$_POST['chitiet'])) {
    // Retrieve POST data
    $sdt=$_POST['sdt'];
    $email = $_POST['email'];
    $tongtien=$_POST['tongtien'];
    $iduser=$_POST['iduser'];
    $diachi=$_POST['diachi'];
    $soluong=$_POST['soluong'];
    $chitiet=$_POST['chitiet'];
    
    $query = 'INSERT INTO `donhang`( `iduser`, `diachi`, `sodienthoai`, `email`, `soluong`, `tongtien`) VALUES ('.$iduser.',"'.$diachi.'","'.$sdt.'","'.$email.'","'.$soluong.'","'.$tongtien.'")';
    $data = mysqli_query($conn, $query);
    if($data==true){
        $query = 'SELECT id AS iddonhang FROM `donhang` WHERE `iduser`='.$iduser.' ORDER BY id DESC LIMIT 1';
        $data = mysqli_query($conn, $query);
        while($row=mysqli_fetch_assoc($data)){
            $iddonhang=($row);
        }
        if(!empty($iddonhang)){
            $chitiet=json_encode($chitiet, true);
            foreach($chitiet as $key => $value){
                $truyvan='INSERT INTO `chitietdonhang`(`iddonhang`, `idsp`, `soluong`, `gia`) VALUES ('.$iddonhang["iddonhang"].','.$value["idsp"].','.$value["solong"].',"'.$value["giasp"].'")';
                echo $truyvan;
                $data = mysqli_query($conn, $truyvan);
            }
            if($data==true){
                $arr = [
                    'success' => true,
                    'message' => "thành công",
                ];
            }else{
                $arr = [
                    'success' => false,
                    'message' => "Không thành công",
                ];
            }
            print_r(json_encode($arr));
        }

    }else{
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
