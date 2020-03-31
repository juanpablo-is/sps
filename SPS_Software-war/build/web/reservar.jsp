<%-- 
    Document   : reservar
    Created on : 24/03/2020, 07:07:15 PM
    Author     : Juan Pablo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>RESERVAR</title>
        <script src="js/all.min.js"></script>
        <link href="https://fonts.googleapis.com/css?family=Roboto+Condensed:300,400,700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="css/inicio.css"/>
    </head>
    <body>
        <input id="namePage" type="hidden" value="2"/>
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
                <div id="formR">
                    <%--<c:choose>--%>
                        <%--<c:when test="${parqueaderos == 3}">--%>
                            <!--<h3>YA TIENE UNA RESERVA</h3>-->
                        <%--</c:when>--%>    
                        <%--<c:otherwise>--%>
                            <h3>RESERVA TU ESPACIO AHORA</h3>
                            <form action="./ReservaServlet" method="POST" id="formReserva">
                                <div>
                                    <label for="dia">DIA:</label>
                                    <input type="date" id="dia" name="dia" required/>
                                </div>

                                <div>
                                    <label for="entrada">HORA ENTRADA:</label>
                                    <input type="time" id="entrada" name="entrada" required/>
                                </div>

                                <!--<div>-->
                                <!--<label for="salida">HORA SALIDA:</label>-->
                                <!--<input type="time" id="salida" name="salida"/>-->
                                <!--</div>-->

                                <div>
                                    <label for="parqueadero">PARQUEADERO:</label>
                                    <select name="idCliente" id="parqueadero">
                                        <option value="vacio">SELECCIONE UN PARQUEADERO:</option>
                                        <c:forEach items="${parqueaderos}" var="parqueadero">
                                            <option value="${parqueadero.id}">${parqueadero.direccion} - Cupos Disponibles: ${parqueadero.cupos}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <input type="submit" value="RESERVAR" name="reservar"/>
                            </form>
                        <%--</c:otherwise>--%>
                    <%--</c:choose>--%>
                </div>
            </section>
        </main>

        <script type="text/javascript" src="js/inicio.js"></script>
    </body>
</html>
