<%-- 
    Document   : registro
    Created on : 22/03/2020, 07:20:03 PM
    Author     : Juan Pablo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <link rel="icon" type="image/gif" href="images/logo.jpg">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:300,400,700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="css/acceso.css"/>
        <title>Registro SPS</title>
    </head>
    <body>
        <main>
            <section id="pnlJoin">
                <div id="contentJoin">
                    <div id="img">
                        <img src="images/logo.jpg" alt="Logo"/>
                        <h1>Smart Parking System</h1>
                    </div>
                    <div id="signIn">
                        <h3>REGISTRO</h3>
                        <h4 style="color:red;font-size:12px;">${error}</h4>
                        <form action="./registro" method="POST" name="registro">
                            <div class="row">
                                <input type="email" class="" placeholder="CORREO" name="email" required value="${persona.correo}"/>
                                <input type="password" class="" placeholder="CONTRASEÑA" name="password" minlength="8" required />
                            </div>
                            <div class="row">
                                <input type="text" style="text-transform: capitalize" class="" placeholder="NOMBRE" name="name" required value="${persona.nombre}"/>
                            </div>
                            <div class="row">
                                <input type="number" class="" placeholder="CEDULA" name="idPerson" required value="${persona.cedula}"/>
                                <input type="number" class="" minlength="10" maxlength="10" placeholder="TELÉFONO" name="phone" value="${persona.telefono}"/>
                            </div>
                            <div class="row">
                                <input id="usuario" type="radio" class="" name="radio" value="Usuario" required="true"/>
                                <label for="usuario">Usuario</label><br>
                                <input id="cliente" type="radio" class="" name="radio" value="Cliente" required="true"/>
                                <label for="cliente">Cliente</label><br>
                                <input id="admin" type="radio" class="" name="radio" value="Movilidad" required="true"/>
                                <label for="admin">Administración</label>
                            </div>
                            <div id="addRow"></div>
                            <input type="submit" value="REGISTRARSE" class="btn btnNegro"/>
                        </form>
                    </div>
                    <div id="insideRegister">
                        <h2>¿Ya Tiene cuenta?</h2>
                        <h3 class="underline btnOtraPagina" data-tipo="2">INICIAR SESIÓN</h3>
                    </div>
                </div>
            </section>
            <section id="pnlImage">
                <img src="images/parking_background_registro.jpg"/>
            </section>
        </main>
        <script src="js/accesoJS.js" type="text/javascript"></script>
    </body>
</html>