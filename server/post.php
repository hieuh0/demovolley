<?php
mysql_connect("localhost","root","");
mysql_select_db("demovolley");


$ten = $_POST["TEN"];
$email = $_POST["EMAIL"];

$qr = "INSERT INTO sinhvien VALUES(null,'$ten','$email')";
if(mysql_query($qr)){
  echo "thanhcong";
}else{
  echo "loi";
}
 ?>
