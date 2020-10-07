<%-- 
    Document   : tiemporeal
    Created on : 18/05/2020, 06:26:00 PM
    Author     : Juan Pablo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <link rel="icon" type="image/gif" href="images/logo.jpg">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tiempo Real</title>
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
        <input id="namePage" type="hidden" value="4"/>
        <main>
            <%@include  file="menu.jsp" %>
            <section id="pnlPrincipal">
                <div class="divCompleto">
                    <div class="pnlDrop"><h3>CARROS</h3><i class="fas fa-chevron-up"></i></div>
                    <div id="pnlCarros">
                        <c:forEach items="${plazas}" var="plaza" varStatus="loop">
                            <c:choose>
                                <c:when test="${plaza[2] eq 'true'}">
                                    <c:choose>
                                        <c:when test="${plaza[1] != null}">
                                            <div class="cardTiempoReal">
                                                <img src="images/carros/<c:out value="<%= (int) (Math.random() * 6 + 1)%>"/>.png"/>
                                                <h3>${plaza[1]}</h3>
                                            </div>
                                        </c:when> 
                                        <c:otherwise>
                                            <div class="cardTiempoReal ocupado">
                                                <h3>${plaza[0]}</h3>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                                </c:when> 
                            </c:choose>
                        </c:forEach>
                    </div>

                    <div class="pnlDrop"><h3>MOTOS</h3><i class="fas fa-chevron-up"></i></div>
                    <div id="pnlMotos">
                        <c:forEach items="${plazas}" var="plaza" varStatus="loop">
                            <c:choose>
                                <c:when test="${plaza[2] eq 'false'}">
                                    <c:choose>
                                        <c:when test="${plaza[1] != null}">
                                            <div class="cardTiempoReal">
                                                <img src="images/motos/<c:out value="<%= (int) (Math.random() * 6 + 1)%>"/>.png"/>
                                                <h3>${plaza[1]}</h3>
                                            </div>
                                        </c:when> 
                                        <c:otherwise>
                                            <div class="cardTiempoReal ocupado">
                                                <h3>${plaza[0]}</h3>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                                </c:when> 
                            </c:choose>
                        </c:forEach>
                    </div>
                </div>
            </section>
        </main>
        <script type="text/javascript" src="js/tiemporeal.js"></script>
    </body>
</html>