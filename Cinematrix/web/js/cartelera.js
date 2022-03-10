var url = "http://localhost:8080/Cinematrix/";
var cartelera = new Array();
var butacas_funcion = new Array();
const months = {0: "Ene", 1: "Feb", 2: "Mar", 3: "Abr", 4: "May", 5: "Jun", 6: "Jul", 7: "Ago", 8: "Sep", 9: "Oct", 10: "Nov", 11: "Dic"};
var funcion_actual = {pelicula: "", sala: "", fecha: 0, precio: 0};
var butacasSeleccionadas = new Array();
var usuario;
var tiquete;

function list() {
    $("#movies-container").html("");
    cartelera.forEach((p) => {
        row($("#movies-container"), p);
    });
}

function row(moviescontainer, peli) {

    let funciones = peli.funciones;
    let movie_container = $("<div />");
    movie_container.addClass("grid-item");
    movie_container.html(
            `<div class="card rounded border-2 border-info">
                            <img class="ovie_container.html(card-img-top movie-image" src="` + url + "api/peliculas/" + peli.nombre + "/imagen" + `" alt="image">
                            <div class="card-body text-center funciones-container custom-scrollbar" id="funciones-container">
                            </div>`);

    funciones.forEach((f) => {
        let h6 = $("<h6 />");
        let fecha = new Date(f.fecha);
        let ampm = " AM";
        let hora = fecha.getHours();
        if (fecha.getHours() > 12) {
            hora = hora - 12;
            ampm = " PM";
        }
        let functioncontainer = `<a href="#" data-toggle='modal' data-target='#reservaDialog' id="recuperaFuncion">` + months[fecha.getMonth()] + " " + fecha.getDate() + ", " + hora + ampm + ` / ` + f.sala + ` </a>`;

        h6.html(functioncontainer);
        h6.addClass("card-text");

        h6.find("#recuperaFuncion").on("click", () => {
            console.log("#recuperaFuncion" + f.codigo);
            recuperaFuncion(f.codigo);
        });
        movie_container.find("#funciones-container").append(h6);
    });
    moviescontainer.append(movie_container);
}

function recuperaFuncion(codigo) {
    let request = new Request(url + 'api/funciones/' + codigo, {method: 'GET', headers: {}});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#buscarDiv #errorDiv"));
            return;
        }
        funcion_actual = await response.json();
        recuperaButacas(codigo);
        render();
        $('#reservaAsiento').click(renderTarjeta);
    })();
}

function renderTarjeta(){
    loadTarjeta;
    $('#tajetaDialog').modal('show');
}

function recuperaButacas(codigo) {

    let request = new Request(url + 'api/butacas/' + codigo, {method: 'GET', headers: {}});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#buscarDiv #errorDiv"));
            return;
        }
        butacas_funcion = await response.json();
    })();

}


function search() {
    let movieName = $('#filtradopelicula').val();

    let request = new Request(url + 'api/peliculas?nombre=' + movieName, {method: 'GET', headers: {}});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#buscarDiv #errorDiv"));
            return;
        }
        cartelera = await response.json();
        list();
        $('#filtradopelicula').val("")
    })();
}

function fetchAndList() {
    let request = new Request(url + 'api/peliculas', {method: 'GET', headers: {}});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#buscarDiv #errorDiv"));
            return;
        }
        cartelera = await response.json();
        list();
        
    })();
}

function loadReserva() {
    let request = new Request(url + 'reserva.html', {method: 'GET'});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#reservaDialog #errorDiv"));
            return;
        }
        content = await response.text();

        $('body').append(content);
        $('#cinema').click((e) => {
            select(e);
        });
    })();
}

function loadTarjeta() {
    let request = new Request(url + 'tarjeta.html', {method: 'GET'});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#reservaDialog #errorDiv"));
            return;
        }
        content = await response.text();

        $('body').append(content);
        $('#comprartiquete').click(purchase);
    })();
}

function render() {
    $("#cinema").html(`<div class="screen"></div>`);
    $("#movieLabel").html(funcion_actual.pelicula);
    $("#imgfunction").attr("src", url + "api/peliculas/" + funcion_actual.pelicula + "/imagen");
    $("#preciofuncion").html("Price: $" + funcion_actual.precio);
    var x = 0;

    let request = new Request(url + 'api/butacas/' + funcion_actual.codigo, {method: 'GET', headers: {}});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#buscarDiv #errorDiv"));
            return;
        }
        butacas_funcion = await response.json();
        
        for (var i = 0; i < 6; i++) {
            let div = $("<div />");
            div.addClass("row-seats");
            for (var j = 0; j < 8; j++) {
                let div2 = $("<div />");
                div2.addClass("seat");
                div2.attr("id", x + 1);
                if (butacas_funcion[x].estado === 1) {
                    div2.addClass("occupied");
                }
                div.append(div2);
                x++;
            }
            $("#cinema").append(div);
        }
        
    })();

}

function purchase(){
    const selectedSeats = document.querySelectorAll(".row-seats .selected");
    const selectedSeatsCount = selectedSeats.length;
    $("#count").html(selectedSeatsCount);
    $("#total").html(selectedSeatsCount * funcion_actual.precio);
    var tarj = $("#numerotarjeta").val();
    tiquete = {
        funcion: funcion_actual.codigo,
        butacas: butacasSeleccionadas.toString(),
        fecha: funcion_actual.fecha,
        pelicula: funcion_actual.pelicula,
        sala: funcion_actual.sala,
        tarjeta: tarj,
        precio: selectedSeatsCount * funcion_actual.precio
    };
    
    
    let request = new Request(url + 'api/tiquetes', {method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify(tiquete)});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#addPelicula-modal #errorDiv"));
            return;
        }
        
        generaPfd(tiquete);
        
        $('#reservaDialog').modal('hide');
        $('#tajetaDialog').modal('hide');
    })();
}

function generaPfd(tiquete){
    let request = new Request(url + 'api/tiquetes/usuario', {method: 'GET', headers: {}});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#buscarDiv #errorDiv"));
            return;
        }
        usuario = await response.json();
        
        var doc = new jsPDF();

        doc.setFontSize(55);
        doc.text(35, 25, 'CINEMATRIX');
            
        doc.setFontSize(25);
        doc.text(35, 45, 'Datos del cliente');
        doc.setFontSize(15);
        doc.text(35, 65, "Cedula: " + usuario.cedula);
        doc.text(35, 75, "Nombre: " + usuario.nombre);
        doc.setFontSize(25);
        doc.text(35, 95, 'Datos de la funcion');
        doc.setFontSize(15);
        doc.text(35, 115, "Numero de Funcion: " + tiquete.funcion.toString());
        doc.text(35, 125, "Asientos: " + tiquete.butacas);
        doc.text(35, 135, "Fecha: " + tiquete.fecha);
        doc.text(35, 145, "Pelicula: " + tiquete.pelicula);
        doc.text(35, 155, "Sala: " + tiquete.sala);
        doc.text(35, 165, "Numero de tarjeta: " + tiquete.tarjeta);
        doc.text(35, 175, "Precio: " + tiquete.precio.toString());
        
        doc.save("tiquete" + usuario.cedula + tiquete.funcion.toString() + tiquete.pelicula + ".pdf");
        
    })();
}

function select(e) {
    if (
            e.target.classList.contains("seat") &&
            !e.target.classList.contains("occupied")
            ) {
        e.target.classList.toggle("selected");
        console.log(butacasSeleccionadas.indexOf(e.target.id));
        if(butacasSeleccionadas.indexOf(e.target.id) >= 0){
            butacasSeleccionadas.splice(butacasSeleccionadas.indexOf(e.target.id), 1);
        }
        else{
            butacasSeleccionadas.push(e.target.id);
        }
        actualizarReserva();
    }
}

function actualizarReserva() {
    const selectedSeats = document.querySelectorAll(".row-seats .selected");
    const selectedSeatsCount = selectedSeats.length;
    $("#count").html(selectedSeatsCount);
    $("#total").html(selectedSeatsCount * funcion_actual.precio);
}


function loadedaa() {
    fetchAndList();
    loadReserva();
    loadTarjeta();
    $("#buscapelicula").click(search);
}



$(loadedaa);


