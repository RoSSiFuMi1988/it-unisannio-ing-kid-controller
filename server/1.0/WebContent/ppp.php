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

$query = sprintf("select p.latitudine, p.longitudine, l.latitudine as lat1, l.longitudine as lon1, l.raggio
    from posizione p join luogo l
    where idPosizione=(select max(POSIZIONE_idPosizione)
            from associa
            where DISPOSITIVO_idDispositivo=(select idDISPOSITIVO
            from dispositivo
            where imei='%s'))
            and idLuogo=(select LUOGO_idLUOGO from dispositivo 
    where imei='%s');", mysql_real_escape_string($imei), mysql_real_escape_string($imei));

$result = mysql_query($query);
if (!$result) {
    die('Query invalida: ' . mysql_error());
}

$row = @mysql_fetch_assoc($result);
$latitudine=$row['latitudine'];
$longitudine=$row['longitudine']; 
$lat1=$row['lat1'];
$lon1=$row['lon1'];
$raggio1=$row['raggio'];

header("Content-type: text/xml");
    
echo '<markers>';
echo '<marker ';
echo 'latitudine="' . $row['latitudine'] . '" ';
echo 'longitudine="' . $row['longitudine'] . '" ';
echo 'lat1="' . $row['lat1'] . '" ';
echo 'lon1="' . $row['lon1'] . '" ';
echo 'raggio1="' . $row['raggio'] . '" ';
echo '/>';
echo '</markers>';
?>
