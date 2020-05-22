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

function informacionPersonal() {

    if (confirm("¿Esta seguro modificar los valores?")) {
        let nombre = document.getElementById("nombre");
        let cedula = document.getElementById("cedula");
        let telefono = document.getElementById("telefono");
        formData.append("nombre", nombre.value);
        formData.append("cedula", cedula.value);
        formData.append("telefono", telefono.value);

        var r = new XMLHttpRequest();
        r.open("GET", 'AJAXPerfilCuenta', true);
        r.onreadystatechange = function () {
            if (r.readyState !== 4 || r.status !== 200)
                location.reload();
            return;
            let respuesta = r.responseText;
            console.log(respuesta);
            if (respuesta === "guardado") {
            } else {
                location.reload();
            }
        };
        r.send(formData);
    }
}
