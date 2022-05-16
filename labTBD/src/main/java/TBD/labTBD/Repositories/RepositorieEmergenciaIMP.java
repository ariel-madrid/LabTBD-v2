package TBD.labTBD.Repositories;

import java.util.List;
import TBD.labTBD.Models.Emergencia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

@Repository
public class RepositorieEmergenciaIMP implements RepositorieEmergencia {

    @Autowired
    private Sql2o sql2o;

    @Override
    public List<Emergencia> getAll() {
        String sql = "SELECT * FROM emergencia";
        try (Connection conn = sql2o.open()) {
            return (List<Emergencia>) conn.createQuery(sql).executeAndFetch(Emergencia.class);
        } catch (Exception e) {
            System.out.println(e.getMessage() + e.getLocalizedMessage() + " Error en la conexion \n");
        }
        return null;
    }

    @Override
    public void update(Emergencia EmergenciaUpdate) {
        String sql = "UPDATE emergencia SET nombre=" + "'" +
                EmergenciaUpdate.getNombre() + "'" +
                ", descripcion=" + "'" +
                EmergenciaUpdate.getDescripcion() + "'" +
                ", id_coordinador =" + "'" +
                EmergenciaUpdate.getId_coordinador() + "'" +
                ", ubicación =" + "'" +
                EmergenciaUpdate.getUbicacion() + "'" +
                ", fecha =" + "'" +
                EmergenciaUpdate.getFecha() + "'" +
                " WHERE id = " +
                EmergenciaUpdate.getId();
        EmergenciaUpdate.getId();
        System.out.println(sql);
        try (Connection conn = sql2o.open()) {

            conn.createQuery(sql).executeUpdate();
        } catch (Exception e) {
            System.out
                    .println(e.getMessage() + e.getLocalizedMessage() + " Error al actualizar datos de la Habilidad\n");
        }

    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM emergencia WHERE id=" + id;
        try (Connection conn = sql2o.open()) {
            conn.createQuery(sql).executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage() + e.getLocalizedMessage() + " error al elminar la habilidad\n");
        }

    }

    @Override
    public Emergencia crearEmergencia(Emergencia EmergenciaNew) {
        String sql = "INSERT INTO emergencia (nombre, descripcion, id_coordinador, fecha, ubicacion) VALUES(:nombre, :descripcion, :id_coordinador, :fecha, :ubicacion)";

        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .addParameter("nombre", EmergenciaNew.getNombre())
                    .addParameter("descripcion", EmergenciaNew.getDescripcion())
                    .addParameter("id_coordinador", EmergenciaNew.getId_coordinador())
                    .addParameter("fecha_inicio", EmergenciaNew.getFecha())
                    .addParameter("ubicacion", EmergenciaNew.getUbicacion())
                    .executeUpdate().getKey();

            EmergenciaNew.setId(id);

            return EmergenciaNew;

        } catch (Exception e) {
            System.out.println(e.getCause() + e.getLocalizedMessage() + "\n");
        }
        return null;

    }

    @Override
    public Emergencia getById(int id) {
        String sql = "SELECT * FROM emergencia WHERE id=" + Integer.toString(id);

        try (Connection conn = sql2o.open()) {
            List<Emergencia> E = conn.createQuery(sql).executeAndFetch(Emergencia.class);
            return E.get(0);

        } catch (Exception e) {
            System.out.println(e.getMessage() + e.getLocalizedMessage()
                    + "Error al realizar la peticion de obtener una Emergencia por ID\n");
        }
        return null;

    }

}
