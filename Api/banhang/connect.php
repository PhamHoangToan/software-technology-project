<?php
$host = "localhost";
$user = "root";
$pass = "";
$database = "dataonline";

// Create connection
$conn = new mysqli($host, $user, $pass, $database);

// Check connection
if ($conn->connect_error) {
   
} else {
}

// Set charset
mysqli_set_charset($conn, "utf8");

?>