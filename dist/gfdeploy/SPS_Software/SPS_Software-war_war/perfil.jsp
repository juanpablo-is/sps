<%-- 
    Document   : perfil
    Created on : 9/05/2020, 10:55:09 PM
    Author     : Juan Pablo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <link rel="icon" type="image/gif" href="images/logo.jpg">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Perfil</title>
        <link rel="stylesheet" href="css/perfil.css"/>       
        <link href="https://fonts.googleapis.com/css?family=Roboto+Condensed:300,400,700&display=swap" rel="stylesheet">
        <script src="js/all.min.js"></script>
    </head>
    <body>
        <input type="hidden" id="inputPerfil" value="${perfil}"/>
        <script>
            if (document.getElementById("inputPerfil").value === '') {
                window.open("./", "_self");
            }
            document.getElementById("inputPerfil").remove();
        </script>
        <div id="top">
            <header>
                <i onclick="goBack()" class="fas fa-arrow-circle-left" id="botonBack"></i>
                <h1>Perfil</h1>
            </header>
            <nav>
                <ul id="listaItems">
                    <li class="seleccionItem">CUENTA</li>
                    <!--<li>AJUSTES</li>-->
                    <!--<li>PERFIL</li>-->
                    <li>INFO</li>
                </ul>
            </nav>
        </div>
        <div id="target"></div>
    </body>

    <script src="js/perfil.js"></script>
</html>
