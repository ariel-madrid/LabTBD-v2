package TBD.labTBD.Repositories;

import java.util.List;
import TBD.labTBD.Models.Voluntario;

//Interfaz que define los metodos del CRUD
public interface RepositorieVoluntario {
    public List<Voluntario> getAll(); // Read

    public void update(Voluntario voluntario);

    public void delete(int id);

    public Voluntario createVoluntario(Voluntario voluntario);

    public Voluntario getById(int id);
}