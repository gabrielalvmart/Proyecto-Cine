var url = "http://localhost:8080/Cinematrix/";
var ticketPrice = 5;
var funcion = {pelicula: "", sala: "", fecha: 0, precio:0};

function render(){
    $("#movieLabel").html(funcion.pelicula);
}


function select(e){
    if (
    e.target.classList.contains("seat") &&
    !e.target.classList.contains("occupied")
  ) {
    e.target.classList.toggle("selected");
    actualizarReserva();  }
}

function actualizarReserva(){
    const selectedSeats = document.querySelectorAll(".row-seats .selected");
    const selectedSeatsCount = selectedSeats.length;
    $("#count").html(selectedSeatsCount);
    $("#total").html(selectedSeatsCount * ticketPrice);
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
        $('reservaAsiento').click();
        $('#cinema').click((e)=>{
            select(e);
        }); 
        render();
    })();
}

$(loadReserva);





/*const container = document.querySelector(".container");
const seats = document.querySelectorAll(".row .seat:not(.occupied)");
const count = document.getElementById("count");
const total = document.getElementById("total");
const movieSelect = document.getElementById("movie");

let ticketPrice = parseInt(movieSelect.value);

function updateSelectedCount() {
  const selectedSeats = document.querySelectorAll(".row .selected");
  const selectedSeatsCount = selectedSeats.length;
  count.innerHTML = selectedSeatsCount;
  total.innerHTML = selectedSeatsCount * ticketPrice;
}

movieSelect.addEventListener("change", (e) => {
  ticketPrice = parseInt(e.target.value);
  updateSelectedCount();
});

container.addEventListener("click", (e) => {
  console.log(e);
  if (
    e.target.classList.contains("seat") &&
    !e.target.classList.contains("occupied")
  ) {
    e.target.classList.toggle("selected");
    updateSelectedCount();
  }
});
*/