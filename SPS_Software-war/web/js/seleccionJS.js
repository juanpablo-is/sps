var cards = document.getElementsByClassName("cardPerfil");
var boton = document.getElementById("btnSubmit");
var anteriorClick = '';

for (let i = 0; i < cards.length; i++) {
    cards[i].addEventListener('click', function (e) {
        if (anteriorClick !== '') {
            cards[anteriorClick].classList.remove("seleccion");
        }

        var idPerfil = cards[i].childNodes[1].value;
        document.getElementById("valorSeleccion").value = idPerfil;
        anteriorClick = i;
        cards[i].classList.add("seleccion");
        boton.classList.add("botonClick");
        boton.removeAttribute("disabled");
    }, true);
}