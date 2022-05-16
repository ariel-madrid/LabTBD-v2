<template>
  <div class="main">
    <div class="contenedor">
     <div class="title">
        <h1>
          Plataforma Ayni
        </h1>
      </div>
    </div>
    <ul class="ul-cards">
      <li style="--accent-color: #68afff">
        <div class="icon">
          <i class="fa-solid fa-user" />
        </div>
        <div class="title-1"> Emergencias</div>
        <div class="table-container">
            <table class="main-table">
                    <tr v-for="(row, index) in emergencias" :key="index">
                        <td>{{ emergencias[index].id }}</td>
                        <td>{{ emergencias[index].nombre }}</td>
                        <td>{{ emergencias[index].ubicacion }}</td>
                        <td>
                            <nuxt-link :to="{ name: 'verEmergencia', 
                                              path: '/verEmergencia',
                                              params: { username: userLogeado, emergencia: emergencias[index].id }}">
                                <img width="25" height="25" :src="joinLogo" />
                            </nuxt-link>
                        </td>                    
                    </tr>
            </table>
        </div>

        <button type="button" class="button-34" @click="crearTarea">Crear nueva tarea</button>

      </li>
    </ul>
  </div>
</template>
<script>
import joinLogo from '../static/arrow-basic-ui-svgrepo-com.svg'
export default ({
    data(){
        return {
            userLogeado: {},
            emergencias: [],
            joinLogo,
        }
    },
    methods: {
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
        },
        crearTarea()
        {
          this.$router.push({ name: 'crearTarea', path: '/crearTarea'})
        }
    },
    //Función que se ejecuta al cargar el componente
  created: async function() {
      
      let username = this.$route.params.username;
      this.userLogeado = username;
      
  },
  mounted: async function(){
           try {
            console.log(this.emergencias)
            let response = await this.$axios.get('http://localhost:8080/emergencias');
            this.emergencias = response.data;
            console.log(this.emergencias)
            console.log(response) 
            } catch (error) {
                console.log('error', error);
            } 
        },
})
</script>

<style>
  @import "@/assets/styles/verEmergencias.css";
</style>