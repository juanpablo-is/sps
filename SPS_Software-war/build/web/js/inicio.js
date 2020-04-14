var namePage = document.getElementById("namePage").value;
var listaMenu = document.querySelector("#menu ul").children;
let iconoInfo = document.getElementById("iconoInfo");
let textoInfo = ["En esta pagina puede ver un detalle de sus reservas mas recientes, ademas de un mapa de su ubicación y un grafico donde representa un historial de su grafica por mes durante este año",
    "Llenando el formulario se hará un reserva en el respectivo parqueadero, todos los campos son requeridos.",
    "Se listará el total de los parqueaderos que trabajan con nosotros y ademas de una detallada descripción.",
    "Podra visualizar un historial de sus reservas anteriormente agregadas."];
var page;
for (var i = 0; i < listaMenu.length; i++) {
    var listaLi = listaMenu[i].firstElementChild.innerText;
    if (i === (namePage - 1)) {
        page = listaLi;
        document.getElementById("pnlInfo").children[0].innerHTML = listaLi;
        document.getElementById("pnlInfo").children[1].innerHTML = textoInfo[i];
        listaMenu[i].firstElementChild.classList.add("seleccionPagina");
    }
}

function iconoFunction(s) {
    document.getElementById("pnlInfo").style.display = s ? "block" : "none";
}

if (page === "RESERVAR") {
    document.getElementById('dia').min = new Date(new Date().getTime() - new Date().getTimezoneOffset() * 60000).toISOString().split("T")[0];
    document.getElementById('dia').value = new Date(new Date().getTime() - new Date().getTimezoneOffset() * 60000).toISOString().split("T")[0];
}

if (page === "INICIO") {
    if (navigator.geolocation) {
        let latitude;
        let longitude;
        navigator.geolocation.getCurrentPosition(
                function (ubicacion) {
                    latitude = ubicacion.coords.latitude;
                    longitude = ubicacion.coords.longitude;
                    console.log(`longitud: ${ latitude } | latitud: ${ longitude }`);
                    function addSVGMarkers(map) {
                        var svgMarkup = '<svg  width="28" height="28" xmlns="http://www.w3.org/2000/svg">' +
                                '<circle stroke="red" fill="red" cx="14" cy="14" r="13" />' +
                                '<text x="14" y="20" font-size="12pt" font-family="Arial" font-weight="bold" ' +
                                'text-anchor="middle" fill="white" >☝</text></svg>';
                        var bearsIcon = new H.map.Icon(
                                svgMarkup.replace('${FILL}', 'blue').replace('${STROKE}', 'red'))
                                ,
                                bearsMarker = new H.map.Marker({lat: latitude, lng: longitude},
                                        {icon: bearsIcon});
                        bearsMarker.setData('<div>Tu Ubicación</div><div><br><b>Latitud:</b>' + latitude + '<br><b>Longitud: </b>' + longitude + '</div>');
                        bearsMarker.addEventListener('tap', function (evt) {
                            var bubble = new H.ui.InfoBubble(evt.target.getGeometry(), {
                                content: evt.target.getData()
                            });
                            ui.addBubble(bubble);
                        }, false);
                        map.addObject(bearsMarker);
                    }

                    var platform = new H.service.Platform({
                        apikey: 'mkmh-D8wPyFPLhRuQd5QiEnygn8xoDsJ4o4Z4V2G-_4'
                    });
                    var defaultLayers = platform.createDefaultLayers();
                    var map = new H.Map(document.getElementById('mapa'),
                            defaultLayers.vector.normal.map, {
                                center: {lat: latitude, lng: longitude},
                                zoom: 15,
                                pixelRatio: window.devicePixelRatio || 1
                            });
                    document.getElementById('mapa').addEventListener('resize', () => map.getViewPort().resize());
                    var behavior = new H.mapevents.Behavior(new H.mapevents.MapEvents(map));
                    var ui = H.ui.UI.createDefault(map, defaultLayers);
                    addSVGMarkers(map);
                },
                function (objPositionError) {
                    let mapa = document.getElementById("mapa");
                    switch (objPositionError.code)
                    {
                        case objPositionError.PERMISSION_DENIED:
                            mapa.innerHTML = "No se ha permitido el acceso a la posición del usuario.";
                            break;
                        case objPositionError.POSITION_UNAVAILABLE:
                            mapa.innerHTML = "No se ha podido acceder a la información de su posición.";
                            break;
                        case objPositionError.TIMEOUT:
                            mapa.innerHTML = "El servicio ha tardado demasiado tiempo en responder.";
                            break;
                        default:
                            mapa.innerHTML = "Error desconocido.";
                    }
                });
    } else {

    }
}