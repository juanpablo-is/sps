<%-- 
    Document   : parqueaderos
    Created on : 30/03/2020, 09:05:06 PM
    Author     : Juan Pablo
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PARQUEADEROS</title>
        <script src="js/all.min.js"></script>
        <link href="https://fonts.googleapis.com/css?family=Roboto+Condensed:300,400,700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="css/inicio.css"/>
    </head>
    <body>
        <input id="namePage" type="hidden" value="3"/>
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
                <div id="pnlParqueaderos">
                    <c:forEach items="${parqueaderos}" var="parqueadero" varStatus="loop">
                        <div class="cardParqueaderos">
                            <div class="cardParqueaderoInterno">
                                <img src="images/imagen_parking_<c:out value="<%= (int)(Math.random() * 5 + 1)%>"/>.jpg"/>
                                <h3><span>Ubicación: </span>${parqueadero.direccion}</h3>
                                <h3><span>Cupos: </span>${parqueadero.cupos}</h3>
                                <h3><span>Precio/min :</span>${parqueadero.precio}</h3>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </section>
        </main>
        <script type="text/javascript" src="js/inicio.js"></script>
    </body>
</html>