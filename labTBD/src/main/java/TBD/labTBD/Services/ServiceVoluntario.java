package TBD.labTBD.Services;
import TBD.labTBD.Models.*;
import java.util.*;
import TBD.labTBD.Repositories.RepositorieVoluntario;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
public class ServiceVoluntario 
{
    private final RepositorieVoluntario repositorieVoluntario;

    ServiceVoluntario(RepositorieVoluntario repositorieVoluntario){
        this.repositorieVoluntario = repositorieVoluntario;
    }

    //CRUD

    //Crear un nuevo voluntario (Metodo POST)
    @RequestMapping(value = "/nuevo-voluntario", method = RequestMethod.POST)
    public Voluntario createVoluntario(@RequestBody Voluntario voluntario) 
    {
        return repositorieVoluntario.createVoluntario(voluntario);
    }

    //Obtener lista de voluntarios (Metodo GET)
    @RequestMapping(value = "/voluntarios", method = RequestMethod.GET)
    public List<Voluntario> getAllVoluntario(){
        return repositorieVoluntario.getAll();
    }

    //Actualizar datos de un voluntario (Metodo UPDATE)
    @RequestMapping(value = "/voluntario/actualizar",method = RequestMethod.PUT)
    public void actualizarVoluntario(@RequestBody Voluntario voluntario)
    {
        repositorieVoluntario.update(voluntario);
    }

    //Metodo eliminar voluntario (Metodo DELETE)
    @DeleteMapping(value = "/voluntario/eliminar/{id}")
    public void eliminar(@PathVariable(value = "id") int id)
    {
        repositorieVoluntario.delete(id);
    }
}