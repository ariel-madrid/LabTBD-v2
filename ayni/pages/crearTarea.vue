<template>
    <div class="main"> 
      <ul class="ul-cards">
        <li style="--accent-color: #68AFFF; height: calc(100vh - 10rem);">
            <div class="icon">
                <i class="fa-solid fa-user" />
            </div>
            <div class="title">
                Nueva Tarea
            </div>
            <div class="content" style="margin-bottom: 3rem;">
              <label for="emergencia">Seleccione emergencia</label>
              <select id="emergencia" name="select">
                <option v-for="(item , index) in emergencias" v-bind:key="index">
                    {{item.id}} - {{item.nombre}}
                </option>
              </select>

              <label for="nombre-tarea">Nombre de la tarea</label>
              <input id="nombre-tarea" v-model="nuevaTarea.nombre" type="text">

              <label for="descripcion">Descripción</label>
              <input id="descripcion" v-model="nuevaTarea.descripcion" type="text">

              <label for="fechaTarea">Fecha de inicio</label>
              <input id="fechaTarea" v-model="nuevaTarea.fechainicio" type="date">

              <label for="ubicacionTarea">Ubicación</label>
              <input id="ubicacionTarea" v-model="nuevaTarea.ubicacion" type="text">

              <div style="grid-column: 1/-1">
                <p style="margin-bottom: 1rem;">Habilidades Requeridas</p>
                <div class="group-habilidades">
                  <div v-for="item in habilidades" v-bind:key="item.id">
                    <label class="label-form">
                      <input type="checkbox"  v-model="user.selecciones" :id="item.id" :value="item.name">
                      <p>{{ item.name }}</p>
                    </label>
                  </div>
                </div>
              </div>
            </div>
            <button type="button" class="button-34" @click="CrearTarea">
              Crear Tarea
            </button>
        </li>
      </ul>
    </div>
</template>
<script>
export default {
  data () {
    return {
      temp: {},
      nuevaTarea: {},
      atributos: [],
      habilidades: [],
      user: {
        selecciones: []
      },
      emergencias: []
    }
  },
  methods: {
    async CrearTarea () {
      try {
        let response = await this.$axios.get("http://localhost:8080/tareas");
        this.temp = response.data;
        
        let id = 0
        for (let i=0;i<=this.temp.length;i++)
        {
          id += 1
        }
        this.nuevaTarea.id = id
        if(this.nuevaTarea.nombre == null ){
            alert("Debe ingresar un nombre para la tarea");
        }
        else if(this.nuevaTarea.descripcion == null){
            alert("Debe ingresar una descripcion para la tarea");
        }
        else if(this.nuevaTarea.fechainicio == null){
            alert("Debe ingresar un fecha de inicio");
        }
        else if(this.nuevaTarea.ubicacion == null){
            alert("Debe ingresar una ubicacion de la tarea")
        }
        else{
          let lista = ''
          
          for(let i = 0; i < this.user.selecciones.length; i++){
            lista = lista + ',' + this.user.selecciones[i]
          }
          this.nuevaTarea.listaHabilidades = lista
          try {
            
            var e = document.getElementById("emergencia");
            var em = e.options[e.selectedIndex].value
            this.nuevaTarea.idEmergencia = em[0]
            this.nuevaTarea.estado = 'Recien Creada - '+this.nuevaTarea.idEmergencia
            await this.$axios
              .post("http://localhost:8080/nueva-tarea", this.nuevaTarea)
              .then((res) => res.data)
              .catch((res) => res);
              
              alert("Se ha creado correctamente la tarea")
              this.$router.push({ name: 'verEmergencias', path: '/verEmergencias'})
          } catch (error) {
            console.log("error", error);
          }
        
        }
      } catch (error) {
        console.log("error", error);
      }
    },
    async getHabilidades () {
      try {
        const response = await this.$axios.get('http://localhost:8080/habilidades')
        this.atributos = response.data
      } catch (error) {
        return error
      }
      this.atributos.forEach((atributo) => {
        this.habilidades.push({ id: atributo.id, name: atributo.nombre })
      })
    },
    async getEmergencias(){
      try {
        console.log(this.emergencias)
        let response = await this.$axios.get('http://localhost:8080/emergencias');
        this.emergencias = response.data;
        console.log(this.emergencias)
        console.log(response) 
        } catch (error) {
        console.log('error', error);
        } 
    }
  },
  created: function () {
    this.getHabilidades()
    this.getEmergencias()
  }
}
</script>

<style>
  @import "@/assets/styles/crearTarea.css";

  .group-habilidades {
    max-height: 70px;
    overflow-y: scroll;
    border: 1px solid black;
    border-radius: 2px;
    width: 95%;
    padding: 5px;
    display: grid;
    justify-content: center;
    grid-template-columns: repeat(3, 1fr);

  }
  .label-form{
    display: flex; 
    flex-direction: row;
    align-items: baseline;
    justify-content: center;
  }
  .label-form > input {
    margin-right: 5px;
  }
</style>
