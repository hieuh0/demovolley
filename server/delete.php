<?php
mysql_connect("localhost","root","");
mysql_select_db("demovolley");

$id= $_POST["ID"];
$qr = "DELETE FROM sinhvien WHERE ID='$id'";

if(mysql_query($qr)){
  echo "ok";
}else{
  echo "loi";
}

?>
