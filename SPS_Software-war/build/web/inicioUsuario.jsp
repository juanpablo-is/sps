<%-- 
    Document   : inicioUsuario
    Created on : 23/04/2020, 09:01:47 PM
    Author     : Juan Pablo
--%>

<%@include  file="menu.jsp" %>
<section id="pnlPrincipal">
    <div id="pnlInicio">
        <div id ="pnlReservasInicio">
            <h2>No hay reservas por mostrar</h2>
        </div>
        <div id="infoInicio">
            <div id="mapaInicio">
                <h2>MAPA PARQUEADEROS</h2>
                <div id="mapa">
                </div>
            </div>
            <!--                        <div id="graficoInicio">
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
    </div>-->
        </div>
    </div>
</section>
