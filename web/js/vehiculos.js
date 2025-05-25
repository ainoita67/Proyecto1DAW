let todosLosVehiculos = [];
  let paginaActual = 1;
  const VEHICULOS_POR_PAGINA = 6;

  async function cargarVehiculos() {
    const respuesta = await fetch('../json/vehiculos.json');
    const vehiculos = await respuesta.json();
    todosLosVehiculos = vehiculos;
    mostrarVehiculosPaginados(vehiculos, paginaActual);
    crearPaginacion(vehiculos);
  }

  function getEstadoMantenimiento(proxMantenimiento) {
    const hoy = new Date();
    const fechaMant = new Date(proxMantenimiento);
    const diferenciaDias = Math.floor((fechaMant - hoy) / (1000 * 60 * 60 * 24));
    if (diferenciaDias < 0) return 'mantenimiento';
    if (diferenciaDias <= 30) return 'cerca-mantenimiento';
    return '';
  }

  function mostrarVehiculosPaginados(lista, pagina) {
    const contenedor = document.querySelector('.vehiculos-container');
    contenedor.innerHTML = '';
    const inicio = (pagina - 1) * VEHICULOS_POR_PAGINA;
    const fin = inicio + VEHICULOS_POR_PAGINA;
    const vehiculosPagina = lista.slice(inicio, fin);

    vehiculosPagina.forEach(vehiculo => {
      const tarjeta = document.createElement('div');
      const estado = getEstadoMantenimiento(vehiculo.proxMantenimiento);
      tarjeta.classList.add('vehiculo-card');
      if (estado) tarjeta.classList.add(estado);

      const subtipo = vehiculo.tipoTurismo || vehiculo.tipoFurgo;
      const tipo = vehiculo.tipo.toLowerCase();
      const etiquetaHTML = (tipo === 'turismo' || tipo === 'furgoneta') && subtipo
        ? `<span class="etiqueta ${subtipo.toLowerCase()}">${subtipo.charAt(0).toUpperCase() + subtipo.slice(1)}</span>`
        : '';

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
      `;
      contenedor.appendChild(tarjeta);
    });
  }

  function crearPaginacion(lista) {
    const paginacion = document.querySelector('.paginacion-container');
    paginacion.innerHTML = '';
    const totalPaginas = Math.ceil(lista.length / VEHICULOS_POR_PAGINA);

    for (let i = 1; i <= totalPaginas; i++) {
      const boton = document.createElement('button');
      boton.textContent = i;
      boton.classList.add('boton-pagina');
      if (i === paginaActual) boton.classList.add('activo');

      boton.addEventListener('click', () => {
        paginaActual = i;
        mostrarVehiculosPaginados(lista, paginaActual);
        crearPaginacion(lista);
      });

      paginacion.appendChild(boton);
    }
  }

  document.addEventListener('DOMContentLoaded', cargarVehiculos);

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