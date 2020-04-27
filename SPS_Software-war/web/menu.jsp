<%-- 
    Document   : menu
    Created on : 30/03/2020, 02:36:12 PM
    Author     : Juan Pablo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div id="panelInfo">
    <i onmouseenter="iconoFunction(true)" onmouseleave="iconoFunction(false)" id="iconoInfo" class="far fa-question-circle"></i>
    <div id="pnlInfo">
        <h2></h2>
        <p></p>
    </div>
</div>
<section id="pnlSide">
    <div id="contenido">
        <img src='images/<c:out value="${((perfil.class.name == 'com.sps.entity.Usuario')?'usuarioSeleccion':(perfil.class.name == 'com.sps.entity.Cliente')?'clienteSeleccion':'adminSeleccion')}"/>.png' alt="Logo Perfil"/>
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
                    <a href="./InicioServlet" id="enlaceInicio"><li><i class="fas fa-home"></i>INICIO</li></a>
                    <a href="./ReservaServlet" id="enlaceReserva"><li><i class="fas fa-receipt"></i>RESERVAR</li></a>
                    <a href="./ParqueaderosServlet" id="enlaceParqueadero"><li><i class="fas fa-parking"></i>PARQUEADEROS</li></a>
                    <a href="./HistorialServlet" id="enlaceHistorial"><li><i class="fas fa-history"></i>HISTORIAL</li></a>
                </c:when>    
                <c:when test="${perfil.getClass().name eq 'com.sps.entity.Cliente'}">
                    <a href="./#" id="enlaceInicio"><li><i class="fas fa-home"></i>INICIO</li></a>
                    <a href="./asignar.jsp" id="enlaceAsignar"><li><i class="fas fa-receipt"></i>RESERVAR</li></a>
                    <a href="./HistorialClienteServlet" id="enlaceHistorial"><li><i class="fas fa-history"></i>HISTORIAL</li></a>
                </c:when>  
                <c:when test="${perfil.getClass().name eq 'com.sps.entity.Administracion'}">
                    <a href="./#" id="enlaceInicio"><li><i class="fas fa-home"></i>INICIO</li></a>
                </c:when>  
                <c:otherwise>
                    <h2>SE HA PRESENTADO UN ERROR</h2>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
    <a href="./SalirServlet">
        <div id="salir">
            <i class="fas fa-sign-out-alt"></i>
            <h2>SALIR</h2>
        </div>
    </a>
</section>

<script src="js/menu.js"></script>