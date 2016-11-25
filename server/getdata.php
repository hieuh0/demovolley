<?php
mysql_connect("localhost","root","");
mysql_select_db("demovolley");
$qr = mysql_query("SELECT * FROM sinhvien");
if(mysql_num_rows($qr) > 0){

  $res = array();
  while($row = mysql_fetch_array($qr)){
    array_push($res, array("ID"=>$row["ID"],"TEN" => $row["TEN"],"EMAIL"=>$row["EMAIL"]));
  }
  echo json_encode($res);
}else{
      echo "Khong Co Du Lieu";
}

?>
