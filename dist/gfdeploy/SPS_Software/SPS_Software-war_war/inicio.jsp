<%-- 
    Document   : inicio
    Created on : 24/03/2020, 07:07:15 PM
    Author     : Juan Pablo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bienvenido ${persona.nombre}</title>
        <script src="js/all.min.js"></script>
        <link rel="stylesheet" href="css/inicio.css"/>
    </head>
    <body>
        <header>
            <div id="headerLogo">
                <img src="images/logo.jpg" alt="Logo"/>
                <h2>SPSystem</h2>
            </div>
            <h2 id="textoBienvenida">BIENVENIDO ${persona.nombre}</h2>
        </header>

        <main>
            <section id="pnlSide">
                <div id="contenido">
                    <img src="images/<c:out value="${((perfil.class.name == 'com.sps.entity.Usuario')?'usuarioSeleccion':(perfil.class.name == 'com.sps.entity.Cliente')?'clienteSeleccion':'adminSeleccion')}"/>.png" alt="Logo Perfil"/>
                    <h2>
                        <strong>
                            <c:out value="${((perfil.class.name == 'com.sps.entity.Usuario')? 'PLACA: ' :(perfil.class.name == 'com.sps.entity.Cliente')?'clienteSeleccion':'adminSeleccion')}"/>
                        </strong>
                        <c:out value="${((perfil.class.name == 'com.sps.entity.Usuario')?perfil.placa:(perfil.class.name == 'com.sps.entity.Cliente')?'clienteSeleccion':'adminSeleccion')}"/>
                    </h2>
                </div>
                <hr>
                <div id="salir">
                    <i class="fas fa-sign-out-alt"></i>
                    <h2>SALIR</h2>
                </div>
            </section>
            <section id="pnlPrincipal">
                <hr>                
            </section>
        </main>
    </body>
</html>
