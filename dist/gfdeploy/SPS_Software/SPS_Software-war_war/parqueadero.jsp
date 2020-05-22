<%-- 
    Document   : parqueaderos
    Created on : 30/03/2020, 09:05:06 PM
    Author     : Juan Pablo
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Parqueaderos</title>
        <script src="js/all.min.js"></script>
        <link href="https://fonts.googleapis.com/css?family=Roboto+Condensed:300,400,700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="css/inicio.css"/>
    </head>
    <body>
        <input type="hidden" id="inputPerfil" value="${perfil}"/>
        <script>
            if (document.getElementById("inputPerfil").value === '') {
                window.open("http://localhost:8080/SPS_Software-war/", "_self");
            }
        </script>
        <input id="namePage" type="hidden" value="4"/>
        <main>
            <%@include  file="menu.jsp" %>
            <section id="pnlPrincipal">
                <div id="pnlParqueaderoIndividual">
                    <div id="mapaParqueaderoIndividual"></div>
                    <div id="infoParqueadero">
                        <h2>${cliente.nombre}</h2>
                        <h4><b>Dirección: </b>${cliente.direccion}</h4>
                        <h4><b>Precio min: </b>${cliente.precio}</h4>
                        <h4><b>Hora entrada: </b>${cliente.inicio}</h4>
                        <h4><b>Hora salida: </b>${cliente.fin}</h4>
                        <div id="reservarPI">
                            <button id="btnReservaPI" onclick="reservaPI('${cliente.id}')">RESERVAR</button>
                        </div>
                        <hr>
                        <c:choose>
                            <c:when test="${reservas != null}">
                                <h2>Listado de sus resevas</h2>
                                <hr>
                                <c:forEach items="${reservas}" var="reserva">
                                    <div class="cardReservaPI">
                                        <h4><b>Día: </b>${reserva.dia}</h4>
                                        <h4><b>Hora: </b>${reserva.entrada}</h4>
                                        <h4><b>Pago: </b>$ ...</h4>
                                    </div>
                                    <hr> 
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <h3>No tiene reservas con este parqueadero</h3>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>

            </section>
        </main>
        <script type="text/javascript" src="js/inicioUsuario.js"></script>
    </body>
</html>