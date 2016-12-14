<?php
mysql_connect("localhost","root","");
mysql_select_db("demovolley");

$id= $_POST["ID"];
$email = $_POST["EMAIL"];
$qr = "UPDATE sinhvien SET EMAIL='$email' WHERE ID='$id'";

if(mysql_query($qr)){
  echo "ok";
}else{
  echo "loi";
}

?>
