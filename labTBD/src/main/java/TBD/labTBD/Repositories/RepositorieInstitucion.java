package TBD.labTBD.Repositories;
import java.util.List;
import TBD.labTBD.Models.Institucion;

//Interfaz que define los metodos del CRUD
public interface RepositorieInstitucion
{
    public List<Institucion> getAll(); //Read
    public void update(Institucion institucion);
    public void delete(int id);
    public Institucion createInstitucion(Institucion institucion);
    public Institucion getById(int id);
}
