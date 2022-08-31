<?php
require_once 'dbconnection.php';
$dbConnection = new DBConnection();
$conn = $dbConnection->getConnection();
$name=$_POST['name'];
$price = $_POST['price'];
$discount = $_POST['discount'];
$category = $_POST['category'];
$img=$_POST['upload'];

$filename="IMG".rand().".jpg";
file_put_contents("images/".$filename,base64_decode($img));

$query = "INSERT INTO foods (namefood, price, discount, picture, category) VALUES ('$name', '$price', '$discount', '$filename','$category')";
		if ($conn->query($query) === TRUE) {
			return "Inserted successfully";
		  } else {
			return "Error: " . $sql . "<br>" . $conn->error;
		  }
?>
