let menuVehiculo = document.getElementsByClassName("pnlDrop");
let opcionMoto = true;
let opcionCarro = true;

menuVehiculo[0].addEventListener('click', function () {
    document.getElementById("pnlCarros").style.display = opcionCarro ? "none" : "grid";
    document.getElementById("pnlCarros").style.transition = "transition: all .5s";

    menuVehiculo[0].lastElementChild.classList.add(opcionCarro ? "fa-chevron-down" : "fa-chevron-up");
    menuVehiculo[0].lastElementChild.classList.remove(opcionCarro ? "fa-chevron-up" : "fa-chevron-down");

    opcionCarro = !opcionCarro;
});

menuVehiculo[1].addEventListener('click', function () {
    document.getElementById("pnlMotos").style.display = opcionMoto ? "none" : "grid";
    document.getElementById("pnlMotos").style.transition = "transition: all .5s";
    
    menuVehiculo[1].lastElementChild.classList.add(opcionMoto ? "fa-chevron-down" : "fa-chevron-up");
    menuVehiculo[1].lastElementChild.classList.remove(opcionMoto ? "fa-chevron-up" : "fa-chevron-down");
    
    opcionMoto = !opcionMoto;
});