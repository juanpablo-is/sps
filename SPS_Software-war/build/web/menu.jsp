<%-- 
    Document   : menu
    Created on : 30/03/2020, 02:36:12 PM
    Author     : Juan Pablo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<header>
    <div id="headerLogo">
        <a href="http://localhost:8080/SPS_Software-war/" style="display: contents;">
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
        <img src='images/<c:out value="${((perfil.class.name == 'com.sps.entity.Usuario')? (perfil.tipoVehiculo eq 'CARRO'?'usuarioCarro':'usuarioMoto') :(perfil.class.name == 'com.sps.entity.Cliente')?'clienteSeleccion':'adminSeleccion')}"/>.png' alt="Logo Perfil"/>
        <h2>
            <strong>
                <c:out value="${((perfil.class.name == 'com.sps.entity.Usuario')? 'PLACA' :(perfil.class.name == 'com.sps.entity.Cliente')?'LUGAR':'CEDULA')}: "/>
            </strong>
            <c:out value="${((perfil.class.name == 'com.sps.entity.Usuario')?perfil.placa:(perfil.class.name == 'com.sps.entity.Cliente')?perfil.direccion:'adminSeleccion')}"/>
        </h2>
    </div>
    <hr>
    <div id="menu">
        <ul>
            <c:choose>
                <c:when test="${perfil.getClass().name eq 'com.sps.entity.Usuario'}">
                    <a href="./inicio" id="enlaceInicio"><li><i class="fas fa-home"></i>INICIO</li></a>
                    <a href="./reservar" id="enlaceReserva"><li><i class="fas fa-receipt"></i>RESERVAR</li></a>
                    <a href="./historial" id="enlaceHistorial"><li><i class="fas fa-history"></i>HISTORIAL</li></a>
                    <a href="./parqueaderos" id="enlaceParqueadero"><li><i class="fas fa-parking"></i>PARQUEADEROS</li></a>
                </c:when>    
                <c:when test="${perfil.getClass().name eq 'com.sps.entity.Cliente'}">
                    <a href="./inicio" id="enlaceInicio"><li><i class="fas fa-home"></i>INICIO</li></a>
                    <a href="./reservar.jsp" id="enlaceAsignar"><li><i class="fas fa-receipt"></i>RESERVAR</li></a>
                    <a href="./historial" id="enlaceHistorial"><li><i class="fas fa-history"></i>HISTORIAL</li></a>
                </c:when>  
                <c:when test="${perfil.getClass().name eq 'com.sps.entity.Movilidad'}">
                    <a href="./#" id="enlaceInicio"><li><i class="fas fa-home"></i>INICIO</li></a>
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
