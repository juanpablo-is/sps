<%-- 
    Document   : historial
    Created on : 30/03/2020, 02:45:51 PM
    Author     : Juan Pablo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HISTORIAL</title>
        <script src="js/all.min.js"></script>
        <link href="https://fonts.googleapis.com/css?family=Roboto+Condensed:300,400,700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="css/inicio.css"/>
    </head>
    <body>
        <input id="namePage" type="hidden" value="4"/>
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
                <c:choose>
                    <c:when test="${reservas == null}">
                        <div id="textoReserva">
                            <h3>NO TIENE HISTORIAL AUN</h3>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div id="history">
                            <c:forEach items="${reservas}" var="reserva">
                                <c:choose>
                                    <c:when test="${reserva.ocupado == true}">
                                        <div class="cardsHistory ocupado">
                                            <h2><span>Dia: </span> ${reserva.dia}</h2>
                                            <h2><span>Entrada: </span> ${reserva.entrada}</h2>
                                            <h2><span>Ubicación: </span> ${reserva.idCliente.direccion}</h2>
                                        </div>
                                    </c:when> 
                                    <c:otherwise>
                                        <div class="cardsHistory">
                                            <h2><span>Dia: </span> ${reserva.dia}</h2>
                                            <h2><span>Entrada: </span> ${reserva.entrada}</h2>
                                            <h2><span>Ubicación: </span> ${reserva.idCliente.direccion}</h2>
                                            <h2><span>Salida: </span> ${reserva.salida}</h2>
                                            <h2><span>Precio: </span> ${reserva.precio}</h2>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </div>
                    </c:otherwise>
                </c:choose>
            </section>
        </main>

        <script type="text/javascript" src="js/inicio.js"></script>
    </body>
</html>
