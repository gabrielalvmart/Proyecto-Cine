var url = "http://localhost:8080/Cinematrix/";

function login() {
    if (!loginValidar())
        return;
    usuario = {
        cedula: $("#cedula").val(),
        password: $("#password").val()
    };
    
    console.log(usuario.cedula);
    console.log(usuario.password);
    
    let request = new Request(url + 'api/login', {method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify(usuario)});

    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#loginDialog #errorDiv"));
            return;
        }
        usuario = await response.json();
        sessionStorage.setItem('user', JSON.stringify(usuario));
        $("#loginDialog").modal("hide");
        
        document.location = url + "index.html";
    })();
}

function loginValidar() {
    $("#loginForm").addClass("was-validated");
    return $("#loginForm").get(0).checkValidity();
}

function logout() {
    let request = new Request(url + 'api/login', {method: 'DELETE', headers: {}});

    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#loginDialog #errorDiv"));
            return;
        }
        sessionStorage.removeItem('user');
        document.location = url + "index.html";
    })();
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

function loadLogin() {
    let request = new Request(url + 'login.html', {method: 'GET'});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#loginDialog #errorDiv"));
            return;
        }
        content = await response.text();
        $('body').append(content);
        $("#login").click(login);
        $("#logout").click(logout);
    })();
}

$(loadLogin);