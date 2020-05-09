<%-- 
    Document   : inicio
    Created on : 24/03/2020, 07:07:15 PM
    Author     : Juan Pablo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=yes">
        <meta http-equiv="Content-type" content="text/html;charset=UTF-8">
        <title>BIENVENIDO</title>
        <link href="https://fonts.googleapis.com/css?family=Roboto+Condensed:300,400,700&display=swap" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="https://js.api.here.com/v3/3.1/mapsjs-ui.css" />
        <link rel="stylesheet" href="css/inicio.css"/>
        <script src="js/all.min.js"></script>
        <script src="https://js.api.here.com/v3/3.1/mapsjs-core.js"
        type="text/javascript" charset="utf-8"></script>
        <script src="https://js.api.here.com/v3/3.1/mapsjs-service.js"
        type="text/javascript" charset="utf-8"></script>
        <script type="text/javascript" src="https://js.api.here.com/v3/3.1/mapsjs-ui.js"></script>
        <script type="text/javascript" src="https://js.api.here.com/v3/3.1/mapsjs-mapevents.js"></script>
    </head>
    <body>
        <input type="hidden" id="inputPerfil" value="${perfil}"/>
        <script>
            if (document.getElementById("inputPerfil").value === '') {
                window.open("http://localhost:8080/SPS_Software-war/", "_self");
            }
            document.getElementById("inputPerfil").remove();
        </script>
        <input id="namePage" type="hidden" value="1"/>
        <header>
            <div id="headerLogo">
                <a href="http://localhost:8080/SPS_Software-war/" style="display: contents;">
                    <img src="images/logo.jpg" alt="Logo"/>
                    <h2>SPSystem</h2>
                </a>
            </div>
            <h2 id="textoBienvenida">BIENVENIDO ${persona.nombre}</h2>
            <div id="panelInfo">
                <i onclick="iconoAccount()" id="iconoAccount" class="fas fa-user-circle"></i>
                <i onmouseenter="iconoFunction(true)" onmouseleave="iconoFunction(false)" id="iconoInfo" class="far fa-market"></i>
                <div id="pnlInfo">
                    <h2></h2>
                    <p></p>
                </div>
            </div>
        </header>
        <main>
            <c:choose>
                <c:when test="${perfil.getClass().name eq 'com.sps.entity.Usuario'}">
                    <input id="parqueaderos" type="hidden" value="${parqueaderos}"/>
                    <%@include  file="inicioUsuario.jsp" %>
                    <script>
                        var parqueaderosArray = ${parqueaderos};
                    </script>
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
    <script src="js/menu.js"></script>
</html>