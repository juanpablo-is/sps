function reservaPI(idCliente) {
    let reservaDiv = document.getElementById("reservarPI");
    reservaDiv.innerHTML = "";

    let form = document.createElement("form");
    form.setAttribute('method', 'post');
    form.style.width = "100%";
    form.setAttribute('action', './reservar');

    let divInputs = document.createElement("div");
    divInputs.classList.add("fila");
    let labelDay = document.createElement("label");
    labelDay.innerHTML = "Fecha";
    labelDay.style.flex = "1";
    let inputDay = document.createElement("input");
    inputDay.setAttribute('type', 'datetime-local');
    inputDay.setAttribute('name', 'fecha');
    inputDay.setAttribute('required', 'on');
    inputDay.min = new Date(new Date().getTime() - new Date().getTimezoneOffset() * 60000).toISOString().split(".")[0].substring(0, 16);
    inputDay.value = new Date(new Date().getTime() - new Date().getTimezoneOffset() * 60000).toISOString().split(".")[0].substring(0, 16);

    divInputs.appendChild(labelDay);
    divInputs.appendChild(inputDay);

    let divCheck = document.createElement("div");
    divCheck.classList.add("fila");

    let labelCubierto = document.createElement("label");
    labelCubierto.classList.add("columna");
    labelCubierto.innerHTML = "Cubierto";

    let divInputCheck = document.createElement("div");
    divInputCheck.classList.add("fila");
    let inputSi = document.createElement("input");
    inputSi.setAttribute('type', 'radio');
    inputSi.setAttribute('name', 'cubierto');
    inputSi.setAttribute('value', 'si');
    let inputNo = document.createElement("input");
    inputNo.setAttribute('type', 'radio');
    inputNo.setAttribute('name', 'cubierto');
    inputNo.setAttribute('value', 'no');
    inputNo.setAttribute('checked', 'on');
    let labelSi = document.createElement("label");
    labelSi.classList.add("columna");
    labelSi.innerHTML = "SI";
    let labelNo = document.createElement("label");
    labelNo.classList.add("columna");
    labelNo.innerHTML = "NO";

    divInputCheck.appendChild(labelSi);
    divInputCheck.appendChild(inputSi);
    divInputCheck.appendChild(labelNo);
    divInputCheck.appendChild(inputNo);

    divCheck.appendChild(labelCubierto);
    divCheck.appendChild(divInputCheck);

    let inputCliente = document.createElement("input");
    inputCliente.setAttribute('name', 'idCliente');
    inputCliente.setAttribute('value', idCliente);
    inputCliente.setAttribute('type', 'hidden');

    let inputSubmit = document.createElement("input");
    inputSubmit.setAttribute('type', 'submit');
    inputSubmit.setAttribute('value', 'RESERVAR');
    inputSubmit.setAttribute('name', 'proceso');

    form.appendChild(divInputs);
    form.appendChild(divCheck);
    form.appendChild(inputSubmit);
    form.appendChild(inputCliente);
    reservaDiv.appendChild(form);
}

function reportePI(idCliente) {
    let reservaDiv = document.getElementById("reservarPI");
    reservaDiv.innerHTML = "";

    let form = document.createElement("form");
    form.setAttribute('method', 'post');
    form.setAttribute('id', 'formSoporte');
    form.setAttribute('action', './reportes');

    let divInputs = document.createElement("div");
    divInputs.classList.add("fila");
    
    let divTitulo = document.createElement("div");
    divTitulo.classList.add("fila");

    let inputReporte = document.createElement("textarea");
    inputReporte.setAttribute('name', 'reporteTexto');
    inputReporte.setAttribute('required', 'on');
    inputReporte.setAttribute('minlength', '30');
    inputReporte.setAttribute('form', 'formSoporte');
    divInputs.appendChild(inputReporte);

    let inputCliente = document.createElement("input");
    inputCliente.setAttribute('name', 'idCliente');
    inputCliente.setAttribute('value', idCliente);
    inputCliente.setAttribute('type', 'hidden');
    
    let inputTitulo = document.createElement("input");
    inputTitulo.setAttribute('name', 'titulo');
    inputTitulo.setAttribute('type', 'text');
    inputTitulo.setAttribute('minlength', '10');
    inputTitulo.style.width = "100%";
    divTitulo.appendChild(inputTitulo);

    let inputSubmit = document.createElement("input");
    inputSubmit.setAttribute('type', 'submit');
    inputSubmit.setAttribute('value', 'REPORTAR');
    inputSubmit.setAttribute('name', 'reportePI');
    
    form.appendChild(divTitulo);
    form.appendChild(divInputs);
    form.appendChild(inputSubmit);
    form.appendChild(inputCliente);
    reservaDiv.appendChild(form);
    form.addEventListener('submit', function (e) {
        e.preventDefault();
        let data = `idCliente=${idCliente}&reporteTexto=${inputReporte.value}&titulo=${inputTitulo.value}&opcion=REPORTAR`;
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
                if (this.responseText === "true") {
                    notificacion("El reporte se ha enviado a revisión, gracias.");
                    location.reload();
                } else {
                    notificacion("Hubo un problema, intente de nuevo mas tarde");
                }
            }
        };
        xhttp.open("POST", "reportes", true);
        xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        xhttp.send(data);
    });
}


let latitude = "4.6085845";
let longitude = "-74.0761647";
function mapaPI(map) {
    var svgMarkup = '<svg  width="28" height="28" xmlns="http://www.w3.org/2000/svg">' +
            '<circle stroke="red" fill="red" cx="14" cy="14" r="13" />' +
            '<text x="14" y="20" font-size="12pt" font-family="Arial" font-weight="bold" ' +
            'text-anchor="middle" fill="white" >☝</text></svg>';
    var bearsIcon = new H.map.Icon(
            svgMarkup.replace('${FILL}', 'blue').replace('${STROKE}', 'red')),
            bearsMarker = new H.map.Marker({lat: latitude, lng: longitude}, {icon: bearsIcon});
    bearsMarker.setData(`<div>Tu Ubicación</div><div><br><b>Latitud:</b>${latitude}<br><b>Longitud: </b>${longitude}</div>`);
    bearsMarker.addEventListener('tap', function (evt) {
        var bubble = new H.ui.InfoBubble(evt.target.getGeometry(), {
            content: evt.target.getData()
        });
        ui.addBubble(bubble);
    }, false);
    map.addObject(bearsMarker);
    let marker = new H.map.Marker({lat: cliente.latitud, lng: cliente.longitud});
    marker.setData(`<div><b>${cliente.nombre}</b></div><div><br><b>Precio:</b>$ ${cliente.precio}<br><b>Dirección:</b>${cliente.direccion}</div><a href="./parqueadero?id=${cliente.id}">RESERVAR</a>`);
    marker.addEventListener('tap', function (evt) {
        var bubble = new H.ui.InfoBubble(evt.target.getGeometry(), {
            content: evt.target.getData()
        });
        ui.addBubble(bubble);
    }, false);
    map.addObject(marker);
}

function calculateRouteFromAtoB(platform) {
    var router = platform.getRoutingService(),
            routeRequestParams = {
                mode: 'fastest;car',
                representation: 'display',
                routeattributes: 'waypoints,summary,shape,legs',
                maneuverattributes: 'direction,action',
                waypoint0: `${latitude},${longitude}`, // Brandenburg Gate
                waypoint1: `${cliente.latitud},${cliente.longitud}`  // Friedrichstraße Railway Station
            };


    router.calculateRoute(
            routeRequestParams,
            onSuccess,
            onError
            );
}

function onSuccess(result) {
    var route = result.response.route[0];
    addRouteShapeToMap(route);
    addManueversToMap(route);

}

function onError(error) {
    alert('Can\'t reach the remote server');
}

var mapContainer = document.getElementById('mapaParqueaderoIndividual')

var platform = new H.service.Platform({
    apikey: 'mkmh-D8wPyFPLhRuQd5QiEnygn8xoDsJ4o4Z4V2G-_4'
});

var defaultLayers = platform.createDefaultLayers();

var map = new H.Map(mapContainer,
        defaultLayers.vector.normal.map, {
            center: {lat: latitude, lng: longitude},
            zoom: 10,
            pixelRatio: window.devicePixelRatio || 1
        });
window.addEventListener('resize', () => map.getViewPort().resize());

var behavior = new H.mapevents.Behavior(new H.mapevents.MapEvents(map));

var ui = H.ui.UI.createDefault(map, defaultLayers);
mapaPI(map);
var bubble;

function openBubble(position, text) {
    if (!bubble) {
        bubble = new H.ui.InfoBubble(
                position,
                // The FO property holds the province name.
                        {content: text});
                ui.addBubble(bubble);
            } else {
        bubble.setPosition(position);
        bubble.setContent(text);
        bubble.open();
    }
}

function addRouteShapeToMap(route) {
    var lineString = new H.geo.LineString(),
            routeShape = route.shape,
            polyline;

    routeShape.forEach(function (point) {
        var parts = point.split(',');
        lineString.pushLatLngAlt(parts[0], parts[1]);
    });

    polyline = new H.map.Polyline(lineString, {
        style: {
            lineWidth: 4,
            strokeColor: 'rgba(0, 128, 255, 0.7)'
        }
    });
    // Add the polyline to the map
    map.addObject(polyline);
    // And zoom to its bounding rectangle
    map.getViewModel().setLookAtData({
        bounds: polyline.getBoundingBox()
    });
}

function addManueversToMap(route) {
    var svgMarkup = '<svg width="18" height="18" ' +
            'xmlns="http://www.w3.org/2000/svg">' +
            '<circle cx="8" cy="8" r="8" ' +
            'fill="#1b468d" stroke="white" stroke-width="1"  />' +
            '</svg>',
            dotIcon = new H.map.Icon(svgMarkup, {anchor: {x: 8, y: 8}}),
            group = new H.map.Group(),
            i,
            j;

    // Add a marker for each maneuver
    for (i = 0; i < route.leg.length; i += 1) {
        for (j = 0; j < route.leg[i].maneuver.length; j += 1) {
            // Get the next maneuver.
            maneuver = route.leg[i].maneuver[j];
            // Add a marker to the maneuvers group
            var marker = new H.map.Marker({
                lat: maneuver.position.latitude,
                lng: maneuver.position.longitude},
                    {icon: dotIcon});
            marker.instruction = maneuver.instruction;
            group.addObject(marker);
        }
    }

    group.addEventListener('tap', function (evt) {
        map.setCenter(evt.target.getGeometry());
        openBubble(
                evt.target.getGeometry(), evt.target.instruction);
    }, false);

    // Add the maneuvers group to the map
    map.addObject(group);
}

Number.prototype.toMMSS = function () {
    return  Math.floor(this / 60) + ' minutes ' + (this % 60) + ' seconds.';
}

// Now use the map as required...
calculateRouteFromAtoB(platform);