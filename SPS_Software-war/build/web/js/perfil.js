let items = document.getElementById("listaItems").children;
let clickItemAnterior = 0;
cargarDocumento(0);

for (let i = 0; i < items.length; i++) {
    items[i].addEventListener('click', function () {
        if (i !== clickItemAnterior) {
            items[clickItemAnterior].classList.remove('seleccionItem')
            items[i].classList.add('seleccionItem')
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
            url = "recursos/ajustes.jsp";
            break;
        case 2:
            url = "recursos/cambios.jsp";
            break;
        case 3:
            url = "recursos/info.jsp";
            break;
        default:
            break;
    }

    var r = new XMLHttpRequest();
    r.open("GET", url, true);
    r.onreadystatechange = function () {
        if (r.readyState !== 4 || r.status !== 200)
            return;
        console.log(r.responseText);
        target.innerHTML = r.responseText;
    };
    r.send();
}