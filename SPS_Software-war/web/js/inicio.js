var namePage = document.getElementById("namePage").value;

var listaMenu = document.querySelector("#menu ul").children;

for (var i = 0; i < listaMenu.length; i++) {
    var listaLi = listaMenu[i].firstElementChild.innerText;
    if (i === (namePage - 1)) {
        listaMenu[i].firstElementChild.classList.add("seleccionPagina");
    }
}