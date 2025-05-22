document.addEventListener("DOMContentLoaded", () => {
  fetch("rss.xml")
    .then(response => response.text())
    .then(data => {
      const parser = new DOMParser();
      const xml = parser.parseFromString(data, "application/xml");
      const items = xml.querySelectorAll("item");
      const container = document.getElementById("rss-container");

      if (items.length === 0) {
        container.innerHTML = "No hay noticias por el momento.";
        return;
      }

      let html = "<ul>";
      items.forEach(item => {
        const title = item.querySelector("title").textContent;
        const description = item.querySelector("description").textContent;
        html += `
          <li>
            <h4>${title}</h4>
            <p>${description}</p>
          </li>
        `;
      });
      html += "</ul>";
      container.innerHTML = html;
    })
    .catch(err => {
      console.error("Error al cargar el RSS:", err);
      document.getElementById("rss-container").innerHTML = "Error al cargar las noticias.";
    });
});
