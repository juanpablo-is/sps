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
                <div id="pnlParqueaderos">
                    <c:forEach items="${parqueaderos}" var="parqueadero" varStatus="loop">
                        <a href="./parqueadero?id=${parqueadero.id}"><div class="cardParqueaderoInterno">
                                <img src="images/imagen_parking_<c:out value="<%= (int) (Math.random() * 5 + 1)%>"/>.jpg"/>
                                <div>
                                    <h3><span>Ubicación: </span>${parqueadero.direccion}</h3>
                                    <h3><span>Nombre: </span>${parqueadero.nombre}</h3>
                                    <h3><span>Precio/min :</span>${parqueadero.precio}</h3>
                                </div>
                            </div>
                        </a>
                    </c:forEach>
                </div>
            </section>
        </main>
        <script type="text/javascript" src="js/inicioUsuario.js"></script>
    </body>
</html>