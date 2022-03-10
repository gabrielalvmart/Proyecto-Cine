var url = "http://localhost:8080/Cinematrix/";

var tiquetes = new Array();
var usuario;

function list(){
    $("#listaFunciones").html("");
    tiquetes.forEach((t) => {
        row($("#listaFunciones"), t);
    });
}

function row(container, tiquete){
    var tr = $("<tr />");
    tr.html("<td>" + tiquete.codigo + "</td>" +
            "<td>" + tiquete.usuario.nombre + "</td>" +
            "<td>" + tiquete.funcion + "</td>" +
            "<td>" + tiquete.butacas + "</td>" +
            "<td>" + tiquete.fecha + "</td>" +
            "<td>" + tiquete.pelicula + "</td>" +
            "<td>" + tiquete.sala + "</td>" +
            "<td>" + tiquete.tarjeta + "</td>" +
            "<td>" + tiquete.precio + "</td>" + 
            "<td id='imprimir'><img src='images/pdf.png'></td>");
    tr.find("#imprimir").on('click', ()=>{imprimir(tiquete);});
    container.append(tr);
}

function imprimir(tiquete){
    var doc = new jsPDF();

        doc.setFontSize(55);
        doc.text(35, 25, 'CINEMATRIX');
            
        doc.setFontSize(25);
        doc.text(35, 45, 'Datos del cliente');
        doc.setFontSize(15);
        doc.text(35, 65, "Cedula: " + tiquete.usuario.cedula);
        doc.text(35, 75, "Nombre: " + tiquete.usuario.nombre);
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
        
        doc.save("tiquete" + tiquete.usuario.cedula + tiquete.funcion.toString() + tiquete.pelicula + ".pdf");
}

function fetchAndList(){
    let request = new Request(url + 'api/tiquetes', {method: 'GET', headers: {}});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#buscarDiv #errorDiv"));
            return;
        }
        tiquetes = await response.json();
        list();
    })();
}

function fetchAndListCliente(user){
    let request = new Request(url + 'api/tiquetes?nombre=' + user.nombre, {method: 'GET', headers: {}});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#buscarDiv #errorDiv"));
            return;
        }
        tiquetes = await response.json();
        list();
    })();
}


function loaded() {
    
    let request = new Request(url + 'api/tiquetes/usuario', {method: 'GET', headers: {}});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            errorMessage(response.status, $("#buscarDiv #errorDiv"));
            return;
        }
        usuario = await response.json();
        
        if(usuario.perfil === "CLI"){
            fetchAndListCliente(usuario);
        }
        else{
            fetchAndList();
        }
    })();
    
    
}

$(loaded);


