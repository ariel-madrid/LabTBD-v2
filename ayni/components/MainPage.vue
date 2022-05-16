<!-- Please remove this file from your project -->
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
      <li style="--accent-color: #68AFFF">
        <div class="icon">
          <i class="fa-solid fa-user" />
        </div>
        <div class="title">
          Iniciar Sesión
        </div>
        <div class="content">
          <label for="nombre-inicio">Ingrese su nombre</label>
          <input id="nombre-inicio" v-model="inicio_sesion.name" type="text">
          <label for="pass-inicio">Ingrese su contraseña</label>
          <input id="pass-inicio" v-model="inicio_sesion.password" type="password">
        </div>
        <button type="reset" class="button-34" @click="InicioSesion">
            Iniciar Sesión
        </button>
      </li>
      <li li style="--accent-color: #FFA44B">
        <div class="icon">
          <i class="fa-solid fa-house" />
        </div>
        <div class="title">
          Registrar Voluntario
        </div>
        <div class="content">
          <label for="nombre-registro">Ingrese su nombre</label>
          <input id="nombre-registro" v-model="registro.nombre" type="text">
          <label for="apellido-registro">Ingrese su apellido</label>
          <input id="apellido-registro" v-model="registro.apellido" type="text">
          <label for="correo-registro">Ingrese su correo</label>
          <input id="correo-registro" v-model="registro.correo" type="email">
          <label for="pass-registro">Ingrese su contraseña</label>
          <input id="pass-registro" v-model="registro.contraseña" type="password">
          <label for="direccion-registro">Ingrese su direccion</label>
          <input id="direccion-registro" v-model="registro.direccion" type="text">
          <label for="rut-registro">Ingrese su RUT</label>
          <input id="rut-registro" v-model="registro.rut" type="text">
          <label for="estado-registro">Ingrese su estado</label>
          <input id="estado-registro" v-model="registro.estado" type="text">
        </div>
        <button type="button" class="button-34" @click="RegistrarVoluntario">
            Registrar
        </button>
      </li>
      <li style="--accent-color: #EF6968">
        <div class="icon">
          <i class="fa-solid fa-wifi" />
        </div>
        <div class="title">
          Registrar Institución
        </div>
        <div class="content">
          <label for="nombre-registroIns">Nombre de la institucion</label>
          <input id="registroIns" v-model="registroIns.nombre" type="text">
          <label for="coordinador-registroIns">Nombre del coordinador</label>
          <input id="registroIns"  v-model="registroIns.coordinador" type="text"> 
          <label for="claveCoordinador-registroIns">Clave del coordinador</label>
          <input id="registroIns" v-model="registroIns.claveCoordinador" type="text">       
        </div>
        <button type="button" class="button-34" @click="RegistrarInstitucion">
            Registrar
        </button>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'MainPage',

  data () {
    return {
      user: {},
      inicio_sesion: {},
      registro: {},
      voluntarios: [],
      instituciones: [],
      registroIns: {}
    }
  },
  methods: {
    async getVoluntarios () {
      try {
        const response = await this.$axios.get('http://localhost:8080/voluntarios')
        this.voluntarios = response.data
      } catch (error) {
        return error
      }
    },
    async getInstituciones () {
      try {
        const response = await this.$axios.get('http://localhost:8080/instituciones')
        this.instituciones = response.data
      } catch (error) {
        return error
      }
    },
    InicioSesion () {
      //  Se debe revisar que los datos ingresados esten en la base de datos
      let tmp = 0
      //  Se verifica si es un voluntario
      //  Traemos todos los voluntarios
      if (this.voluntarios.length !== 0) {
        this.voluntarios.forEach((voluntario) => {
          //  Si los datos ingresados corresponden a los registrados en la base de datos se le envia a la vista de voluntarios
          if ((this.inicio_sesion.name === (voluntario.nombre + ' ' + voluntario.apellido)) && (this.inicio_sesion.password === voluntario.contraseña)) {
            //  Enviar a vista de voluntarios
            tmp = 1
            alert('Sesion iniciada como voluntario')
          }
        })        
      }
      //  Si no se le reconoce como voluntario es porque quizas es un coordinador
      if (this.instituciones) {
        this.instituciones.forEach((institucion) => {
          if (this.inicio_sesion.name === institucion.coordinador && this.inicio_sesion.password === institucion.claveCoordinador) {
            tmp = 1
            alert('Sesion iniciada como coordinador')
            this.$router.push({ name: 'verEmergencias', path: '/verEmergencias', params: { username: this.inicio_sesion.name }})
          }
        })
      }
      if (this.instituciones.length != 0)
      {
        if (tmp === 0) {
          alert('Credenciales invalidas')
        }
      }
    },
    async RegistrarVoluntario () {
      //  Se debe verificar que el correo del usuario a registrar no se encuentre ya.
      let flag = 0
      let num = 0
      this.getVoluntarios()
      num = this.voluntarios.length
      this.registro.id = num + 1
      this.voluntarios.forEach((voluntario) => {
        if (voluntario.correo === this.registro.correo) {
          alert('El correo electronico ya se encuentra registrado')
          flag = 1
        }
      })
      if (flag == 0) 
      {
        if (this.registro.nombre != undefined 
            && this.registro.apellido != undefined 
            && this.registro.correo != undefined 
            && this.registro.contraseña != this.registro 
            && this.registro.direccion != undefined 
            && this.registro.rut != undefined 
            && this.registro.estado != undefined)
        {
          console.log(this.registro)
          try {
            await this.$axios
              .post('http://localhost:8080/nuevo-voluntario', this.registro)
              .then(res => res.data)
              .catch(res => res)
            alert('Voluntario registrado correctamente')
          } catch (error) {
            alert('Error al conectar con el servidor')
            return error
          }
        }else 
        {
          alert('Debe rellenar todos los campos para registrar un voluntario')
        }
      }
      this.getVoluntarios()
    },
    
    async RegistrarInstitucion() {
      // Se debe verificar que la institucion a agregar no se encuentre registrada.
      let flag = 0;
      let num = 0;
      this.getInstituciones()
      num = this.instituciones.length
      this.registroIns.id = num + 1
      
      this.instituciones.forEach((institucion) => {
        if (institucion.nombre === this.registroIns.nombre) {
          flag = 1
          alert('El nombre de la institucion ya se encuentra registrado')
        }
      })
      if (flag === 0) {
        try {
          await this.$axios
            .post('http://localhost:8080/nueva-institucion', this.registroIns)
            .then(res => res.data)
            .catch(res => res)
          alert('Institucion registrada correctamente')
        } catch (error) {
          alert('Error al conectar con el servidor')
          return error
        }
      }
      this.getInstituciones()
      console.log(this.registroIns)
      
    }
  },
  created: function ()
  {
    this.getVoluntarios()
    this.getInstituciones()
  }
}
</script>

<style scoped>
  @import '@/assets/styles/main.css';
</style>
