let items = document.getElementById("listaItems").children;
let clickItemAnterior = 0;
cargarDocumento(0);
let formData = new FormData();

for (let i = 0; i < items.length; i++) {
    items[i].addEventListener('click', function () {
        if (i !== clickItemAnterior) {
            items[clickItemAnterior].classList.remove('seleccionItem');
            items[i].classList.add('seleccionItem');
            clickItemAnterior = i;

            cargarDocumento(i);
        }
    }, true);
}

function goBack() {
    window.history.back();
}

function cargarDocumento(i) {
    let target = document.getElementById("target");
    let url = "";
    switch (i) {
        case 0:
            url = "recursos/perfil.jsp";
            break;
        case 1:
            url = "recursos/info.jsp";
            break;
        case 2:
            url = "recursos/cambios.jsp";
            break;
        case 3:
            url = "recursos/ajustes.jsp";
            break;
        default:
            break;
    }

    var r = new XMLHttpRequest();
    r.open("GET", url, true);
    r.onreadystatechange = function () {
        if (r.readyState !== 4 || r.status !== 200)
            return;
//        console.log(r.responseText);
        target.innerHTML = r.responseText;
        switch (i) {
            case 0:
                break;
            case 1:
                urlInfo();
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                break;
        }
    };
    r.send();
}

function urlInfo() {
    let preguntas = document.getElementsByClassName("pnlDrop");

    for (let i = 0; i < preguntas.length; i++) {
        preguntas[i].addEventListener('click', function () {
            let divPregunta = this;
            if (divPregunta.classList.length === 1) {
                divPregunta.classList.add('preguntaSeleccion');
                divPregunta.children[0].children[1].classList.remove('fa-chevron-down');
                divPregunta.children[0].children[1].classList.add('fa-chevron-up');
                divPregunta.children[1].style.display = "block";
            } else {
                divPregunta.classList.remove('preguntaSeleccion');
                divPregunta.children[0].children[1].classList.remove('fa-chevron-up');
                divPregunta.children[0].children[1].classList.add('fa-chevron-down');
                divPregunta.children[1].style.display = "none";
            }
        }, false);
    }
}

function informacionModificacion(info) {
    if (confirm("¿Esta seguro modificar los valores?")) {
        let data = null;
        switch (info) {
            case 'personal':
                let nombre = document.getElementById("nombre");
                let cedula = document.getElementById("cedula");
                let telefono = document.getElementById("telefono");
                data = `info=${info}&nombre=${nombre.value}&cedula=${cedula.value}&telefono=${telefono.value}`;
                break;
            case 'acceso':
                let correo = document.getElementById("correo");
                let contrasenia = document.getElementById("contrasenia");
                data = `info=${info}&correo=${correo.value}&contrasenia=${contrasenia.value}`;
                break;
            default:
                return;
        }
        ajaxInformacion(data);
    }
}

function ajaxInformacion(data) {
    var r = new XMLHttpRequest();
    r.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let respuesta = r.responseText;
            if (respuesta) {
                notificacion("Se ha modificado correctamente");
            } else {
                location.reload();
            }
        }
    };
    r.open("POST", 'AJAXPerfilCuenta', true);
    r.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
    r.send(data);
}

function informacionPerfil(tipo) {
    if (confirm("¿Esta seguro modificar los valores?")) {
        let data = null;
        switch (tipo) {
            case 'cliente':
                let precio = document.getElementById("precio");
                let nombre = document.getElementById("nombreCliente");
                let direccion = document.getElementById("direccion");
                let horaEntrada = document.getElementById("entrada");
                let horaSalida = document.getElementById("salida");
                data = `tipo=cliente&info=perfil&precio=${precio.value}&nombreCliente=${nombre.value}&direccion=${direccion.value}&entrada=${horaEntrada.value}&salida=${horaSalida.value}`;
                break;
            case 'usuario':
                let marca = document.getElementById("marca");
                data = `tipo=usuario&info=perfil&marca=${marca.value}`;
                break;
            case 'movilidad':
                let sede = document.getElementById("sede");
                data = `tipo=movilidad&info=perfil&sede=${sede.value}`;
                break;
            default:
                return;
        }
        ajaxInformacion(data);
    }
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