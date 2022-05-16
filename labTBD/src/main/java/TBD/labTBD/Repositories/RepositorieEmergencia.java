package TBD.labTBD.Repositories;

import java.util.List;
import TBD.labTBD.Models.Emergencia;

//Interfaz que define los metodos del CRUD
public interface RepositorieEmergencia {

    public List<Emergencia> getAll();

    public void update(Emergencia EmergenciaUpdate);

    public void delete(int id);

    public Emergencia crearEmergencia(Emergencia EmergenciaNew);

    public Emergencia getById(int id);
}
