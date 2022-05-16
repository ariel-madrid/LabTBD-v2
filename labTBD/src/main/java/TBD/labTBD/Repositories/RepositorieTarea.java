package TBD.labTBD.Repositories;
import java.util.List;
import TBD.labTBD.Models.Tarea;

//Interfaz que define los metodos del CRUD
public interface RepositorieTarea{
    public List<Tarea> getAll(); // read
    public void update(Tarea tarea);
    public void delete(int id);
    public Tarea createTarea(Tarea tarea);
    public Tarea getById(int id);
}
