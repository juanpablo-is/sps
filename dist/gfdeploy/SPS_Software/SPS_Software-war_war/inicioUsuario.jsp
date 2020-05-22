<%-- 
    Document   : inicioUsuario
    Created on : 23/04/2020, 09:01:47 PM
    Author     : Juan Pablo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include  file="menu.jsp" %>
<section id="pnlPrincipal">
    <div id="pnlInicio">
        <div id ="pnlReservasInicio">
            <c:choose>
                <c:when test="${reservas == null}">
                    <h2>No hay reservas por mostrar</h2>
                </c:when>    
                <c:otherwise>
                    <div id="pnlReservasGrid">
                        <c:forEach items="${reservas}" var="reserva">
                            <c:choose>
                                <c:when test="${reserva.estado eq 'true'}">
                                    <div class="cardReserva reservaActiva">
                                        <h3><em>Ubicación: </em>${reserva.idPlaza.idCliente.nombre}</h3>
                                        <h3><em>Dia: </em>${reserva.dia}</h3>
                                        <h3><em>Hora: </em>${reserva.entrada}</h3>
                                    </div>
                                </c:when>    
                                <c:otherwise>
                                    <div class="cardReserva">
                                        <h3><em>Ubicación: </em>${reserva.idPlaza.idCliente.nombre}</h3>
                                        <h3><em>Dia: </em>${reserva.dia}</h3>
                                        <h3><em>Hora: </em>${reserva.entrada}</h3>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
        <div id="infoInicio">
            <div id="mapaInicio">
                <h2>MAPA PARQUEADEROS</h2>
                <div id="mapa"></div>
            </div>
            <%--<div id="graficoInicio">
                <h2>USO DE RESERVAS</h2>
                <div id="grafico">
                    <c:choose>
                        <c:when test="${grafica.length() > 0}">
                            <img src="${grafica}"/>
                        </c:when>    
                        <c:otherwise>
                            <h2>NO HAY DATOS PARA GRAFICAR</h2>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>--%>
        </div>
    </div>
</section>
