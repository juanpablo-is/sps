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

            var divRow2 = document.createElement("div");
            divRow2.classList.add("row");

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
            input2.style.textTransform = 'CAPITALIZE';

            var input3 = document.createElement("input");
            input3.setAttribute("type", "text");
            input3.setAttribute("placeholder", "ID PROPIEDAD");
            input3.setAttribute("name", "idPropiedad");
            input3.setAttribute("required", "on");
            input3.style.textTransform = 'UPPERCASE';

            var input4 = document.createElement("select");
            input4.setAttribute("placeholder", "TIPO VEHÍCULO CARRO/MOTO");
            input4.setAttribute("name", "tipoVehiculo");
            input4.setAttribute("required", "on");

            let opcion1 = document.createElement("option");
            opcion1.innerHTML = "CARRO";
            opcion1.setAttribute("value", "carro");

            let opcion2 = document.createElement("option");
            opcion2.innerHTML = "MOTO";
            opcion2.setAttribute("value", "moto");

            input4.appendChild(opcion1);
            input4.appendChild(opcion2);
            divRow.appendChild(input1);
            divRow.appendChild(input2);
            divRow2.appendChild(input3);
            divRow2.appendChild(input4);
            divAdd.appendChild(divRow);
            divAdd.appendChild(divRow2);

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

            var divRow3 = document.createElement("div");
            divRow3.classList.add("row");

            var input5 = document.createElement("input");
            input5.setAttribute("type", "number");
            input5.setAttribute("min", "0");
            input5.setAttribute("placeholder", "CANTIDAD CARROS");
            input5.setAttribute("name", "cantidadCarros");

            var input6 = document.createElement("input");
            input6.setAttribute("type", "number");
            input6.setAttribute("min", "0");
            input6.setAttribute("placeholder", "CANTIDAD MOTOS");
            input6.setAttribute("name", "cantidadMotos");
            divRow3.appendChild(input5);
            divRow3.appendChild(input6);

            divAdd.appendChild(divRow);
            divAdd.appendChild(divRow2);
            divAdd.appendChild(divRow3);
        } else if (e.target.id === 'admin') {
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (this.readyState === 4 && this.status === 200) {
                    let array = this.responseText.split("\n");
                    array.pop();

                    var divRow = document.createElement("div");
                    divRow.classList.add("row");
                    divRow.id = "rowEmpresa";

                    var input1 = document.createElement("select");
                    input1.id = "selectEmpresa";
                    input1.setAttribute("name", "empresa");
                    input1.setAttribute("required", "on");

                    let opcion1 = document.createElement("option");
                    opcion1.innerHTML = "Seleccione una empresa";
                    opcion1.setAttribute("selected", true);
                    opcion1.setAttribute("disabled", "disabled");
                    input1.appendChild(opcion1);

                    for (let i = 0; i < array.length; i++) {
                        let opcion1 = document.createElement("option");
                        opcion1.innerHTML = array[i];
                        opcion1.setAttribute("value", array[i]);
                        input1.appendChild(opcion1);
                    }

                    let opcion2 = document.createElement("option");
                    opcion2.innerHTML = "Agregar una nueva";
                    opcion2.setAttribute("value", "nueva");
                    input1.appendChild(opcion2);

                    divRow.appendChild(input1);
                    divAdd.appendChild(divRow);

                    input1.addEventListener('change', function (e) {
                        let empresa = document.getElementById("rowEmpresa");
                        if (e.target.value === "nueva") {
                            var inputEmpresa = document.createElement("input");
                            inputEmpresa.setAttribute("type", "text");
                            inputEmpresa.setAttribute("placeholder", "Nombre de la empresa");
                            inputEmpresa.setAttribute("name", "empresa");
                            inputEmpresa.setAttribute("required", "on");
                            inputEmpresa.style.textTransform = 'CAPITALIZE';

                            empresa.appendChild(inputEmpresa);
                        } else if (empresa.childElementCount > 1) {
                            empresa.removeChild(empresa.lastElementChild);
                        }
                    }, true);
                }
            };
            xhttp.open("GET", "EmpresasMovilidadServlet", true);
            xhttp.send();
        }
    }
}