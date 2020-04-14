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
        <input id="namePage" type="hidden" value="1"/>
        <header>
            <div id="headerLogo">
                <img src="images/logo.jpg" alt="Logo"/>
                <h2>SPSystem</h2>
            </div>
            <h2 id="textoBienvenida">BIENVENIDO ${persona.nombre}</h2>
        </header>
        <main>
            <%@include  file="menu.jsp" %>
            <section id="pnlPrincipal">
                <div id="pnlInicio">
                    <div id ="pnlReservasInicio">
                        <h2>No hay reservas por mostrar</h2>
                    </div>
                    <div id="infoInicio">
                        <div id="mapaInicio">
                            <h2>MAPA PARQUEADEROS</h2>
                            <div id="mapa">
                            </div>
                        </div>
                        <div id="graficoInicio">
                            <h2>USO DE RESERVAS</h2>
                            <div id="grafico">
                                <c:choose>
                                    <c:when test="${grafica.length() > 0}">
                                        <img src="${grafica}"/>
                                    </c:when>    
                                    <c:otherwise>
                                        <h2>NO HAY DATOS PARA GRAFICAR</h2>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </main>
        <script type="text/javascript" src="js/inicio.js"></script>
    </body>
</html>