var menu = `<header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand title-icon" href="index.html"><img src="/Cinematrix/images/popcorn.png"
                                                                                              alt="icon"> Cinematrix</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="index.html">Home <span class="sr-only">(current)</span></a>
      </li>
        <li class="nav-item">
            <a class="nav-link disable" href="#" data-toggle='modal' data-target='#aboutUsDialog'>About us</a>
        </li>`;

        let usuarioJson = sessionStorage.getItem('user');
        let perfil = "Cliente";
        
        if (usuarioJson!=null){
            let usuario= JSON.parse(usuarioJson);
            
            if (usuario.perfil == 'ADM') {
                perfil = "Administrador";
                menu += `
                    <li class='nav-item dropdown'>
                      <a class='nav-link dropdown-toggle' data-toggle='dropdown' href='#'>Administrar Cine</a>
                      <div class='dropdown-menu'>
                        <a class='dropdown-item' href='salas.html'>Administrar Salas</a>
                        <a class='dropdown-item' href='peliculas.html'>Administrar Películas</a>
                        <a class='dropdown-item' href='funciones.html'>Programar Funciones</a>
                        <a class='dropdown-item' href='tiquetes.html'>Imprimir Tiquetes</a>
                      </div>
                    </li>`;
            }
            
            menu += `
                    <li class='nav-item dropdown'>
                      <a class='nav-link dropdown-toggle' data-toggle='dropdown' href='#'>${usuario.nombre} (${perfil})</a>
                      <div class='dropdown-menu'>`;
                      if(usuario.perfil == 'CLI'){
                          menu += `<a class='dropdown-item' href='tiquetes.html'>Ver Compras</a>`;
                      }
                        menu += `<a class='dropdown-item' href='#' id='logout'>Salir</a>
                      </div>
                    </li>`;
        }
        else {
            menu += `
                    <li class="nav-item">
                        <a class="nav-link disable" href="#" data-toggle='modal' data-target='#registroDialog'>Registrarse</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link disable" href="#" data-toggle='modal' data-target='#loginDialog'>Login</a>
                    </li>`;
        }
        
        menu += `
        </ul>`;

        if (document.location == 'http://localhost:8080/Cinematrix/' || document.location == 'http://localhost:8080/Cinematrix/index.html') {
            menu += `<div class="form-inline my-2 my-lg-0">
                        <input class="form-control mr-sm-2" type="text" placeholder="Search Movies" aria-label="Search" id="filtradopelicula">
                        <button class="btn text-light bg-success my-2 my-sm-0" type="submit" id="buscapelicula">Search Movies</button>
                    </div>`;
        }
        
        menu += `
  </div>
</nav>
</header>`;
        
function loadNavbar() {
    $('body').prepend(menu);
}

$(loadNavbar);