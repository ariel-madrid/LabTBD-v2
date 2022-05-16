package TBD.labTBD.Repositories;
import java.util.List;
import TBD.labTBD.Models.Habilidad;

//Interfaz que define los metodos del CRUD
public interface RepositorieHabilidad
{
    public List<Habilidad> getAll(); //Read
    public void update(Habilidad habilidadUpdate);
    public void delete(int id);
    public Habilidad createHabilidad(Habilidad habilidadNew);
    public Habilidad getById(int id);
}
