let page = document.getElementById("namePage").value;
if (page === "2") {
    if (document.body.contains(document.getElementById('fecha'))) {
        document.getElementById('fecha').min = new Date(new Date().getTime() - new Date().getTimezoneOffset() * 60000).toISOString().split(".")[0].substring(0, 16);
        document.getElementById('fecha').value = new Date(new Date().getTime() - new Date().getTimezoneOffset() * 60000).toISOString().split(".")[0].substring(0, 16);

        let checkRadio = document.getElementsByName('cubierto');
        checkRadio[0].addEventListener('click', function () {
            ajaxParqueaderos("si");
        });

        checkRadio[1].addEventListener('click', function () {
            ajaxParqueaderos("no");
        });

        function  ajaxParqueaderos(estado) {
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (this.readyState === 4 && this.status === 200) {
                    let arrayParqueaderos = this.responseText.split("\n");
                    arrayParqueaderos.pop();
                    if (arrayParqueaderos.length > 0) {
                        let selector = document.getElementById('parqueadero');
                        selector.style.pointerEvents = "auto";
                        selector.style.opacity = "1";
                        selector.innerHTML = "";
                        var elements = "";
                        for (let i = 0; i < arrayParqueaderos.length; i++) {
                            eval("elements =" + arrayParqueaderos[i]);
                            console.log(elements);
                            let option = document.createElement('option');
                            option.setAttribute('value', elements.id);
                            option.innerHTML = elements.nombre + ' - ' + elements.direccion;
                            selector.appendChild(option);
                        }
                    }
                }
            };
            xhttp.open("GET", "reservar?proceso=cubierto&cubierto=" + estado, true);
            xhttp.send();
        }
    }

    function liquidarReserva() {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
                if (this.responseText === "true") {
                    notificacion("La reserva ha sido liquidada");
                    setTimeout(function () {
                        location.reload();
                    }, 1500);
                } else {
                    notificacion("La reserva no ha sido liquidada, intente de nuevo")
                }
            }
        };
        xhttp.open("GET", "reservar?proceso=liquidar", true);
        xhttp.send();
    }

    function  cancelarReserva() {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
                if (this.responseText === "true") {
                    notificacion("La reserva ha sido cancelada");
                    setTimeout(function () {
                        location.reload();
                    }, 1500);
                } else {
                    notificacion("La reserva no ha sido cancelada, intente de nuevo")
                }
            }
        };
        xhttp.open("GET", "reservar?proceso=cancelar", true);
        xhttp.send();
    }
}

if (page === "1") {
//    if (navigator.geolocation) {
    let latitude;
    let longitude;
//        navigator.geolocation.getCurrentPosition(
//                function (ubicacion) {
//                    latitude =  ubicacion.coords.latitude;
//                    longitude = ubicacion.coords.longitude;
    latitude = "4.6085845";
    longitude = "-74.0761647";
//        console.log(`longitud: ${ latitude } | latitud: ${ longitude }`);
    function addSVGMarkers(map) {
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
        for (let i = 0; i < parqueaderosArray.length; i++) {
            let marker = new H.map.Marker({lat: parqueaderosArray[i].latitud, lng: parqueaderosArray[i].longitud});
            marker.setData(`<div><b>${parqueaderosArray[i].nombre}</b></div><div><br><b>Precio:</b>$ ${parqueaderosArray[i].precio}<br><b>Dirección:</b>${parqueaderosArray[i].direccion}</div><a href="./parqueadero?id=${parqueaderosArray[i].id}">RESERVAR</a>`);
            marker.addEventListener('tap', function (evt) {
                var bubble = new H.ui.InfoBubble(evt.target.getGeometry(), {
                    content: evt.target.getData()
                });
                ui.addBubble(bubble);
            }, false);
            map.addObject(marker);
        }
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
//                },
//                function (objPositionError) {
//                    let mapa = document.getElementById("mapa");
//                    switch (objPositionError.code)
//                    {
//                        case objPositionError.PERMISSION_DENIED:
//                            mapa.innerHTML = "No se ha permitido el acceso a la posición del usuario.";
//                            break;
//                        case objPositionError.POSITION_UNAVAILABLE:
//                            mapa.innerHTML = "No se ha podido acceder a la información de su posición.";
//                            break;
//                        case objPositionError.TIMEOUT:
//                            mapa.innerHTML = "El servicio ha tardado demasiado tiempo en responder.";
//                            break;
//                        default:
//                            mapa.innerHTML = "Error desconocido.";
//                    }
//                });
//    }
}

function notificacion(texto) {

    if (!("Notification" in window)) {
        alert(texto);
    } else if (Notification.permission === "granted") {
        var notification = new Notification('SPS Software', {
            body: texto,
            icon: "http://localhost:8080/SPS_Software-war/images/logo.jpg"
        });

    } else if (Notification.permission !== "denied") {
        Notification.requestPermission(function () {
            if (Notification.permission === "granted") {
                var notification = new Notification('SPS Software', {
                    body: texto,
                    icon: "SPS_Software-war/images/logo.jpg"
                });
            }
        });
    }
}