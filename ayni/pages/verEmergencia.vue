<template>
    <div class="main">
        <div class="title">
            <h1>
            Plataforma Ayni
            </h1>
        </div>
        <ul class="ul-cards">
            <li style="--accent-color: #68afff">
                <div class="icon">
                    <i class="fa-solid fa-user" />
                </div>
                <div class="title-1"> Emergencia {{id_emergencia}}</div> 
                <table id="table" class="flat-table">
                    <tbody>
                        <tr id="row">
                        <th>Nombre</th>
                        <th>Descripcion</th>
                        <th>Ubicacion</th>
                        <th>Fecha</th>
                        <th>Tareas</th>
                        </tr>
                        <tr id="row-1" >
                        <td>{{this.emergencia.nombre}}</td>
                        <td>{{this.emergencia.descripcion}}</td>
                        <td>{{this.emergencia.ubicacion}}</td>
                        <td>{{this.emergencia.fecha}}</td>
                        <td>
                            <nuxt-link :to="{ name: 'mostrarTarea', 
                                              path: '/mostrarTarea',
                                              params: { tarea: this.emergencia.id}}">
                                <img width="25" height="25" :src="editLogo" />
                            </nuxt-link>
                        </td>
                        </tr>
                    </tbody>
                </table>
            </li>
        </ul>
    </div>
</template>

<script>

import editLogo from '../static/edit_icon.svg'
export default ({
    data() {
        return{
            userLogeado: {},
            emergencia: {},
            editLogo,
            id_emergencia: this.$route.params.emergencia
        }
    },
    created: function() {
      
      let username = this.$route.params.username;
      this.userLogeado = username; 
  },
  mounted: async function(){
     try {
        console.log(this.emergencia)
        let response = await this.$axios.get('http://localhost:8080/emergencia/' + this.id_emergencia);
        this.emergencia = response.data;
        console.log(this.emergencia)
        console.log(response) 
        } catch (error) {
            console.log('error', error);
        } 
  },
    
})
</script>
