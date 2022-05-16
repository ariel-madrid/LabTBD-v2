package TBD.labTBD.Repositories;

import java.util.List;
import TBD.labTBD.Models.Habilidad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

@Repository
public class RepositorieHabilidadIMP implements RepositorieHabilidad {

    @Autowired
    private Sql2o sql2o;

    // Obtener todas las habilidades (Metodo GET)
    @Override
    public List<Habilidad> getAll() {
        String sql = "SELECT * FROM habilidad";
        try (Connection conn = sql2o.open()) {
            return (List<Habilidad>) conn.createQuery(sql).executeAndFetch(Habilidad.class);
        } catch (Exception e) {
            System.out.println(e.getMessage() + e.getLocalizedMessage() + " Error en la conexion \n");
        }
        return null;
    }

    // Actualizar una habilidad
    @Override
    public void update(Habilidad habilidadUpdate) {
        String sql = "UPDATE habilidad SET nombre=" + "'" +
                habilidadUpdate.getNombre() + "'" +
                ", descripcion=" + "'" +
                habilidadUpdate.getDescripcion() + "'" +
                " WHERE id = " +
                habilidadUpdate.getId();
        System.out.println(sql);
        try (Connection conn = sql2o.open()) {

            conn.createQuery(sql).executeUpdate();
        } catch (Exception e) {
            System.out
                    .println(e.getMessage() + e.getLocalizedMessage() + " Error al actualizar datos de la Habilidad\n");
        }

    }

    // Eliminar una habilidad
    @Override
    public void delete(int id) {
        String sql = "DELETE FROM habilidad WHERE id=" + id;
        try (Connection conn = sql2o.open()) {
            conn.createQuery(sql).executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage() + e.getLocalizedMessage() + " Error al elminar la habilidad\n");
        }

    }

    // La verificacion si existe una habilidad para ser creada se realiza en el
    // front
    @Override
    public Habilidad createHabilidad(Habilidad habilidadNew) {
        String sql = "INSERT INTO habilidad (nombre, descripcion) VALUES(:nombre, :descripcion)";

        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .addParameter("nombre", habilidadNew.getNombre())
                    .addParameter("descripcion", habilidadNew.getDescripcion())
                    .executeUpdate().getKey();

            habilidadNew.setId(id);
            return habilidadNew;

        } catch (Exception e) {
            System.out.println(e.getCause() + e.getLocalizedMessage() + "\n");
        }
        return null;
    }

    // Obtener una habilidad por ID
    @Override
    public Habilidad getById(int id) {
        String sql = "SELECT * FROM habilidad WHERE id=" + Integer.toString(id);

        try (Connection conn = sql2o.open()) {
            List<Habilidad> l = conn.createQuery(sql).executeAndFetch(Habilidad.class);
            return l.get(0);

        } catch (Exception e) {
            System.out.println(e.getMessage() + e.getLocalizedMessage()
                    + "   Error al realizar la peticion de obtener una habilidad por ID\n");
        }
        return null;
    }

}