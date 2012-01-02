<?php
$imei = $_GET['imei'];
$connection=mysql_connect (localhost, 'root', 'Emanuela');
if (!$connection) {
	die('Non connesso : ' . mysql_error());
}

$db_selected = mysql_select_db('kidcontrol', $connection);
if (!$db_selected) {
	die ('database non selezionato : ' . mysql_error());
}

$query = sprintf("call trovaPosizione ('%s');", mysql_real_escape_string($imei));

$result = mysql_query($query);
if (!$result) {
	die('Query invalida: ' . mysql_error());
}

header("Content-type: text/xml");
	
echo '<markers>';
	
while ($row = @mysql_fetch_assoc($result)){
echo '<marker ';
echo 'latitudine="' . $row['latitudine'] . '" ';
echo 'longitudine="' . $row['longitudine'] . '" ';
echo '/>';
}
echo '</markers>';

?>

