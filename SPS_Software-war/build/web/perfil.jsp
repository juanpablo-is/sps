<%-- 
    Document   : perfil
    Created on : 9/05/2020, 10:55:09 PM
    Author     : Juan Pablo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Perfil</title>
        <link rel="stylesheet" href="css/perfil.css"/>       
        <link href="https://fonts.googleapis.com/css?family=Roboto+Condensed:300,400,700&display=swap" rel="stylesheet">
        <script src="js/all.min.js"></script>
    </head>
    <body>
        <div id="top">
            <header>
                <i onclick="goBack()" class="fas fa-arrow-circle-left" id="botonBack"></i>
                <h1>Perfil</h1>
            </header>
            <nav>
                <ul id="listaItems">
                    <li class="seleccionItem">PERFIL</li>
                    <li>AJUSTES</li>
                    <li>CAMBIOS</li>
                    <li>INFO</li>
                </ul>
            </nav>
        </div>
        <div id="target"></div>
    </body>

    <script src="js/perfil.js"></script>
</html>
