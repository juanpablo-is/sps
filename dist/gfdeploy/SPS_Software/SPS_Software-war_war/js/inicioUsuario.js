let page = document.getElementById("namePage").value;

if (page === "2") {
    document.getElementById('dia').min = new Date(new Date().getTime() - new Date().getTimezoneOffset() * 60000).toISOString().split("T")[0];
    document.getElementById('dia').value = new Date(new Date().getTime() - new Date().getTimezoneOffset() * 60000).toISOString().split("T")[0];
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
                let selector = document.getElementById('parqueadero');
                selector.style.pointerEvents = "auto";
                selector.style.opacity = "1";
                selector.innerHTML = "";
                let arrayParqueaderos = this.responseText.split("\n");
                arrayParqueaderos.pop();
                for (let i = 0; i < arrayParqueaderos.length; i++) {
                    console.log(arrayParqueaderos[i]);
                    let elements = JSON.parse(arrayParqueaderos[i]);
                    let option = document.createElement('option');
                    option.setAttribute('value', elements.id);
                    option.innerHTML = elements.nombre + ' - ' + elements.direccion;
                    selector.appendChild(option);
                }
            }
        };
        xhttp.open("GET", "reservar?cubierto=" + estado, true);
        xhttp.send();
    }
}

if (page === "1") {
    if (navigator.geolocation) {
        let latitude;
        let longitude;
//        navigator.geolocation.getCurrentPosition(
//                function (ubicacion) {
//                    latitude =  ubicacion.coords.latitude;
//                    longitude = ubicacion.coords.longitude;
        latitude = "4.6085845";
        longitude = "-74.0761647";
        console.log(`longitud: ${ latitude } | latitud: ${ longitude }`);
        function addSVGMarkers(map) {
            var svgMarkup = '<svg  width="28" height="28" xmlns="http://www.w3.org/2000/svg">' +
                    '<circle stroke="red" fill="red" cx="14" cy="14" r="13" />' +
                    '<text x="14" y="20" font-size="12pt" font-family="Arial" font-weight="bold" ' +
                    'text-anchor="middle" fill="white" >☝</text></svg>';
            var bearsIcon = new H.map.Icon(
                    svgMarkup.replace('${FILL}', 'blue').replace('${STROKE}', 'red')),
                    bearsMarker = new H.map.Marker({lat: latitude, lng: longitude}, {icon: bearsIcon});
            bearsMarker.setData('<div>Tu Ubicación</div><div><br><b>Latitud:</b>' + latitude + '<br><b>Longitud: </b>' + longitude + '</div>');
            bearsMarker.addEventListener('tap', function (evt) {
                var bubble = new H.ui.InfoBubble(evt.target.getGeometry(), {
                    content: evt.target.getData()
                });
                ui.addBubble(bubble);
            }, false);
            map.addObject(bearsMarker);
            for (let i = 0; i < parqueaderosArray.length; i++) {
                let marker = new H.map.Marker({lat: parqueaderosArray[i].latitud, lng: parqueaderosArray[i].longitud});
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
                    zoom: 17,
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
    }
}

function reservaPI(idCliente) {
    let reservaDiv = document.getElementById("reservarPI");
    reservaDiv.innerHTML = "";

    let form = document.createElement("form");
    form.setAttribute('method', 'post');
    form.setAttribute('action', './reservar');

    let divInputs = document.createElement("div");
    divInputs.classList.add("fila");
    let divDay = document.createElement("div");
    divDay.classList.add("columna");
    let labelDay = document.createElement("label");
    labelDay.innerHTML = "Día reserva";
    let inputDay = document.createElement("input");
    inputDay.setAttribute('type', 'date');
    inputDay.setAttribute('name', 'dia');
    inputDay.setAttribute('required', 'on');
    divDay.appendChild(labelDay);
    divDay.appendChild(inputDay);
    let divTime = document.createElement("div");
    divTime.classList.add("columna");
    let labelTime = document.createElement("label");
    labelTime.innerHTML = "Fecha reserva";
    let inputTime = document.createElement("input");
    inputTime.setAttribute('type', 'time');
    inputTime.setAttribute('name', 'entrada');
    inputTime.setAttribute('required', 'on');
    divTime.appendChild(labelTime);
    divTime.appendChild(inputTime);
    divInputs.appendChild(divDay);
    divInputs.appendChild(divTime);

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
    inputSubmit.setAttribute('name', 'reservar');

    form.appendChild(divInputs);
    form.appendChild(divCheck);
    form.appendChild(inputSubmit);
    form.appendChild(inputCliente);
    reservaDiv.appendChild(form);
}