var url = "http://localhost:8080/Cinematrix/";

var funciones = new Array();
var salas = new Array();
var peliculas = new Array();
const months = {0:"Ene", 1:"Feb", 2:"Mar", 3:"Abr", 4:"May", 5:"Jun", 6:"Jul", 7:"Ago", 8:"Sep", 9:"Oct", 10:"Nov", 11:"Dic"};
var funcion = {pelicula: "", sala: "", fecha: 0, precio: 0};


function render() {
    $("#peliculaFuncion").val(funcion.pelicula);
    $("#salaFuncion").val(funcion.sala);
    $("#precioFuncion").val(funcion.sala);
    
    $("#peliculaFuncion").html("");
    $("#salaFuncion").html("");
    $("#fechaFuncion").val("");
    $("#HoraFuncion").val("");
    
    fetchAndListSalas();
    fetchAndListPeliculas();

    $('#addFuncion').off('click').on('click', add);

    $('#addFuncion-modal').modal('show');
}

function load(){
    funcion = {pelicula: $("#peliculaFuncion").val(),
        sala: $("#salaFuncion").val(),
        fecha: $("#fechaFuncion").val() + " " + $("#HoraFuncion").val() + ":00",
        precio: $("#precioFuncion").val()
    };
}

function validar(){
    var error = false;
    $("#funcionFormulario input").removeClass("invalid");
    error |= $("#peliculaFormulario input[type='text']").filter((i, e) => {
        return e.value == '';
    }).length > 0;
    $("#funcionFormulario input[type='text']").filter((i, e) => {
        return e.value == '';
    }).addClass("invalid");
    
    error |= $("#funcionFormulario input[type='date']").filter((i, e) => {
        return e.value == 0;
    }).length > 0;
    $("#funcionFormulario input[type='date']").filter((i, e) => {
        return e.value == 0;
    }).addClass("invalid");
    
    error |= $("#funcionFormulario input[type='time']").filter((i, e) => {
        return e.value == 0;
    }).length > 0;
    $("#funcionFormulario input[type='time']").filter((i, e) => {
        return e.value == 0;
    }).addClass("invalid");

    return !error;
}

function add(){
    load();
    if (!validar())
        return;

    let request = new Request(url + 'api/funciones', {method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify(funcion)});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#addFuncion-modal #errorDiv"));
            return;
        }
        fetchAndList();
        reset();
        $('#addFuncion-modal').modal('hide');
    })();
}

function errorMessage(status, place) {
    switch (status) {
        case 404:
            error = "Registro no encontrado";
            break;
        case 403:
        break;
        case 405:
                error = "Funcion no autorizada";
            break;
        case 406:
        break;
        case 405:
            error = "Funcion ya existe";
            break;
    }
    ;
    place.html('<div class="alert alert-danger fade show">' +
            '<button type="button" class="close" data-dismiss="alert">' +
            '&times;</button><h4 class="alert-heading">Error!</h4>' + error + '</div>');
    return;
}

function reset(){
    funcion = {pelicula: "", sala: "", fecha: 0, precio: 0};
}
function list() {
    $("#listaFunciones").html("");
    funciones.forEach((f) => {
        row($("#listaFunciones"), f);
    });
}

function row(listaFunciones, funcion) {
    var tr = $("<tr />");
    let fecha = new Date(funcion.fecha);
    let ampm = " AM";
    let hora = fecha.getHours();
    if(fecha.getHours() > 12){
        hora = hora - 12;
        ampm = " PM"
    }
    
    
    tr.html("<td>" + funcion.codigo + "</td>" +
            "<td>" + funcion.pelicula + "</td>" +
            "<td>" + funcion.sala + "</td>" +
            "<td>" + months[fecha.getMonth()] + " " + fecha.getDate() + ", " + hora + ampm + "</td>" +
            "<td>" + funcion.precio + "</td>");
    listaFunciones.append(tr);
}

function makenew() {
    reset();
    render();
}

function search() {
    //to do
}

function fetchAndList() {
    let request = new Request(url + 'api/funciones', {method: 'GET', headers: {}});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#buscarDiv #errorDiv"));
            return;
        }
        funciones = await response.json();
        list();
    })();
}

function fetchAndListSalas() {
    let request = new Request(url + 'api/salas', {method: 'GET', headers: {}});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            return;
        }
        salas = await response.json();
        listarSalas();
    })();
}

function listarSalas() {
    salas.forEach((s) => {
        $("#salaFuncion").append(
                "<option value=" + s.numero_Sala +">" + s.numero_Sala + "</option>");
    });
}

function fetchAndListPeliculas() {
    let request = new Request(url + 'api/peliculas', {method: 'GET', headers: {}});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            return;
        }
        peliculas = await response.json();
        listarPeliculas();
    })();
}

function listarPeliculas() {
    peliculas.forEach((p) => {
        $("#peliculaFuncion").append(
                "<option value=" + p.codigo +">" + p.nombre + "</option>");
    });
}

function loaded() {
    fetchAndList();
    $("#agregarFuncion").click(makenew);
    //$("#buscarSala").on("click", search);
    
}

$(loaded);