document.addEventListener('DOMContentLoaded', cargarVehiculos);

//crea el array vacio de vehiculos
let todosLosVehiculos = [];
//hace que la pagina inicial sea la primera
let paginaActual = 1;
//crea una constante para saber cuantas tarjetas mostrar por pagina 
const VEHICULOS_POR_PAGINA = 6;

//función que carga los datos desde el archivo JSON y muestra los vehiculos con la paginacion
async function cargarVehiculos() {
  const respuesta = await fetch('../json/alquilados.json'); //se recoge la información del archivo JSON
  const vehiculos = await respuesta.json(); //se convierte la respuesta a formato JSON
  todosLosVehiculos = vehiculos; //se guarda la lista completa en el array global
  mostrarVehiculosPaginados(vehiculos, paginaActual); //se muestran los vehículos correspondientes a la página actual
  crearPaginacion(vehiculos); //se generan los botones de paginación
}

//funcion que devuelve el estado del vehuclo, segun la cercania con el mantenimiento
function getEstadoMantenimiento(proxMantenimiento) {
  const hoy = new Date();
  const fechaMant = new Date(proxMantenimiento);
  const diferenciaDias = Math.floor((fechaMant - hoy) / (1000 * 60 * 60 * 24));
  if (diferenciaDias < 0) return 'mantenimiento';
  if (diferenciaDias <= 30) return 'cerca-mantenimiento';
  return '';
}

//función que muestra en pantalla los vehículos correspondientes a una página concreta
function mostrarVehiculosPaginados(lista, pagina) {
  const contenedor = document.querySelector('.vehiculos-container'); //se selecciona el contenedor donde irán las tarjetas
  contenedor.innerHTML = ''; //se limpia el contenido anterior

  //se calcula el índice del primer y último vehículo que se mostrará en esta página
  const inicio = (pagina - 1) * VEHICULOS_POR_PAGINA;
  const fin = inicio + VEHICULOS_POR_PAGINA;

  //se obtiene un subconjunto del array con los vehículos que deben mostrarse en esta página
  const vehiculosPagina = lista.slice(inicio, fin);
  //crea la tarjeta para cada vehiculo de la lista
  vehiculosPagina.forEach(vehiculo => {
    const tarjeta = document.createElement('div');
    const estado = getEstadoMantenimiento(vehiculo.proxMantenimiento);
    tarjeta.classList.add('vehiculo-card');
    if (estado) tarjeta.classList.add(estado); //añade la clase dependiendo el proximo mantenimiento

    const subtipo = vehiculo.tipoTurismo || vehiculo.tipoFurgo;
    const tipo = vehiculo.tipo.toLowerCase();
    const etiquetaHTML = (tipo === 'turismo' || tipo === 'furgoneta') && subtipo
      ? `<span class="etiqueta ${subtipo.toLowerCase()}">${subtipo.charAt(0).toUpperCase() + subtipo.slice(1)}</span>`
      : '';
    //rellena el interior de la targeta con el codigo html y los datos del vehiculo
    tarjeta.innerHTML = `
      <div class="vehiculo-imagen">
        <img src="fotos/${vehiculo.modelo}.jpeg" alt="${vehiculo.marca} ${vehiculo.modelo}">
        ${etiquetaHTML}
      </div>
      <div class="vehiculo-info">
        <h3>${vehiculo.marca} ${vehiculo.modelo}</h3>
        <span class="tipo">${vehiculo.tipo}</span>
        <ul class="caracteristicas">
          <li><strong>Matrícula:</strong><p class="matricula"> ${vehiculo.matricula} </p></li>
          <li><strong>Color:</strong><p class="color"> ${vehiculo.color} </p></li>
          <li><strong>Plazas:</strong> ${vehiculo.plazas}</li>
          <li><strong>Año:</strong> ${vehiculo.fechaMatriculacion.split('-')[0]}</li>
          <li><strong>Precio/hora:</strong> <span class="precio">${vehiculo.precioHora}€</span></li>
        </ul>
        <div class="cliente-info">
          <p><strong>Alquilado por:</strong> ${vehiculo.nombre}</p>
          <p><strong>DNI:</strong> ${vehiculo.dni}</p>
          <p><strong>Email:</strong> ${vehiculo.correo}</p>
        </div>
    `;
    //se añade la tarjeta al contenedor
    contenedor.appendChild(tarjeta);
  });
}

//función que crea los botones de paginación según la cantidad total de vehículos
function crearPaginacion(lista) {
  const paginacion = document.querySelector('.paginacion-container'); //se selecciona el contenedor de la paginación
  paginacion.innerHTML = ''; //se limpia la paginación anterior

  //se calcula cuántas páginas hacen falta en total
  const totalPaginas = Math.ceil(lista.length / VEHICULOS_POR_PAGINA);

  //se crean los botones de la paginación uno por uno
  for (let i = 1; i <= totalPaginas; i++) {
    const boton = document.createElement('button'); 
    boton.textContent = i; 
    boton.classList.add('boton-pagina');

    //si es la página actual, se marca como activa
    if (i === paginaActual) boton.classList.add('activo');

    //cuando se hace click en un botón, se actualiza la página y se regeneran los datos
    boton.addEventListener('click', () => {
      paginaActual = i; //se cambia a la página seleccionada
      mostrarVehiculosPaginados(lista, paginaActual); //se muestran los nuevos vehículos
      crearPaginacion(lista); //se actualizan los botones
    });

    paginacion.appendChild(boton);
  }
}


//filtros por tipo
document.querySelector('.boton-filtrar').addEventListener('click', () => {
  const tipoSeleccionado = document.getElementById('tipo').value;
  let filtrados = todosLosVehiculos;

  if (tipoSeleccionado !== 'todos') {
    filtrados = todosLosVehiculos.filter(vehiculo =>
      vehiculo.tipo.toLowerCase() === tipoSeleccionado.toLowerCase()
    );
  }

  paginaActual = 1;
  mostrarVehiculosPaginados(filtrados, paginaActual);
  crearPaginacion(filtrados);
});