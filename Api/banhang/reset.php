<?php
include "connect.php";
if(isset($_POST['submit_email']) && $_POST['email'])
{
  $query = 'SELECT * FROM `user` WHERE `email`="'.$email.'" ';
  $data = mysqli_query($conn, $query);

  $result=array();
  while($row=mysqli_fetch_assoc($data)){
    $result[]=($row);
    //code
  }


  if(empty($result)){
    $arr=[
      'success'=>false,
      'message'=>"Mail khong chinh xac",
      'result'=>$result
    ];
  }else{
    //sent mail


   $email=($result[0]["email"]);
    $pass=($result[0]["pass"]);

      $link="<a href='http://192.168.1.106/banhang/reset_pass.php?key=".$email."&reset=".$pass."'>Click To Reset password</a>";
    require_once('phpmail/PHPMailerAutoload.php');
    $mail = new PHPMailer();
    $mail->CharSet =  "utf-8";
    $mail->IsSMTP();
    // enable SMTP authentication
    $mail->SMTPAuth = true;                  
    // GMAIL username
    $mail->Username = "htoan6270@gmail.com";
    // GMAIL password
    $mail->Password = "130903lchp!";
    $mail->SMTPSecure = "ssl";  
    // sets GMAIL as the SMTP server
    $mail->Host = "smtp.gmail.com";
    // set the SMTP port for the GMAIL server
    $mail->Port = "465";
    $mail->From=htoan6270@gmail.com;
    $mail->FromName='App ban hang';
    $mail->AddAddress($email, 'reciever_name');
    $mail->Subject  =  'Reset Password';
    $mail->IsHTML(true);
    $mail->Body    = 'Click On This Link to Reset Password '.$pass.'';
    if($mail->Send())
    {
      echo "Check Your Email and Click on the link sent to your email";
    }
    else
    {
      echo "Mail Error - >".$mail->ErrorInfo;
    }
  } 
  }
  print_r($arr);

  
?>