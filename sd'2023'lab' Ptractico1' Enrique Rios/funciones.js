let contador = 0;

const contenedor_nuevo = document.getElementById("rectangulo_Nuevo");
const contenedor_Haciendo = document.getElementById("rectangulo_Haciendo");
const contenedor_Hecho = document.getElementById("rectangulo_Hecho");

function crearTarea(titulo, descripcion, fecha, estado, id) {
  //Propiedades y clases de la tarea
  const tarea = document.createElement("div");
  tarea.classList.add("rectangulo");
  tarea.classList.add("tarea_cuerpo");
  tarea.classList.add("Contenido_CentradoColumna");
  tarea.draggable = true;
  //tarea.textContent = `Tarea ${contador} ${tarea_Titulo}`;
  tarea.id = id;
  //Le doy la propiedad para poder transferir sus ids
  tarea.addEventListener("dragstart", (e) => {
    e.dataTransfer.setData("text/plain", tarea.id);
  });
  //--------Terminamos de cargar.

  //Cargamos el contenido en cada tarea.
  const t_t = document.createElement("h1");
  t_t.classList.add("tarea_titulo");
  t_t.textContent = titulo;
  t_t.id = `t_Titulo${id}`;
  const t_d = document.createElement("i");
  t_d.classList.add("tarea_Descripcion");
  t_d.textContent = descripcion;
  t_d.id = `t_Descripcion${id}`;
  const t_f = document.createElement("b");
  t_f.textContent = fecha;
  t_f.id = `t_Fecha${id}`;
  tarea.appendChild(t_t);
  tarea.appendChild(t_d);
  tarea.appendChild(t_f);
  //---------- Contenido cargado

  if (estado === "nuevo") {
    contenedor_nuevo.appendChild(tarea);
  } else if (estado === "haciendo") {
    contenedor_Haciendo.appendChild(tarea);
  } else if (estado === "hecho") {
    contenedor_Hecho.appendChild(tarea);
  }
}

//Este boton solo se puede usar una vez haya cargado
//la pagina y eso significa que ya habran cargado
//todas las tareas guardadas en localStorage.
document.getElementById("crearTarea").addEventListener("click", () => {
  //Optenemos el texto que ira en la tarea
  const tarea_Titulo = document.getElementById("tarea_titulo").value;
  if (tarea_Titulo.trim() === "") {
    //Esta vacio el texto
    return;
  }
  const tarea_Descripcion = document.getElementById("tarea_descripcion").value;
  const tarea_Fecha = document.getElementById("tarea_fecha").value;
  //----Terminamos de leer datos del DOM

  crearTarea(tarea_Titulo, tarea_Descripcion, tarea_Fecha, "nuevo", contador);

  const tareaData = {
    titulo: tarea_Titulo,
    descripcion: tarea_Descripcion,
    fecha: tarea_Fecha,
    estado: "nuevo",
    id: contador,
  };
  //Guardamos el valor actual del contador.
  //if (contador == 1) {
     // localStorage.setItem("contador", contador.toString());
   // }else{
       localStorage.setItem("contador", contador.toString());
       //}
       localStorage.setItem(contador.toString(), JSON.stringify(tareaData));
       contador++;
});
//

//--------------------------Codigo de drag and drop

/*contenedor de Nuevo*/
contenedor_nuevo.addEventListener("dragover", (e) => {
  e.preventDefault(); // Permite soltar elementos aquí
});

contenedor_nuevo.addEventListener("drop", (e) => {
  e.preventDefault();
  //Optiene el  id en formato texto y plano, como
  //seteamos que el dato a transferir seria el id, es ese el que nos da.
  const tareaId = parseInt(e.dataTransfer.getData("text/plain"));
  const tarea = document.getElementById(tareaId);
  contenedor_nuevo.appendChild(tarea);

  /*
      Necesito optener la tarea a travez de su ID, 
      luego crear un objeto Tarea Data,[en este caso tareaObj]
      actualizar el estado
      volver a guardarlo en el localSorage.
     */
  const tareaData = localStorage.getItem(tareaId);
  if (tareaData) {
    const tareaObj = JSON.parse(tareaData);
    tareaObj.estado = "nuevo";
    localStorage.setItem(tareaId, JSON.stringify(tareaObj));
  }
});

/*contenedor de haciendo*/
contenedor_Haciendo.addEventListener("dragover", (e) => {
  e.preventDefault();
});

contenedor_Haciendo.addEventListener("drop", (e) => {
  e.preventDefault();
  const tareaId = parseInt(e.dataTransfer.getData("text/plain"));
  const tarea = document.getElementById(tareaId);
  contenedor_Haciendo.appendChild(tarea);

  const tareaData = localStorage.getItem(tareaId);
  if (tareaData) {
    const tareaObj = JSON.parse(tareaData);
    tareaObj.estado = "haciendo";
    localStorage.setItem(tareaId, JSON.stringify(tareaObj));
  }
});

/*contenedor de hecho*/
contenedor_Hecho.addEventListener("dragover", (e) => {
  e.preventDefault();
});

contenedor_Hecho.addEventListener("drop", (e) => {
  e.preventDefault();
  const tareaId = parseInt(e.dataTransfer.getData("text/plain"));
  const tarea = document.getElementById(tareaId);
  contenedor_Hecho.appendChild(tarea);

  const tareaData = localStorage.getItem(tareaId);
  if (tareaData) {
    const tareaObj = JSON.parse(tareaData);
    tareaObj.estado = "hecho";
    localStorage.setItem(tareaId, JSON.stringify(tareaObj));
  }
});

window.addEventListener("load", () => {
  //Cargamos el contador del contador.
  contador = parseInt(localStorage.getItem("contador"));
  if (isNaN(contador)) {
    contador = 1;
  }

  for (let i = 1; i <= contador + 1; i++) {
    //Vamos cargando
    const tareaData = localStorage.getItem(`${i}`);
    if (tareaData) {
      const { titulo, descripcion, fecha, estado, id } = JSON.parse(tareaData);
      crearTarea(titulo, descripcion, fecha, estado, id);
    }
  }
});
