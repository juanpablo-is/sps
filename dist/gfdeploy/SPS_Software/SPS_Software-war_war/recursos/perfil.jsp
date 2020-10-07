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
            <input type="number" id="cedula" name="cedula" placeholder="Ingrese la cedula" value="${persona.cedula}" disabled="true"/>
        </div>
        <div class="column">
            <label for="telefono">Teléfono</label>
            <input type="number" id="telefono" name="telefono" placeholder="Ingrese el teléfono" max="10" min="10" value="${persona.telefono}" required="true"/>
        </div>
    </div>
    <input onclick="informacionModificacion('personal')" id="btnPersonal" type="submit"/>
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
            <input type="password" id="contrasenia" placeholder="Ingrese contraseña para cambiar" required="true"/>
        </div>
    </div>
    <input onclick="informacionModificacion('acceso')" type="submit"/>
    <hr>
    <h2>Información Cuenta</h2>
    <c:choose>
        <c:when test="${perfil.getClass().name eq 'com.sps.entity.Usuario'}">
            <div class="row">
                <div class="column">
                    <label for="placa">Placa</label>
                    <input type="text" id="placa" name="placa" value="${perfil.placa}" disabled="true"/>
                </div>
                <div class="column">
                    <label for="marca">Marca</label>
                    <input type="texto" id="marca" name="marca" placeholder="Ingrese la marca del vehiculo" value="${perfil.marca}" required="true"/>
                </div>
            </div>
            <input onclick="informacionPerfil('usuario')" type="submit"/>
        </c:when>
        <c:when test="${perfil.getClass().name eq 'com.sps.entity.Cliente'}">
            <div class="row">
                <div class="column">
                    <label for="id">ID</label>
                    <input type="text" id="id" name="id" value="${perfil.id}" disabled="true"/>
                </div>
                <div class="column">
                    <label for="precio">Precio</label>
                    <input type="number" id="precio" name="precio" placeholder="Ingrese el precio del parqueadero" value="${perfil.precio}" required="true"/>
                </div>
            </div>
            <div class="row">
                <div class="column">
                    <label for="nombreCliente">Nombre</label>
                    <input type="text" id="nombreCliente" name="nombreCliente" placeholder="Ingrese el nombre del parqueadero" value="${perfil.nombre}" required="true"/>
                </div>
                <div class="column">
                    <label for="direccion">Direccion</label>
                    <input type="texto" id="direccion" name="direccion" placeholder="Ingrese la dirección" value="${perfil.direccion}" required="true"/>
                </div>
            </div>
            <div class="row">
                <div class="column">
                    <label for="entrada">Hora entrada</label>
                    <input type="text" id="entrada" name="entrada" minlength="5" maxlength="5" placeholder="Ingrese la hora de apertura" value="${perfil.inicio}" required="true"/>
                </div>
                <div class="column">
                    <label for="salida">Hora salida</label>
                    <input type="texto" id="salida" name="salida" placeholder="Ingrese la hora de cierre" value="${perfil.fin}" required="true"/>
                </div>
            </div>
            <input onclick="informacionPerfil('cliente')" type="submit"/>
        </c:when>
        <c:when test="${perfil.getClass().name eq 'com.sps.entity.Movilidad'}">
            <div class="row">
                <div class="column">
                    <label for="sede">Sede</label>
                    <input type="text" id="sede" name="empresa" placeholder="Ingrese el nombre de la sede" value="${perfil.empresa}" required="true"/>
                </div>
                <div class="column">
                    <label for="id">ID</label>
                    <input type="texto" id="id" name="id" placeholder="Ingrese id de la empresa" value="${perfil.id}" disabled="true"/>
                </div>
            </div>
            <input onclick="informacionPerfil('movilidad')" type="submit"/>
        </c:when>
    </c:choose>
</main>