<%-- 
    Document   : inicioUsuario
    Created on : 23/04/2020, 09:01:47 PM
    Author     : Juan Pablo
--%>

<%@include  file="menu.jsp" %>
<section id="pnlPrincipal">
    <!--<div id="pnlInicioCliente">-->
    <section id="infoCliente">
        <div id="pnlTopInfo">
            <div id="tarjetasInfo">
                <div class="tarjetaInfo">
                    <i class="fas fa-dollar-sign"></i>
                    <div>
                        <h3>Dinero</h3>
                        <h2><c:out value="${precio != null ? precio : 0}"/></h2>
                    </div>
                </div>
                <div class="tarjetaInfo">
                    <i class="fas fa-user-plus"></i>
                    <div>
                        <h3>Personas</h3>
                        <h2><c:out value="${cantidadPersonas > 0 ? cantidadPersonas : 0}"/></h2>
                    </div>
                </div>
                <div class="tarjetaInfo">
                    <i class="fas fa-sticky-note"></i>
                    <div>
                        <h3>Reservas</h3>
                        <h2><c:out value="${cantidadReservas > 0 ? cantidadReservas : 0}"/></h2>
                    </div>
                </div>
                <!--<div class="tarjetaInfo">
                    <i class="fas fa-dollar-sign"></i>
                    <div>
                        <h3></h3>
                        <h2></h2>
                    </div>
                </div>-->
            </div>
            <div id="reportesInfo">
                <c:choose>
                    <c:when test="${reportes.size() > 0}">
                        <div class="divCompleto">
                            <h3>LISTADO REPORTES</h3>
                            <hr>
                            <c:forEach items="${reportes}" var="reporte" varStatus="loop">
                                <div class='cardReporte <c:out value="${reporte.estado eq 'true' ? 'desocupado' : ''}"/>'>
                                    <h4>${reporte.titulo}</h4>
                                    <h4><b>Por: </b>${reporte.idUsuario.idPersona.nombre}</h4>
                                </div>
                            </c:forEach>
                        </div>
                    </c:when>  
                    <c:otherwise>
                        <h3 class="centrado">NO HAY REPORTES</h3>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <div id="pnlGrafico">
            <img src="${grafico}"/>
        </div>
    </section>
    <section id="usuariosCliente">
        <c:choose>
            <c:when test="${reservas.size() > 0}">
                <div>
                    <h3>Listado usuarios</h3>
                    <hr>
                    <c:forEach items="${reservas}" var="reserva" varStatus="loop">
                        <div class='cardUsuario <c:out value="${reserva.estado eq 'true' ? '' : 'desocupado'}"/>'>
                            <img src="images/usuarioCarro.png" alt="Icono tipo vehiculo"/>
                            <div>
                                <h4>${reserva.idUsuario.idPersona.nombre}</h4>
                                <h4><b>Placa: </b>${reserva.idUsuario.placa}</h4>
                                <h4><b>Plaza: </b>${reserva.idPlaza.id.split("-")[0]}</h4>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:when>  
            <c:otherwise>
                <h3 class="centrado">No hay usuarios de hoy todavia</h3>
            </c:otherwise>
        </c:choose>
    </section>
    <!--</div>-->
</div>
</section>
