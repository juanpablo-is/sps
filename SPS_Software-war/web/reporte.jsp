<%-- 
    Document   : reporte
    Created on : 24/05/2020, 07:50:32 PM
    Author     : Juan Pablo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <link rel="icon" type="image/gif" href="images/logo.jpg">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reporte</title>
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
        <c:choose>
            <c:when test="${perfil.getClass().name eq 'com.sps.entity.Cliente'}">
                <input id="namePage" type="hidden" value="5"/>
            </c:when>
            <c:when test="${perfil.getClass().name eq 'com.sps.entity.Movilidad'}">
                <input id="namePage" type="hidden" value="2"/>
            </c:when>
        </c:choose>
        <main>
            <%@include  file="menu.jsp" %>
            <section id="pnlPrincipal">
                <div id="pnlReportesMovilidad">
                    <c:choose>
                        <c:when test="${reportes.size() > 0}">
                            <div class="headerReporte">
                                <h2 class="contenidoReporte">CONTENIDO</h2>
                                <h2>FECHA</h2>
                                <h2>USUARIO</h2>
                                <h2>CLIENTE</h2>
                            </div>
                            <c:forEach items="${reportes}" var="reporte" varStatus="loop">
                                <div class="reporteCard <c:out value="${(reporte.estado eq 'true')?'bloqueada':''}"/>">
                                    <div class="contenidoReporte">
                                        <h2>${reporte.titulo}</h2>
                                        <p>${reporte.texto}</p>
                                    </div>
                                    <h2>${reporte.fecha}</h2>
                                    <div class="usuarioReporte">
                                        <h2>${reporte.idUsuario.idPersona.nombre}</h2>
                                        <h3>${reporte.idUsuario.placa}</h3>
                                    </div>
                                    <div class="clienteReporte">
                                        <h2>${reporte.idCliente.nombre}</h2>
                                        <h3>${reporte.idCliente.idPersona.nombre}</h3>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <h3 id="noReportes">NO HAY REPORTES</h3>
                        </c:otherwise>
                    </c:choose>
                </div>
            </section>
        </main>
        <script type="text/javascript" src="js/inicioUsuario.js"></script>
    </body>
</html>