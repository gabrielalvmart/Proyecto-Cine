var url = "http://localhost:8080/Cinematrix/";

function registro() {
    if (!registroValidar())
        return;
    usuario = {
        cedula: $("#newCedula").val(),
        nombre: $("#newNombre").val(),
        password: $("#newPassword").val(),
        perfil: "CLI"
    }

    let request = new Request(url + 'api/registro', {method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify(usuario)});

    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#registroDialog #errorDiv"));
            return;
        }
        $("#registroDialog").modal("hide");
        
        alert("Usuario Registrado");

        document.location = url;
    })();
}

function registroValidar() {
    $("#registroForm").addClass("was-validated");
    return $("#registroForm").get(0).checkValidity();
}

function errorMessage(status, place) {
    switch (status) {
        case 404:
            error = "Registro no encontrado";
            break;
        case 403:
        case 405:
            error = "Usuario no autorizado";
            break;
        case 406:
        case 405:
            error = "Usuario ya existe";
            break;
    }
    ;
    place.html('<div class="alert alert-danger fade show">' +
            '<button type="button" class="close" data-dismiss="alert">' +
            '&times;</button><h4 class="alert-heading">Error!</h4>' + error + '</div>');
    return;
}

function loadRegistro() {
    let request = new Request(url + 'registro.html', {method: 'GET'});

    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#registroDialog #errorDiv"));
            return;
        }
        content = await response.text();
        $('body').append(content);
        $("#registro").click(registro);
    })();
}

$(loadRegistro);

