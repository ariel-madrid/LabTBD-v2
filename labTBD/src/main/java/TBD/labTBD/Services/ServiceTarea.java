package TBD.labTBD.Services;
import TBD.labTBD.Models.Tarea;
import TBD.labTBD.Repositories.RepositorieTarea;

import java.util.*;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ServiceTarea {
    private final RepositorieTarea repositorio;

    ServiceTarea(RepositorieTarea r){
        this.repositorio = r;
    }

    //CRUD

    //Crear una nueva taera (Metodo POST)
    @RequestMapping(value = "/nueva-tarea", method=RequestMethod.POST)
    public Tarea createHabilidad(@RequestBody Tarea tarea) 
    {
        
        return repositorio.createTarea(tarea);
    }

    //Obtener lista de tareas (Metodo GET)
    @RequestMapping(value = "/tareas", method = RequestMethod.GET)
    public List<Tarea> getAllHabilidad(){

        return repositorio.getAll();
    }

    //Actualizar datos de una tarea (Metodo UPDATE)
    @RequestMapping(value="/tarea/actualizar",method=RequestMethod.PUT)
    public void actualizarTarea(@RequestBody Tarea habilidad)
    {
        repositorio.update(habilidad);
    }

    //Metodo eliminar tarea (Metodo DELETE)
    @DeleteMapping(value="/tarea/eliminar/{id}")
    public void eliminar(@PathVariable(value = "id") int id)
    {
        repositorio.delete(id);
    }
}