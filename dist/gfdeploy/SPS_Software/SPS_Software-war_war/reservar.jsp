<%-- 
    Document   : reservar
    Created on : 24/03/2020, 07:07:15 PM
    Author     : Juan Pablo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <link rel="icon" type="image/gif" href="images/logo.jpg">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reservar</title>
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
                    <c:choose>
                        <c:when test="${perfil.getClass().name eq 'com.sps.entity.Usuario'}">
                            <c:choose>
                                <c:when test="${reservaCheck != null}">
                                    <h2>RESERVA EN ESPERA</h2>
                                    <h3><span>Nombre: </span>${reservaCheck.idPlaza.idCliente.nombre}</h3>
                                    <h3><span>Dirección: </span>${reservaCheck.idPlaza.idCliente.direccion}</h3>
                                    <h3><span>Fecha: </span>${reservaCheck.fecha}</h3>
                                    <c:choose>
                                        <c:when test="${cancelar eq 'si'}">
                                            <div id="botonesReserva">
                                                <button onclick="cancelarReserva()">CANCELAR RESERVA</button>
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <h3><span>Precio: </span>$ ${precio}</h3>
                                            <div id="botonesReserva">
                                                <button onclick="liquidarReserva()">LIQUIDAR RESERVA</button>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                                </c:when>
                                <c:otherwise>
                                    <h3>RESERVA TU ESPACIO AHORA</h3>
                                    <form action="./reservar" method="POST" id="formReserva">
                                        <div>
                                            <label for="fecha">FECHA:</label>
                                            <input type="datetime-local" id="fecha" name="fecha" required/>
                                        </div>
                                        <div>
                                            <input type="radio" id="cubierto" name="cubierto" value="si" required>
                                            <label for="cubierto">Cubierto</label><br>
                                            <input type="radio" id="noCubierto" name="cubierto" value="no">
                                            <label for="noCubierto">No cubierto</label><br>
                                        </div>
                                        <div>
                                            <label for="parqueadero">PARQUEADERO:</label>
                                            <select class="select" name="idCliente" id="parqueadero">
                                            </select>
                                        </div>
                                        <input onclick="notificacion('Reserva creada')" type="submit" value="RESERVAR" name="proceso"/>
                                    </form>
                                </c:otherwise>
                            </c:choose>
                        </c:when>  
                        <c:when test="${perfil.getClass().name eq 'com.sps.entity.Cliente'}">
                            <form action="./reservar" method="POST" id="formReserva">
                                <div>
                                    <label for="fecha">FECHA:</label>
                                    <input type="datetime-local" id="fecha" name="fecha" required/>
                                    <input type="hidden" name="cliente" value="${perfil.id}"/>
                                </div>
                                <div>
                                    <label for="placa">PLACA:</label>
                                    <input type="text" id="placa" name="placa" required/>
                                </div>

                                <div>
                                    <label for="tipoVehiculo">TIPO VEHICULO</label>
                                    <select name="tipoVehiculo" id="tipoVehiculo">
                                        <option value="1">CARRO</option>
                                        <option value="2">MOTO</option>
                                    </select>
                                </div>

                                <div>
                                    <input type="radio" id="cubierto" name="cubierto" value="si" required>
                                    <label for="cubierto">Cubierto</label><br>
                                    <input type="radio" id="noCubierto" name="cubierto" value="no">
                                    <label for="noCubierto">No cubierto</label><br>
                                </div>
                                <input onclick="notificacion('Reserva creada')" type="submit" value="RESERVAR" name="reservar"/>
                            </form>
                        </c:when>    
                        <c:otherwise>
                            <h2>SE HA PRESENTADO UN ERROR</h2>
                        </c:otherwise>
                    </c:choose>

                    <%--</c:otherwise>--%>
                    <%--</c:choose>--%>
                </div>
            </section>
        </main>
        <script type="text/javascript" src="js/inicioUsuario.js"></script>
    </body>
</html>