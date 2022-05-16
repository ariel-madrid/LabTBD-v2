package TBD.labTBD.Services;
import TBD.labTBD.Models.*;
import java.util.*;
import TBD.labTBD.Repositories.RepositorieHabilidad;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
public class ServiceHabilidad 
{
    private final RepositorieHabilidad repositorieHabilidad;

    ServiceHabilidad(RepositorieHabilidad repositorieHabilidad){
        this.repositorieHabilidad = repositorieHabilidad;
    }

    //CRUD

    //Crear una nueva habilidad (Metodo POST)
    @RequestMapping(value = "/nueva-habilidad", method=RequestMethod.POST)
    public Habilidad createHabilidad(@RequestBody Habilidad habilidadNew) 
    {
        return repositorieHabilidad.createHabilidad(habilidadNew);
    }

    //Obtener lista de habilidades (Metodo GET)
    @RequestMapping(value = "/habilidades", method = RequestMethod.GET)
    public List<Habilidad> getAllHabilidad(){
        return repositorieHabilidad.getAll();
    }

    //Actualizar datos de una habilidad (Metodo UPDATE)
    @RequestMapping(value="/habilidad/actualizar",method=RequestMethod.PUT)
    public void actualizarHabilidad(@RequestBody Habilidad habilidad)
    {
        repositorieHabilidad.update(habilidad);
    }

    //Metodo eliminar habilidad (Metodo DELETE)
    @DeleteMapping(value="/habilidad/eliminar/{id}")
    public void eliminar(@PathVariable(value = "id") int id)
    {
        repositorieHabilidad.delete(id);
    }
}