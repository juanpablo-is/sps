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
        <link rel="icon" type="image/gif" href="images/logo.jpg">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Parqueaderos</title>
        <link href="https://fonts.googleapis.com/css?family=Roboto+Condensed:300,400,700&display=swap" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="https://js.api.here.com/v3/3.1/mapsjs-ui.css" />
        <link rel="stylesheet" href="css/inicio.css"/>
        <script src="js/all.min.js"></script>
        <script src="https://js.api.here.com/v3/3.1/mapsjs-core.js" type="text/javascript" charset="utf-8"></script>
        <script src="https://js.api.here.com/v3/3.1/mapsjs-service.js" type="text/javascript" charset="utf-8"></script>
        <script type="text/javascript" src="https://js.api.here.com/v3/3.1/mapsjs-ui.js"></script>
        <script type="text/javascript" src="https://js.api.here.com/v3/3.1/mapsjs-mapevents.js"></script>
    </head>
    <body>
        <input type="hidden" id="inputPerfil" value="${perfil}"/>
        <script>
            if (document.getElementById("inputPerfil").value === '') {
                window.open("./", "_self");
            }
            document.getElementById("inputPerfil").remove();
        </script>
        <input id="namePage" type="hidden" value="4"/>
        <main>
            <%@include  file="menu.jsp" %>
            <section id="pnlPrincipal">
                <div id="pnlParqueaderoIndividual">
                    <div id="mapaParqueaderoIndividual">
                        <script>
                            var cliente = ${cliente};
                        </script>
                    </div>
                    <div id="infoParqueadero">
                        <h2>${cliente.nombre}</h2>
                        <h4><b>Dirección: </b>${cliente.direccion}</h4>
                        <h4><b>Precio min: </b>${cliente.precio}</h4>
                        <h4><b>Hora entrada: </b>${cliente.inicio}</h4>
                        <h4><b>Hora salida: </b>${cliente.fin}</h4>
                        <div id="reservarPI">
                            <button id="btnReservaPI" onclick="reservaPI('${cliente.id}')">RESERVAR</button>
                            <button onclick="reportePI('${cliente.id}')">REPORTAR</button>
                        </div>
                        <hr>
                        <c:choose>
                            <c:when test="${historiales != null}">
                                <h2>Listado de sus resevas</h2>
                                <hr>
                                <c:forEach items="${historiales}" var="historial">
                                    <div class="cardReservaPI">
                                        <h4><b>Fecha: </b>${historial.idReserva.fecha}</h4>
                                        <h4><b>Pago: </b>$ ${historial.precio}</h4>
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
        <script type="text/javascript" src="js/parqueaderoIndividual.js"></script>
    </body>
</html>