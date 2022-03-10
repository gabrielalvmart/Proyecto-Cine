var url = "http://localhost:8080/Cinematrix/";

var salas = new Array();
var sala = {numero_Sala: "", numero_butacas: 0};

function render() {
    $("#codigoSala").val(sala.numero_Sala);

    $("#addSala").off('click').on('click', add);
    $('#addSala-modal').modal('show');
}

function load() {
    //sala = Object.fromEntries((new FormData($("#formulario").get(0))).entries());
    sala = {numero_Sala: $("#codigoSala").val(),
        numero_butacas: $("#butacasSala").val()};
}

function reset() {
    sala = {numero_Sala: "", numero_butacas: 0};
}

function add() {
    load();
    if (!validar())
        return;

    //console.log(sala.numero_Sala);
    //console.log(sala.numero_butacas);

    let request = new Request(url + 'api/salas', {method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify(sala)});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#addSala-modal #errorDiv"));
            return;
        }
        fetchAndList();
        reset();
        $('#addSala-modal').modal('hide');
    })();
}
//ARREGLAR
function validar() {
    var error = false;
    $("#salaFormulario input").removeClass("invalid");
    error |= $("#salaFormulario input[type='text']").filter((i, e) => {
        return e.value == '';
    }).length > 0;
    $("#salaFormulario input[type='text']").filter((i, e) => {
        return e.value == '';
    }).addClass("invalid");
    
    //console.log(error);
    return !error;
}

function list() {
    $("#listaSalas").html("");
    salas.forEach((s) => {
        row($("#listaSalas"), s);
    });
}

function row(listaSalas, sala) {
    var tr = $("<tr />");
    tr.html("<td>" + sala.numero_Sala + "</td>" +
            "<td>" + sala.numero_butacas + "</td>");

    listaSalas.append(tr);
}

function makenew() {
    reset();
    render();
}

function search() {
    //to do
}

function fetchAndList() {
    let request = new Request(url + 'api/salas', {method: 'GET', headers: {}});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#buscarDiv #errorDiv"));
            return;
        }
        salas = await response.json();
        list();
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
            error = "Sala no autorizada";
            break;
        case 406:
        break;
        case 405:
            error = "Sala ya existe";
            break;
    }
    ;
    place.html('<div class="alert alert-danger fade show">' +
            '<button type="button" class="close" data-dismiss="alert">' +
            '&times;</button><h4 class="alert-heading">Error!</h4>' + error + '</div>');
    return;
}

function loaded() {
    fetchAndList();
    $("#agregarSala").click(makenew);
    $("#buscarSala").on("click", search);
}

$(loaded);