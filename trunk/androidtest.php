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

}elseif($_POST[cmd] == "patientList") {

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
else {
	print "No.";
}


?>