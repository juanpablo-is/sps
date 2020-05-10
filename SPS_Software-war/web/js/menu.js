let accounts = new Array();
getAccounts();
let pnlInfo = document.getElementById("pnlInfo");
var namePage = document.getElementById("namePage").value;
var listaMenu = document.querySelector("#menu ul").children;
let iconoInfo = document.getElementById("iconoInfo");
let textoInfo = ["En esta pagina puede ver un detalle de sus reservas mas recientes, ademas de un mapa de su ubicación y un grafico donde representa un historial de su grafica por mes durante este año",
    "Llenando el formulario se hará un reserva en el respectivo parqueadero, todos los campos son requeridos.",
    "Se listará el total de los parqueaderos que trabajan con nosotros y ademas de una detallada descripción.",
    "Podra visualizar un historial de sus reservas anteriormente agregadas."];
createInfo();

let click = false;

function createInfo() {
    for (var i = 0; i < listaMenu.length; i++) {
        var listaLi = listaMenu[i].firstElementChild.innerText;
        if (i === (namePage - 1)) {
            pnlInfo.innerHTML = "";
            let elementH2 = document.createElement("h2");
            elementH2.innerHTML = listaLi;
            let elementP = document.createElement("p");
            elementP.innerHTML = textoInfo[i];
            pnlInfo.appendChild(elementH2);
            pnlInfo.appendChild(elementP);
            listaMenu[i].firstElementChild.classList.add("seleccionPagina");
        }
    }
}

function iconoAccount() {
    click = !click;
    pnlInfo.innerHTML = "";
    if (click) {
        let divPerfil = document.createElement("div");
        divPerfil.addEventListener('click',function(){
            window.open("http://localhost:8080/SPS_Software-war/perfil.jsp","_self");
        });
        let divSoporte = document.createElement("div");
        divPerfil.classList.add("menuDesplegable");
        divSoporte.classList.add("menuDesplegable");

        let iconoPerfil = document.createElement("i");
        iconoPerfil.classList.add("fas", "fa-user-circle");
        let textoPerfil = document.createElement("h3");
        textoPerfil.innerHTML = "PERFIL";
        divPerfil.appendChild(iconoPerfil);
        divPerfil.appendChild(textoPerfil);

        let iconoSoporte = document.createElement("i");
        iconoSoporte.classList.add("fas", "fa-headset");
        let textoSoporte = document.createElement("h3");
        textoSoporte.innerHTML = "SOPORTE";
        divSoporte.appendChild(iconoSoporte);
        divSoporte.appendChild(textoSoporte);

        pnlInfo.appendChild(divPerfil);
        pnlInfo.appendChild(divSoporte);
        pnlInfo.appendChild(document.createElement("hr"));
        let textoCuenta = document.createElement("h3");
        textoCuenta.innerHTML = "CAMBIAR CUENTA";
        textoCuenta.id = "textoCuenta";
        pnlInfo.appendChild(textoCuenta);
        pnlInfo.classList.add("accountsList");
        for (let i = 0; i < accounts.length - 1; i++) {
            let elements = Object.values(accounts[i]);
            if (elements[0] !== Object.values(accounts[accounts.length - 1])[0]) {
                let elementH2 = document.createElement("h2");
                elementH2.innerHTML = elements[1];
                elementH2.addEventListener('click', function () {
                    pnlInfo.style.display = "none";
                    click = false;
                    var xhttp = new XMLHttpRequest();
                    xhttp.onreadystatechange = function () {
                        if (this.readyState === 4 && this.status === 200) {
                            window.open("http://localhost:8080/SPS_Software-war/inicio", "_self");
                        }
                    };
                    xhttp.open("GET", "AJAXCuentasPersona?proceso=2&valor=" + elements[0] + "&perfil=" + elements[2], true);
                    xhttp.send();
                }, false);
                pnlInfo.appendChild(elementH2);
            }
        }
    }
    pnlInfo.style.display = click ? "block" : "none";
}

function getAccounts() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let array = this.responseText.split("\n");
            let accountActive = array.pop();
            for (let i = 0; i < array.length; i++) {
                accounts[i] = JSON.parse(array[i]);
            }
            accounts.push(JSON.parse(accountActive));
        }
    };
    xhttp.open("GET", "AJAXCuentasPersona?proceso=1", true);
    xhttp.send();
}

function iconoFunction(s) {
    if (pnlInfo.childElementCount === 0 && !click) {
        createInfo();
    }

    if (!click) {
        pnlInfo.style.display = s ? "block" : "none";
    }
}
