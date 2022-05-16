package TBD.labTBD.Services;
import TBD.labTBD.Models.*;
import java.util.*;
import TBD.labTBD.Repositories.RepositorieInstitucion;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
public class ServiceInstitucion 
{
    private final RepositorieInstitucion repositorieInstitucion;

    ServiceInstitucion(RepositorieInstitucion repositorieInstitucion){
        this.repositorieInstitucion = repositorieInstitucion;
    }

    //CRUD

    //Crear una nueva institucion (Metodo POST)
    @RequestMapping(value = "nueva-institucion", method = RequestMethod.POST)
    public Institucion createInstitucion(@RequestBody Institucion institucion)
    {
        return repositorieInstitucion.createInstitucion(institucion);
    }

    //Obtener lista de instituciones (Metodo GET)
    @RequestMapping(value = "/instituciones", method = RequestMethod.GET)
    public List<Institucion> getAllInstitucion(){
        return repositorieInstitucion.getAll();
    }

    //Actualizar datos de una institucion (Metodo UPDATE)
    @RequestMapping(value = "/institucion/actualizar", method = RequestMethod.PUT)
    public void actualizarInstitucion(@RequestBody Institucion institucion)
    {
        repositorieInstitucion.update(institucion);
    }

    //Metodo eliminar institucion (Metodo DELETE)
    @DeleteMapping(value = "/institucion/eliminar/{id}")
    public void eliminar(@PathVariable(value = "id") int id)
    {
        repositorieInstitucion.delete(id);
    } 
}
