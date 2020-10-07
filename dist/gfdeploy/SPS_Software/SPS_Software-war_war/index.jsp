<%-- 
    Document   : index
    Created on : 22/03/2020, 02:47:10 PM
    Author     : Juan Pablo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <link rel="icon" type="image/gif" href="images/logo.jpg">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:300,400,700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="css/acceso.css"/>
        <title>Smart Parking System</title>
    </head>
    <body>
        <input type="hidden" id="inputPerfil" value="${perfil}"/>
        <script>
            if (document.getElementById("inputPerfil").value !== '') {
                window.open("./inicio", "_self");
            }
            document.getElementById("inputPerfil").remove();
        </script>
        <main>
            <section id="pnlJoin">
                <div id="contentJoin">
                    <img src="images/logo.jpg" alt="Logo"/>
                    <h1>Smart Parking System</h1>
                    <div id="signIn">
                        <h3>Iniciar Sesión</h3>
                        <h4 style="color:red;font-size:12px;">${error}</h4>
                        <form action="./ingresar" method="POST">
                            <div class="row">
                                <input type="email" name="email" placeholder="CORREO" required="on"/>
                                <input type="password" name="password" placeholder="CONTRASEÑA" required="on" minlength="8"/>
                            </div>
                            <input type="submit" value="INGRESAR" class="btn btnNegro"/>
                        </form>
                        <div id="extraSignIn">
                            <h3 class="underline">¿Olvido Contraseña?</h3>
                        </div>
                    </div>

                    <%--<div id="signWith">
                        <h3>Iniciar Sesión Con</h3>
                        <ul>
                            <li class="btn btnGoogle">GOOGLE</li>
                            <li class="btn btnTwitter">TWITTER</li>
                            <li class="btn btnFacebook">FACEBOOK</li>
                        </ul>
                    </div>--%>
                    <div id="insideRegister">
                        <h2>¿No tiene cuenta?</h2>
                        <h3 class="underline btnOtraPagina" data-tipo="1">REGISTRAR</h3>
                    </div>
                </div>
            </section>
            <section id="pnlImage">
                <img src="images/parking_background.jpg"/>
            </section>
        </main>
        <script src="js/accesoJS.js" type="text/javascript"></script>
    </body>
</html>