<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
  <link rel='stylesheet' type='text/css' href='css/site.css'>
  <link href="https://fonts.googleapis.com/css?family=Galada" rel="stylesheet">
  <title>Openbar</title>
</head>
<body>
	<c:url var="homeURL" value="/homePage"/>
    <header class="header">
    	<a href="${homeURL}"><h1 class="header__title">Openbar</h1></a>
    </header>

    <main class="container">