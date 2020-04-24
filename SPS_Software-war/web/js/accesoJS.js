let btnIniciarSesionPage = document.getElementsByClassName("btnOtraPagina")[0];
let textoBoton = btnIniciarSesionPage.getAttribute('data-tipo');

btnIniciarSesionPage.addEventListener('click', function () {
    if (textoBoton === '1') {
        window.open("registro.jsp", "_self");
    } else if (textoBoton === '2') {
        window.open("index.jsp", "_self");
    }
}, false);

if (textoBoton === '2') {
    var radios = document.querySelectorAll("input[type=radio]");
    for (var i = 0; i < radios.length; i++) {
        radios[i].addEventListener('click', radio, true);
    }

    function radio(e) {
        var divAdd = document.getElementById("addRow");
        divAdd.innerHTML = '';

        if (e.target.id === 'usuario') {
            var divRow = document.createElement("div");
            divRow.classList.add("row");

            var input1 = document.createElement("input");
            input1.setAttribute("type", "text");
            input1.setAttribute("placeholder", "PLACA");
            input1.setAttribute("name", "placa");
            input1.setAttribute("required", "on");
            input1.style.textTransform = 'UPPERCASE';

            var input2 = document.createElement("input");
            input2.setAttribute("type", "text");
            input2.setAttribute("placeholder", "MARCA");
            input2.setAttribute("name", "marca");

            var input3 = document.createElement("input");
            input3.setAttribute("type", "text");
            input3.setAttribute("placeholder", "ID PROPIEDAD");
            input3.setAttribute("name", "idPropiedad");
            input3.setAttribute("required", "on");
            input3.style.textTransform = 'UPPERCASE';

            divRow.appendChild(input1);
            divRow.appendChild(input2);
            divRow.appendChild(input3);

            divAdd.appendChild(divRow);

        } else if (e.target.id === 'cliente') {
            var divRow = document.createElement("div");
            divRow.classList.add("row");

            var input1 = document.createElement("input");
            input1.setAttribute("type", "number");
            input1.setAttribute("min", "0");
            input1.setAttribute("step", "any");
            input1.setAttribute("placeholder", "PRECIO POR MINUTO");
            input1.setAttribute("name", "precio");

            var input2 = document.createElement("input");
            input2.setAttribute("type", "number");
            input2.setAttribute("placeholder", "CUPOS");
            input2.setAttribute("name", "cupos");
            input2.setAttribute("required", "on");
            divRow.appendChild(input2);
            divRow.appendChild(input1);

            var divRow2 = document.createElement("div");
            divRow2.classList.add("row");

            var input3 = document.createElement("input");
            input3.setAttribute("type", "time");
            input3.setAttribute("placeholder", "HORA ENTRADA");
            input3.setAttribute("name", "horaEntrada");

            var input4 = document.createElement("input");
            input4.setAttribute("type", "time");
            input4.setAttribute("placeholder", "HORA CIERRE");
            input4.setAttribute("name", "horaCierre");
            divRow2.appendChild(input3);
            divRow2.appendChild(input4);

            divAdd.appendChild(divRow);
            divAdd.appendChild(divRow2);
        } else if (e.target.id === 'admin') {
        }
    }
}