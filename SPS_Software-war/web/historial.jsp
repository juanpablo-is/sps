<%-- 
    Document   : historial
    Created on : 30/03/2020, 02:45:51 PM
    Author     : Juan Pablo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <link rel="icon" type="image/gif" href="images/logo.jpg">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Historial</title>
        <script src="js/all.min.js"></script>
        <link href="https://fonts.googleapis.com/css?family=Roboto+Condensed:300,400,700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="css/inicio.css"/>
    </head>
    <body>
        <input type="hidden" id="inputPerfil" value="${perfil}"/>
        <script>
            if (document.getElementById("inputPerfil").value === '') {
                window.open("./", "_self");
            }
            document.getElementById("inputPerfil").remove();
        </script>
        <input id="namePage" type="hidden" value="3"/>
        <main>
            <%@include  file="menu.jsp" %>
            <section id="pnlPrincipal">
                <c:choose>
                    <c:when test="${historiales == null}">
                        <div id="textoReserva">
                            <h3>NO TIENE HISTORIAL AUN</h3>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <table id="history">
                            <c:choose>
                                <c:when test="${perfil.getClass().name eq 'com.sps.entity.Usuario'}">
                                    <tr class="row">
                                        <th>PARQUEADERO</th>
                                        <th>FECHA ENTRADA</th>
                                        <th>FECHA SALIDA</th>
                                        <th>PRECIO</th>
                                        <!--<th>ENTRADA</th>-->
                                    </tr>
                                    <c:forEach items="${historiales}" var="historial">
                                        <tr class="row">
                                            <td>${historial.idReserva.idPlaza.idCliente.nombre} - ${historial.idReserva.idPlaza.idCliente.direccion}</td>
                                            <td>${historial.idReserva.fecha}</td>
                                            <td>${historial.salida}</td>
                                            <td>${historial.precio}</td>
                                            <%--<td>${historial.idReserva.entrada}</td>--%>
                                        </tr>
                                    </c:forEach>

                                </c:when>    
                                <c:when test="${perfil.getClass().name eq 'com.sps.entity.Cliente'}">
                                    <tr class="row">
                                        <th>CEDULA</th>
                                        <th>PLACA</th>
                                        <th>ENTRADA</th>
                                        <th>SALIDA</th>
                                        <th>PRECIO</th>
                                    </tr>
                                    <c:forEach items="${historiales}" var="historial">
                                        <tr class="row">
                                            <td>${historial.idReserva.idUsuario.idPersona.cedula}</td>
                                            <td>${historial.idReserva.idUsuario.placa}</td>
                                            <td> ${historial.idReserva.fecha}</td>
                                            <td>${historial.salida}</td>
                                            <td>$ ${historial.precio}</td>
                                        </tr>
                                    </c:forEach>
                                </c:when>
                            </c:choose>
                            <%--<c:forEach items="${historiales}" var="historial">
                                <c:choose>
                                <c:when test="${reserva.estado == true}">
                                <c:choose>
                                    <c:when test="${perfil.getClass().name eq 'com.sps.entity.Usuario'}">
                                        <div class="cardsHistory">
                                            <h2><span>Dia: </span> ${reserva.dia}</h2>
                                            <h2><span>Entrada: </span> ${reserva.entrada}</h2>
                                            <h2><span>Ubicación: </span> ${reserva.idPlaza.idCliente.direccion}</h2>
                                        </div>
                                    </c:when>    
                                    <c:when test="${perfil.getClass().name eq 'com.sps.entity.Cliente'}">
                                        <div class="cardsHistory">
                                            <h2><span>Cedula: </span>${historial.idReserva.idUsuario.idPersona.cedula} </h2>
                                            <h2><span>Placa: </span>${historial.idReserva.idUsuario.placa}</h2>
                                            <h2><span>Entrada: </span> ${historial.idReserva.entrada}</h2>
                                            <h2><span>Salida: </span> ${historial.salida}</h2>
                                            <h2><span>Precio: </span> $ ${historial.precio}</h2>
                                        </div>
                                    </c:when>
                                </c:choose>
                            <%--</c:when>
                            <c:otherwise>
                                <c:choose>
                                    <c:when test="${perfil.getClass().name eq 'com.sps.entity.Usuario'}">
                                        <div class="cardsHistory">
                                            <h2><span>Dia: </span> ${reserva.dia}</h2>
                                            <h2><span>Entrada: </span> ${reserva.entrada}</h2>
                                            <h2><span>Ubicación: </span> ${reserva.idPlaza.idCliente.direccion}</h2>
                                            <h2><span>Salida: </span> ${reserva.salida}</h2>
                                            <h2><span>Precio: </span> ${reserva.precio}</h2>
                                        </div>
                                    </c:when>    
                                    <c:when test="${perfil.getClass().name eq 'com.sps.entity.Cliente'}">
                                        <div class="cardsHistory">
                                            <h2><span>Dia: </span> ${reserva.dia}</h2>
                                            <h2><span>Entrada: </span> ${reserva.entrada}</h2>
                                            <h2><span>Salida: </span> ${reserva.salida}</h2>
                                            <h2><span>Tiempo transcurrido: </span> ${reserva.salida} </h2>
                                            <h2><span>Precio: </span> ${reserva.precio} </h2>
                                        </div>
                                    </c:when>
                                </c:choose>
                            </c:otherwise>
                        </c:choose>
                        </c:forEach>--%>
                        </table>
                    </c:otherwise>
                </c:choose>
            </section>
        </main>
        <script type="text/javascript" src="js/inicioUsuario.js"></script>
    </body>
</html>