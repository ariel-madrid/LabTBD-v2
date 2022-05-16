package TBD.labTBD.Repositories;

import java.util.List;
import TBD.labTBD.Models.Institucion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;




@Repository
public class RepositorieInstitucionIMP implements RepositorieInstitucion
{
    @Autowired 
    private Sql2o sql2o;
    
    //Obtener todas las instituciones (Metodo GET)
    @Override
    public List<Institucion> getAll() {
        String sql = "SELECT * FROM institucion";
        try(Connection conn = sql2o.open()){
            return (List<Institucion>) conn.createQuery(sql).executeAndFetch(Institucion.class);
        } catch(Exception e) {
            System.out.println(e.getMessage() + e.getLocalizedMessage()+" Error en la conexion \n");
        }
        return null;
    }
    
    //Actualizar una institucion
    @Override
    public void update(Institucion institucion) {
        String sql = "UPDATE institucion SET nombre="+"'"+
                    institucion.getNombre()+"'"+
                    ", coordinador="+"'"+
                    institucion.getCoordinador()+"'"+
                    ", clave_Coordinador="+"'"+
                    institucion.getClaveCoordinador()+"'"+
                    "WHERE id = "+
                    institucion.getId();
        System.out.println(sql);
        try(Connection conn = sql2o.open())
        {
            conn.createQuery(sql).executeUpdate();
        }catch(Exception e)
        {
            System.out.println(e.getMessage() + e.getLocalizedMessage()+" Error al actualizar la institucion\n");
        }

    }

    //Eliminar una institucion
    @Override
    public void delete(int id) {
        String sql = "DELETE FROM institucion WHERE id="+id;
        try(Connection conn = sql2o.open())
        {
            conn.createQuery(sql).executeUpdate();
        }catch(Exception e)
        {
            System.out.println(e.getMessage() + e.getLocalizedMessage()+" Error al eliminar una institucion\n");
        }
    }

    //La verificacion si existe una institucion para ser creada se realiza en el front
    @Override
    public Institucion createInstitucion(Institucion institucion) {
        String sql = "INSERT INTO institucion (id, nombre, coordinador, clave_Coordinador) VALUES(:id, :nombre, :coordinador, :clave_Coordinador)";
    
        try(Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
            .addParameter("id", institucion.getId())
            .addParameter("nombre", institucion.getNombre())
            .addParameter("coordinador", institucion.getCoordinador())
            .addParameter("clave_Coordinador", institucion.getClaveCoordinador())
            .executeUpdate().getKey();

            institucion.setId(id);
            return institucion;  
        }
        catch(Exception e) {
            System.out.println(e.getCause() + e.getLocalizedMessage() + "\n");
        }
        return null;
    }

    //Obtener una institucion por ID
    @Override
    public Institucion getById(int id) {
        String sql = "SELECT * FROM institucion WHERE id="+ Integer.toString(id);

        try(Connection conn = sql2o.open()){
            List<Institucion> l = conn.createQuery(sql).executeAndFetch(Institucion.class);
            return l.get(0);
        } catch(Exception e) {
            System.out.println(e.getMessage() + e.getLocalizedMessage()+" Error al realizar la peticion de obtener una institucion por ID\n");
        }
        return null;
    }
}
