    <%-- 
    Document   : Asignar
    Created on : 25/04/2020, 02:58:38 PM
    Author     : andre
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
                    <h3>RESERVAR UN PARQUEADERO DISPONIBLE</h3>
                    <form action="./AsignarServlet" method="POST" id="formReserva">
                        <div>
                            <label for="dia">DIA:</label>
                            <input type="date" id="dia" name="dia" required/>
                            <input type="hidden" name="cliente" value="${perfil.id}"/>
                        </div>
                        <div>
                            <label for="hora">HORA:</label>
                            <input type="time" id="hora" name="hora" required/>
                        </div>

                        <div>
                            <label for="placa">PLACA:</label>
                            <input type="text" id="placa" name="placa" required/>
                        </div>

                        <input type="submit" value="ASIGNAR" name="reservar"/>
                    </form>
                    <%--</c:otherwise>--%>
                    <%--</c:choose>--%>
                </div>
            </section>
        </main>
        <script type="text/javascript" src="js/inicioUsuario.js"></script>
    </body>
</html>