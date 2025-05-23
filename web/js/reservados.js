let todosLosVehiculos = [];
  let paginaActual = 1;
  const VEHICULOS_POR_PAGINA = 10;

  async function cargarVehiculos() {
    const respuesta = await fetch('../json/alquilados.json');
    const vehiculos = await respuesta.json();
    todosLosVehiculos = vehiculos;
    mostrarVehiculosPaginados(vehiculos, paginaActual);
    crearPaginacion(vehiculos);
  }

  function getEstadoMantenimiento(proxMantenimiento) {
    const hoy = new Date();
    const fechaMant = new Date(proxMantenimiento);
    const dias = Math.floor((fechaMant - hoy) / (1000 * 60 * 60 * 24));
    if (dias < 0) return 'mantenimiento';
    if (dias <= 30) return 'cerca-mantenimiento';
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
          <div class="cliente-info">
            <p><strong>Alquilado por:</strong> ${vehiculo.nombre}</p>
            <p><strong>DNI:</strong> ${vehiculo.dni}</p>
            <p><strong>Email:</strong> ${vehiculo.correo}</p>
          </div>
        </div>
      `;
      contenedor.appendChild(tarjeta);
    });
  }

  function crearPaginacion(lista) {
    const paginacion = document.querySelector('.paginacion-container') || document.createElement('div');
    paginacion.className = 'paginacion-container';
    if (!document.body.contains(paginacion)) document.body.appendChild(paginacion);

    paginacion.innerHTML = '';
    const totalPaginas = Math.ceil(lista.length / VEHICULOS_POR_PAGINA);
    for (let i = 1; i <= totalPaginas; i++) {
      const btn = document.createElement('button');
      btn.textContent = i;
      btn.className = 'boton-pagina' + (i === paginaActual ? ' activo' : '');
      btn.onclick = () => {
        paginaActual = i;
        mostrarVehiculosPaginados(lista, i);
        crearPaginacion(lista);
      };
      paginacion.appendChild(btn);
    }
  }

  document.addEventListener('DOMContentLoaded', cargarVehiculos);

  document.querySelector('.boton-filtrar').addEventListener('click', () => {
    const tipo = document.getElementById('tipo').value;
    const filtrados = tipo === 'todos'
      ? todosLosVehiculos
      : todosLosVehiculos.filter(v => v.tipo.toLowerCase() === tipo.toLowerCase());
    paginaActual = 1;
    mostrarVehiculosPaginados(filtrados, paginaActual);
    crearPaginacion(filtrados);
  });