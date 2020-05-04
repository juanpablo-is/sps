var namePage = document.getElementById("namePage").value;
var listaMenu = document.querySelector("#menu ul").children;
let iconoInfo = document.getElementById("iconoInfo");
let textoInfo = ["En esta pagina puede ver un detalle de sus reservas mas recientes, ademas de un mapa de su ubicación y un grafico donde representa un historial de su grafica por mes durante este año",
    "Llenando el formulario se hará un reserva en el respectivo parqueadero, todos los campos son requeridos.",
    "Se listará el total de los parqueaderos que trabajan con nosotros y ademas de una detallada descripción.",
    "Podra visualizar un historial de sus reservas anteriormente agregadas."];

for (var i = 0; i < listaMenu.length; i++) {
    var listaLi = listaMenu[i].firstElementChild.innerText;
    if (i === (namePage - 1)) {
        document.getElementById("pnlInfo").children[0].innerHTML = listaLi;
        document.getElementById("pnlInfo").children[1].innerHTML = textoInfo[i];
        listaMenu[i].firstElementChild.classList.add("seleccionPagina");
    }
}

function iconoFunction(s) {
    document.getElementById("pnlInfo").style.display = s ? "block" : "none";
}
