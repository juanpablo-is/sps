<%-- 
    Document   : seleccion
    Created on : 24/03/2020, 07:11:39 PM
    Author     : Juan Pablo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://fonts.googleapis.com/css?family=PT+Sans:400,700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="css/seleccion.css"/>
        <title>Selección Acceso</title>
    </head>
    <body>
        <main>
            <div class="contenedor">
                <div id="img">
                    <img src="images/logo.jpg" alt="Logo"/>
                    <h1>Smart Parking System</h1>
                </div>
                <h2>Seleccione un perfil a continuación:</h2>
                <section id="seccionPerfil">
                    <c:forEach items="${perfil}" var="dato">
                        <div class="cardPerfil">
                            <input type="hidden" value="<c:out value="${((dato[0] == 'USUARIO')?'U-':(dato[0] == 'CLIENTE')?'C-':'A-')}${dato[3]}"/>"/>

                            <div id="topCard">
                                <img src="images/<c:out value="${((dato[0] == 'USUARIO')?'usuarioSeleccion':(dato[0] == 'CLIENTE')?'clienteSeleccion':'adminSeleccion')}"/>.png" alt="Logo Perfil"/>
                                <h3>${dato[0]}</h3>
                            </div>
                            <hr>
                            <div id="textoCard">
                                <p><strong>${dato[1]}: </strong>${dato[2]}</p>
                            </div>
                        </div>
                    </c:forEach>
                </section>
                <form action="./SeleccionServlet" method="POST">
                    <input type="hidden" id="valorSeleccion" name="id"/>
                    <input id="btnSubmit" type="submit" value="CONTINUAR" disabled="disabled"/>
                </form>
            </div>
        </main>
        <script src="js/seleccionJS.js" type="text/javascript"></script>
    </body>
</html>