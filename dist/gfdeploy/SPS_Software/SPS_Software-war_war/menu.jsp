<%-- 
    Document   : menu
    Created on : 30/03/2020, 02:36:12 PM
    Author     : Juan Pablo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<header>
    <input type="hidden" id="inputPerfil" value="${perfil}"/>
    <script>
        if (document.getElementById("inputPerfil").value === '') {
            window.open("./", "_self");
        }
        document.getElementById("inputPerfil").remove();
    </script>
    <div id="headerLogo">
        <a href="./" style="display: contents;">
            <img src="images/logo.jpg" alt="Logo"/>
            <h2>SPSystem</h2>
        </a>
    </div>
    <h2 id="textoBienvenida">BIENVENIDO ${persona.nombre}</h2>
    <div id="panelInfo">
        <i onclick="iconoAccount()" id="iconoAccount" class="fas fa-user-circle"></i>
        <i onmouseenter="iconoFunction(true)" onmouseleave="iconoFunction(false)" id="iconoInfo" class="far fa-market"></i>
        <div id="pnlInfo">
            <h2></h2>
            <p></p>
        </div>
    </div>
</header>
<section id="pnlSide">
    <div id="contenido">
        <img src='images/<c:out value="${((perfil.class.name == 'com.sps.entity.Usuario')? (perfil.tipoVehiculo eq 'true'?'usuarioCarro':'usuarioMoto') :(perfil.class.name == 'com.sps.entity.Cliente')?'clienteSeleccion':'adminSeleccion')}"/>.png' alt="Logo Perfil"/>
        <h2>
            <strong>
                <c:out value="${((perfil.class.name == 'com.sps.entity.Usuario')? 'PLACA' :'LUGAR')}: "/>
            </strong>
            <c:out value="${((perfil.class.name == 'com.sps.entity.Usuario')?perfil.placa:(perfil.class.name == 'com.sps.entity.Cliente')?perfil.nombre:perfil.empresa)}"/>
        </h2>
    </div>
    <hr>
    <div id="menu">
        <ul>
            <c:choose>
                <c:when test="${perfil.getClass().name eq 'com.sps.entity.Usuario'}">
                    <a href="./inicio"><li><i class="fas fa-home"></i>INICIO</li></a>
                    <a href="./reservar"><li><i class="fas fa-receipt"></i>RESERVAR</li></a>
                    <a href="./historial"><li><i class="fas fa-history"></i>HISTORIAL</li></a>
                    <a href="./parqueaderos"><li><i class="fas fa-parking"></i>PARQUEADEROS</li></a>
                </c:when>
                <c:when test="${perfil.getClass().name eq 'com.sps.entity.Cliente'}">
                    <a href="./inicio"><li><i class="fas fa-home"></i>INICIO</li></a>
                    <a href="./reservar.jsp"><li><i class="fas fa-receipt"></i>RESERVAR</li></a>
                    <a href="./historial"><li><i class="fas fa-history"></i>HISTORIAL</li></a>
                    <a href="./tiemporeal"><li><i class="fas fa-stopwatch"></i>TIEMPO REAL</li></a>
                </c:when>
                <c:when test="${perfil.getClass().name eq 'com.sps.entity.Movilidad'}">
                    <a style="display: none;" href="./inicio"><li><i class="fas fa-home"></i>INICIO</li></a>
                    <a href="./reportes"><li><i class="fas fa-envelope-open-text"></i>REPORTE</li></a>
                </c:when>
                <c:otherwise>
                    <h2>SE HA PRESENTADO UN ERROR</h2>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
    <a href="./salir">
        <div id="salir">
            <i class="fas fa-sign-out-alt"></i>
            <h2>SALIR</h2>
        </div>
    </a>
</section>
<script src="js/menu.js"></script>
