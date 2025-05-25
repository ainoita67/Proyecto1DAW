document.addEventListener("DOMContentLoaded", () => {
  const form = document.getElementById("contacto-form");
  const mensajeEstado = document.getElementById("form-msg");

  form.addEventListener("submit", function (e) {
    e.preventDefault(); // Prevenir el envío real

    let errores = [];

    const nombre = document.getElementById("nombre").value.trim();
    if (!/^[A-Za-zÁÉÍÓÚáéíóúÑñ\s]+$/.test(nombre) || nombre.length < 3) {
      errores.push("El nombre debe tener al menos 3 letras y solo usar letras y espacios.");
    }

    const email = document.getElementById("email").value.trim();
    if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
      errores.push("El correo electrónico no es válido.");
    }

    const telefono = document.getElementById("telefono").value.trim();
    if (telefono !== "" && !/^[0-9\s+()-]{9,15}$/.test(telefono)) {
      errores.push("El teléfono debe tener entre 9 y 15 caracteres válidos.");
    }

    const mensaje = document.getElementById("mensaje").value.trim();
    if (mensaje.length < 10) {
      errores.push("El mensaje debe tener al menos 10 caracteres.");
    }

    // Mostrar resultados
    if (errores.length > 0) {
      mensajeEstado.innerHTML = `<ul style="color: red;"><li>${errores.join("</li><li>")}</li></ul>`;
    } else {
      mensajeEstado.style.color = "green";
      mensajeEstado.innerHTML = "✅ ¡Mensaje enviado correctamente!";
      form.reset();
    }
  });
});
