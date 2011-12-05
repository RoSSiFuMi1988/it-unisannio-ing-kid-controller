<?php

$connection=mysql_connect (localhost, 'root', 'Emanuela');
if (!$connection) {
	die('Non connesso : ' . mysql_error());
}

$db_selected = mysql_select_db('geolocalizzazione', $connection);
if (!$db_selected) {
	die ('database non selezionato : ' . mysql_error());
}

$query = "SELECT latitudine, longitudine FROM posizione WHERE idposizione=6";
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

// Genera il tag di chiusura del nodo principale
echo '</markers>';

?>
