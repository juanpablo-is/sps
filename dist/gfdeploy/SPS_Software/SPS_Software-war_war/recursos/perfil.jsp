<%-- 
    Document   : perfil
    Created on : 11/05/2020, 03:29:40 PM
    Author     : Juan Pablo
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<main id="pnlPerfil">
    <h2>Información Personal</h2>
    <div class="row">
        <div class="column">
            <label for="nombre">Nombre</label>
            <input type="text" id="nombre" name="nombre" placeholder="Ingrese nombre" value="${persona.nombre}" required="true"/>
        </div>
    </div>
    <div class="row">
        <div class="column">
            <label for="cedula">Cedula</label>
            <input type="number" id="cedula" name="cedula" placeholder="Ingrese la cedula" value="${persona.cedula}" required="true"/>
        </div>
        <div class="column">
            <label for="telefono">Teléfono</label>
            <input type="number" id="telefono" name="telefono" placeholder="Ingrese el teléfono" max="10" min="10" value="${persona.telefono}" required="true"/>
        </div>
    </div>
    <input onclick="informacionPersonal()" id="btnPersonal" type="submit"/>
    <hr>
    <h2>Información Acceso</h2>
    <div class="row">
        <div class="column">
            <label for="correo">Correo</label>
            <input type="email" id="correo" placeholder="Ingrese correo" value="${persona.correo}" required="true"/>
        </div>
    </div>
    <div class="row">
        <div class="column">
            <label for="contrasenia">Contraseña</label>
            <input type="password" id="contrasenia" placeholder="Ingrese contraseña" value="***********" required="true"/>
        </div>
    </div>
    <input id="botonAcceso" type="submit"/>
    <hr>
    <h2>Información Cuenta</h2>
    <c:choose>
        <c:when test="${perfil.getClass().name eq 'com.sps.entity.Usuario'}">
            <h2>usuario</h2>
        </c:when>    
        <c:when test="${perfil.getClass().name eq 'com.sps.entity.Cliente'}">
            <h2>cliente</h2>
        </c:when>
        <c:when test="${perfil.getClass().name eq 'com.sps.entity.Movilidad'}">
            <h2>movilidad</h2>
        </c:when>
    </c:choose>
    <h2>${perfil.getClass().name}</h2>
</main>