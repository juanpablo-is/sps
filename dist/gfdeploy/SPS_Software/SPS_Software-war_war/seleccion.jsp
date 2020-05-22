<%-- 
    Document   : seleccion
    Created on : 24/03/2020, 07:11:39 PM
    Author     : Juan Pablo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://fonts.googleapis.com/css?family=PT+Sans:400,700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="css/seleccion.css"/>
        <title>Selección Acceso</title>
    </head>
    <body>
        <input type="hidden" id="inputPerfil" value="${persona}"/>
        <script>
            if (document.getElementById("inputPerfil").value === '') {
                window.open("http://localhost:8080/SPS_Software-war/", "_self");
            }
            document.getElementById("inputPerfil").remove();
        </script>
        <main>
            <div class="contenedor">
                <div id="img">
                    <img src="images/logo.jpg" alt="Logo"/>
                    <h1>Smart Parking System</h1>
                </div>
                <h2>Seleccione un perfil a continuación:</h2>
                <section id="seccionPerfil">
                    <c:forEach items="${perfiles}" var="perfil">
                        <div class="cardPerfil">
                            <input type="hidden" value="<c:out value="${((perfil.getClass().name == 'com.sps.entity.Usuario')?'U-'+=perfil.placa:(perfil.getClass().name eq 'com.sps.entity.Cliente')?'C-'+=perfil.id:'A-'+=perfil.id)}"/>"/>

                            <div id="topCard">
                                <img src='images/<c:out value="${((perfil.class.name == 'com.sps.entity.Usuario')? (perfil.tipoVehiculo eq 'true'?'usuarioCarro':'usuarioMoto') :(perfil.class.name == 'com.sps.entity.Cliente')?'clienteSeleccion':'adminSeleccion')}"/>.png' alt="Logo Perfil"/>
                                <h3>
                                    ${((perfil.class.name == 'com.sps.entity.Usuario')? 'USUARIO' :(perfil.class.name == 'com.sps.entity.Cliente')?'CLIENTE':'MOVILIDAD')}
                                </h3>
                            </div>
                            <hr>
                            <div id="textoCard">
                                <p><strong>${((perfil.class.name == 'com.sps.entity.Usuario')? 'PLACA' :(perfil.class.name == 'com.sps.entity.Cliente')?'NOMBRE':'LUGAR')}: 
                                    </strong>${((perfil.class.name == 'com.sps.entity.Usuario')? perfil.placa :(perfil.class.name == 'com.sps.entity.Cliente')?perfil.nombre:perfil.empresa)}
                                </p>
                            </div>
                        </div>
                    </c:forEach>
                </section>
                <form action="./seleccion" method="POST">
                    <input type="hidden" id="valorSeleccion" name="id"/>
                    <input id="btnSubmit" type="submit" value="CONTINUAR" disabled="disabled"/>
                </form>
            </div>
        </main>
        <script src="js/seleccionJS.js" type="text/javascript"></script>
    </body>
</html>