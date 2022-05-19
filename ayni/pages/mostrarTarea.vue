<template>
  <div class="main">
    <ul class="ul-cards">
      <li style="--accent-color: #68afff">
        <div class="icon">
          <i class="fa-solid fa-user" />
        </div>
        <div class="title-1">Ver tarea</div>
        <table id="table" class="flat-table">
          <tbody>
            <tr id="row">
              <th>Nombre</th>
              <th>Descripcion</th>
              <th>Ubicacion</th>
              <th>Fecha</th>
              <th>Estado</th>
              <th>Accion</th>
            </tr>
            <tr id="row-1" v-for="(row,index) in tarea" v-bind:key="index">
              <td>{{tarea[index].nombre}}</td>
              <td>{{tarea[index].descripcion}}</td>
              <td>{{tarea[index].ubicacion}}</td>
              <td>{{tarea[index].fechainicio}}</td>
              <td>{{tarea[index].estado}}</td>
              <td>
                <button role="button" class="button-edit" @click="editar(index)">
                  Editar Campos
                </button>
              </td>
            </tr>
          </tbody>
        </table>

        <div class="editar" v-if="edit">
          <h3>Editando tarea: {{nombreTareaEditando}}</h3>

          <label for="tarea">Nombre tarea</label>
          <input type="text" v-model = "nuevaTarea.nombre"><br>
          <label for="descripcion">Descripcion tarea</label>
          <input type="text" v-model = "nuevaTarea.descripcion"><br>
          <label for="ubicacion">Ubicacion tarea</label>
          <input type="text" v-model = "nuevaTarea.ubicacion"><br>
          <label for="fecha">Fecha de inicio</label>
          <input type="date" v-model = "nuevaTarea.fechainicio"><br>
          <label for="estado">Estado tarea</label>
          <input type="text" v-model = "nuevaTarea.estado"><br>

          <button type="reset" class="button" @click="GuardarCambios">Guardar cambios</button>
        </div>

        <button type="reset" class="button" @click="volver">Volver</button>
      </li>
    </ul>
  </div>
</template>

<script>
import Vue from "vue";
import axios from "axios";

export default {
  data () {
    return {
        tareas: [],
        tarea: [],
        emergencia: this.$route.params.tarea,
        edit: false,
        nuevaTarea: {},
        cambios:false,
        nombreTareaEditando: null,
        tareaActualizar: null
    }
  },
  methods: {
    volver() {
      this.$router.push({ path: '/verEmergencias'})
    },
    GuardarCambios()
    {
      this.edit = false 

      if (this.nuevaTarea.nombre != null){
        this.tarea[this.tareaActualizar].nombre = this.nuevaTarea.nombre
      }

      if (this.nuevaTarea.descripcion != null)
      {
        this.tarea[this.tareaActualizar].descripcion = this.nuevaTarea.descripcion
      }

      if (this.nuevaTarea.estado != null)
      {
        this.tarea[this.tareaActualizar].estado = this.nuevaTarea.estado
      }

      if (this.nuevaTarea.fechainicio != null)
      {
        this.tarea[this.tareaActualizar].fechainicio = this.nuevaTarea.fechainicio
      }

      if (this.nuevaTarea.ubicacion != null)
      {
        this.tarea[this.tareaActualizar].ubicacion = this.nuevaTarea.ubicacion
      }

      this.nuevaTarea = {}

      //Realizar UPDATE en el backend
      this.ActualizarTarea()
    },
    editar(index)
    {
      this.tareaActualizar = index
      this.edit = true
      this.nombreTareaEditando = this.tarea[index].nombre
    },

    async ActualizarTarea() {
      try {
        let response = await this.$axios.put("http://localhost:8080/tarea/actualizar",this.tarea[this.tareaActualizar]);
      } catch (error) {
        console.log("error", error);
      }
      alert("Datos actualizados correctamente")
    }
  },
  mounted: async function(){
    try {
      let response = await this.$axios.get('http://localhost:8080/tareas');
      this.tareas = response.data;
      } catch (error) {
        console.log('error', error);
    }

    for (let i = 0;i<this.tareas.length;i++)
    {
      if (this.emergencia === parseInt(this.tareas[i].idEmergencia))
      {
        this.tareas[i].estado = this.tareas[i].estado.split(' - ')[0]
        this.tarea.push(this.tareas[i])
      }
    }
  } 
};
</script>

<style                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    >
@import "@/assets/styles/verTarea.css";
</style>
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         