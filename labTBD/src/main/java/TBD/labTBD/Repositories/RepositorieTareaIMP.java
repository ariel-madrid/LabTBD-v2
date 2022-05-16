package TBD.labTBD.Repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import TBD.labTBD.Models.Tarea;
@Repository
public class RepositorieTareaIMP implements RepositorieTarea{
    @Autowired 
    private Sql2o sql2o;

    
    @Override
    public List<Tarea> getAll() {
        String sql = "SELECT * FROM tarea";

        try(Connection conn = sql2o.open()){
            return (List<Tarea>) conn.createQuery(sql).executeAndFetch(Tarea.class);   
        } catch (Exception e) {
            System.out.println(e.getMessage() + e.getLocalizedMessage()+" Error en la conexion \n");
        }
        return null;
    }

    @Override
    public void update(Tarea tarea) {
        String sql = "UPDATE tarea SET nombre="+"'"+
        tarea.getNombre()+"'"+
        ", descripcion="+"'"+
        tarea.getDescripcion()+"'"+
        ", estado="+"'"+
        tarea.getEstado()+"'"+
        ", fechainicio="+"'"+
        tarea.getFechaInicio()+"'"+
        ", ubicacion="+"'"+
        tarea.getUbicacion()+"'"+
        " WHERE id = "+
        tarea.getId();
        
        System.out.println(sql);

        try(Connection conn = sql2o.open()){
            conn.createQuery(sql).executeUpdate();
        }catch(Exception e){
            System.out.println(e.getMessage() + e.getLocalizedMessage()+" Error al actualizar datos de la Habilidad\n");
        }
                
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM tarea WHERE id="+id;
        try(Connection conn = sql2o.open())
        {
            conn.createQuery(sql).executeUpdate();
        }catch(Exception e)
        {
            System.out.println(e.getMessage() + e.getLocalizedMessage()+" error al elminar la habilidad\n");
        }
    }

    @Override
    public Tarea createTarea(Tarea tarea) {
        
        String sql = "INSERT INTO tarea (id, emergencia, nombre, descripcion, estado, fechainicio, ubicacion, listaHabilidades) VALUES(:id, :emergencia, :nombre, :descripcion, :estado, :fechainicio, :ubicacion, :listaHabilidades)";
        
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql,true)
            .addParameter("id", tarea.getId())
            .addParameter("emergencia", tarea.getIdEmergencia())
            .addParameter("nombre", tarea.getNombre())
            .addParameter("descripcion", tarea.getDescripcion())
            .addParameter("estado", tarea.getEstado())
            .addParameter("fechainicio", tarea.getFechaInicio())
            .addParameter("ubicacion", tarea.getUbicacion())
            .addParameter("listaHabilidades",tarea.getListaHabilidades())
            .executeUpdate().getKey();

            tarea.setId(id);
            return tarea;
        
        }
        catch (Exception e) {
            System.out.println(e.getCause() + e.getLocalizedMessage() + "\n");
            
        }
        
        return null;
    }

    @Override
    public Tarea getById(int id) {
        String sql = "SELECT * FROM tarea WHERE id="+ Integer.toString(id);

        try(Connection conn = sql2o.open()){
            List<Tarea> l = conn.createQuery(sql).executeAndFetch(Tarea.class);   
            return l.get(0);
            
        } catch (Exception e) {
            System.out.println(e.getMessage() + e.getLocalizedMessage()+"Error al realizar la peticion de obtener una tarea por ID\n");
        }
        return null;
    }

    
}
