var url = "http://localhost:8080/Cinematrix/";

var peliculas = new Array();
var pelicula = {nombre: "", genero: ""};

function render() {
    $("#nombrePelicula").val(pelicula.nombre);
    $("#generoPelicula").val(pelicula.genero);
    $("#imagenPelicula").val("");

    $('#addPelicula').off('click').on('click', add);

    $('#addPelicula-modal').modal('show');
}

function load() {
    pelicula = {nombre: $("#nombrePelicula").val(),
        genero: $("#generoPelicula").val()};
}

function reset() {
    pelicula = {nombre: "", genero: ""};
}

function add() {
    load();
    if (!validar())
        return;

    let request = new Request(url + 'api/peliculas', {method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify(pelicula)});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#addPelicula-modal #errorDiv"));
            return;
        }
        addImagen();
        fetchAndList();
        reset();
        $('#addPelicula-modal').modal('hide');
    })();
}

function addImagen() {
    var imagenData = new FormData();
    imagenData.append("nombre", pelicula.nombre);
    imagenData.append("imagen", $("#imagenPelicula").get(0).files[0]);
    let request = new Request(url + 'api/peliculas/' + pelicula.nombre + "/imagen", {method: 'POST', body: imagenData});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#add-modal #errorDiv"));
            return;
        }
    })();
}

//ARREGLAR
function validar() {
    var error = false;
    $("#peliculaFormulario input").removeClass("invalid");
    error |= $("#peliculaFormulario input[type='text']").filter((i, e) => {
        return e.value == '';
    }).length > 0;
    $("#peliculaFormulario input[type='text']").filter((i, e) => {
        return e.value == '';
    }).addClass("invalid");

    error |= $("#peliculaFormulario input[type='text']").filter((i, e) => {
        return e.value == 0;
    }).length > 0;
    $("#peliculaFormulario input[type='text']").filter((i, e) => {
        return e.value == 0;
    }).addClass("invalid");

    console.log(error);
    return !error;
}

function errorMessage(status, place) {
    switch (status) {
        case 404:
            error = "Registro no encontrado";
            break;
        case 403:
            break;
        case 405:
            error = "Pelicula no autorizada";
            break;
        case 406:
            break;
        case 405:
            error = "Pelicula ya existe";
            break;
    }
    ;
    place.html('<div class="alert alert-danger fade show">' +
            '<button type="button" class="close" data-dismiss="alert">' +
            '&times;</button><h4 class="alert-heading">Error!</h4>' + error + '</div>');
    return;
}

function list() {
    $("#listaPeliculas").html("");
    peliculas.forEach((p) => {
        row($("#listaPeliculas"), p);
    });
}

function row(listaPeliculas, pelicula) {
    var tr = $("<tr />");
    tr.html("<td>" + pelicula.codigo + "</td>" +
            "<td>" + pelicula.nombre + "</td>" +
            "<td>" + pelicula.genero + "</td>" +
            "<td><img src='" + url + "api/peliculas/" + pelicula.nombre + "/imagen' class='icon_large' ></td>");

    listaPeliculas.append(tr);
}

function makenew() {
    reset();
    render();
}

function search() {
    //to do
}

function fetchAndList() {
    let request = new Request(url + 'api/peliculas', {method: 'GET', headers: {}});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#buscarDiv #errorDiv"));
            return;
        }
        peliculas = await response.json();
        list();
    })();
}

function loaded() {
    fetchAndList();
    $("#agregarPelicula").click(makenew);
    $("#buscarPelicula").on("click", search);
}

$(loaded);