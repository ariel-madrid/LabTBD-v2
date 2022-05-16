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
                <button role="button" class="button-edit" @click="editar">
                  Editar estado
                </button>
              </td>
            </tr>
          </tbody>
        </table>
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
        emergencia: this.$route.params.tarea
    }
  },
  methods: {
    volver() {
      this.$router.push({ path: '/verEmergencias'})
    },
    editar()
    {
      var estado = prompt('Ingrese nuevo estado: '+this.emergencia)
      var table = document.getElementById('table')
      this.tarea[0].estado = estado

      //Falta recibir los datos desde la vista de ver emergencia
      
      //Realizar UPDATE en el backend
      this.ActualizarTarea()

      //Volver a la pagina de emergencias
      
    },
    async ActualizarTarea() {
      try {
        let response = await this.$axios.put("http://localhost:8080/tarea/actualizar",this.tarea[0]);
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
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         