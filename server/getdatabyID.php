<?php
mysql_connect("localhost","root","");
mysql_select_db("demovolley");
$id = $_GET["ID"];

$qr = mysql_query("SELECT * FROM sinhvien WHERE ID='$id'");
if(mysql_num_rows($qr) > 0){

  $res = array();
  while($row = mysql_fetch_array($qr)){
    array_push($res, array("TEN" => $row["TEN"],"EMAIL"=>$row["EMAIL"]));
  }
  echo json_encode($res);
}else{
      echo "Khong Co Du Lieu";
}

?>
