<?php

#print_r($_POST);
include("mysql.php");

#print "User: " . $_POST[username] . "\n";
#print "Pass: " . $_POST[password] . "\n";
#print "Cmd: " . $_POST[cmd] . "\n";


if($_POST[cmd] == "login") {

	$sql = "SELECT * FROM users WHERE username='" . $_POST[username] . "' and password=md5('".$_POST[password]."')";
	$user_results = DoMysqlQuery("openemr", $sql);

	#print_r($user_results);
	if($user_results && $_POST[password] != "" && $user_results[0]) {
			print "Pass.";
	} else {	
			print "Fail.";
	}

} elseif($_POST[cmd] == "hasLoggedIn") {
	$sql = "SELECT id from users WHERE username='" . $_POST[username] ."'";
	$user_results = DoMysqlQuery("openemr", $sql);
	
	foreach($sql_results as $res) {
		print $res[id] . "\n";
	}
}
elseif($_POST[cmd] == "patientList") {

	$sql = "SELECT fname, lname, dob, pid FROM openemr.patient_data";
	$sql_results = DoMysqlQuery("openemr", $sql);

	foreach($sql_results as $res) {
		print $res[fname] . ',' . $res[lname] .','. $res[dob] .','. $res[pid] ."\n";
	}
	
}elseif($_POST[cmd] == "patientVitals") {

	$sql = "SELECT id, date, bmi, temperature FROM openemr.form_vitals WHERE pid='" . $_POST[pid] . "' order by date desc";
	$sql_results = DoMysqlQuery("openemr", $sql);

	foreach($sql_results as $res) {
		print $res[id] . ',' . $res[date] .','. $res[bmi] .','. $res[temperature] ."\n";
	}
	
}
elseif($_POST[cmd] == "medicationList") {

	$sql = "SELECT id, patient_id, drug, dosage, unit, active FROM openemr.prescriptions WHERE patient_id='" . $_POST[pid] . "'";
	$sql_results = DoMysqlQuery("openemr", $sql);

	foreach($sql_results as $res) {
		print $res[id] . ',' . $res[patient_id] .','. $res[drug] . ','. $res[dosage] .','. $res[unit] . ','. $res[active] ."\n";
	}	
}
elseif($_POST[cmd] == "doctorSchedule") {
	$sql = "SELECT pc_aid, pc_title, pc_time FROM openemr.openemr_postcalendar_events where pc_aid = '" . $_POST[pc_aid] . "'";
	$sql_results = DoMysqlQuery("openemr", $sql);
	 
	foreach($sql_results as $res) {
		print $res[pc_aid] . ',' . $res[pc_title] . ',' . $res[pc_time] . "\n";
	}
}
elseif($_POST[cmd] == "patientNotes") {
	$sql = "select id, title, body, date from openemr.pnotes where pid = '" . $_POST[pid] . "'";
	$sql_results = DoMysqlQuery("openemr", $sql);
	
	foreach($sql_results as $res) {
		print $res[id] . ',' . $res[title] . ',' . $res[body] . ',' . $res[date] . "\n";
	}
}
elseif ($_POST[cmd] == "updatePatientNote") {
	$sql = "update pnotes set body = '" . $_POST[msg] . "' where id = '" . $_POST[id] . "'";
	DoMysqlQuery("openemr", $sql);
	print "Hi";
}
elseif ($_POST[cmd] == "createPatientNote") {
	$sql = "select count(*) from pnotes";
	$sql_results = DoMysqlQuery("openemr", $sql);
	$res = $sql_results;
	$res = $res + 1;
	$sql = "insert into pnotes values('" . $res . "', '" . NOW() . 
	"', '" . $_POST[msg] . "', '1', 'admin', NULL, NULL, NULL, '" . $_POST[title] . "', NULL, '0'";
	DoMysqlQuery("openemr", $sql);
	
	
}
else {
	print "No.";
}


?>