<%-- 
    Document   : inicio
    Created on : 24/03/2020, 07:07:15 PM
    Author     : Juan Pablo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <link rel="icon" type="image/gif" href="images/logo.jpg">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=yes">
        <meta http-equiv="Content-type" content="text/html;charset=UTF-8">
        <title>Inicio</title>
        <link href="https://fonts.googleapis.com/css?family=Roboto+Condensed:300,400,700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="css/inicio.css"/>
        <script src="js/all.min.js"></script>
        <c:choose>
            <c:when test="${perfil.getClass().name eq 'com.sps.entity.Usuario'}">
                <link rel="stylesheet" type="text/css" href="https://js.api.here.com/v3/3.1/mapsjs-ui.css" />
                <script src="https://js.api.here.com/v3/3.1/mapsjs-core.js"
                type="text/javascript" charset="utf-8"></script>
                <script src="https://js.api.here.com/v3/3.1/mapsjs-service.js"
                type="text/javascript" charset="utf-8"></script>
                <script type="text/javascript" src="https://js.api.here.com/v3/3.1/mapsjs-ui.js"></script>
                <script type="text/javascript" src="https://js.api.here.com/v3/3.1/mapsjs-mapevents.js"></script>
            </c:when> 
            <c:when test="${perfil.getClass().name eq 'com.sps.entity.Cliente'}">
                <link rel="stylesheet" href="css/cliente.css"/>
            </c:when>
        </c:choose>
    </head>
    <body>
        <input type="hidden" id="inputPerfil" value="${perfil}"/>
        <script>
            if (document.getElementById("inputPerfil").value === '') {
                window.open("./", "_self");
            }
            document.getElementById("inputPerfil").remove();
        </script>
        <input id="namePage" type="hidden" value="1"/>
        <main>
            <c:choose>
                <c:when test="${perfil.getClass().name eq 'com.sps.entity.Usuario'}">
                    <%@include  file="inicioUsuario.jsp" %>
                    <script type="text/javascript" src="js/inicioUsuario.js"></script>
                </c:when>    
                <c:when test="${perfil.getClass().name eq 'com.sps.entity.Cliente'}">
                    <%@include  file="inicioCliente.jsp" %>
                </c:when>  
                <c:when test="${perfil.getClass().name eq 'com.sps.entity.Movilidad'}">
                    <%@include  file="inicioMovilidad.jsp" %>
                </c:when>  
                <c:otherwise>
                    <h2>SE HA PRESENTADO UN ERROR</h2>
                </c:otherwise>
            </c:choose>
        </main>
    </body>
</html>