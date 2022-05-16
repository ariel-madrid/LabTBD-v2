package TBD.labTBD.Models;

public class Habilidad{
    int id;
    String nombre;
    String descripcion;



    //Getters
    public String getNombre(){
        return this.nombre;

    }

    public String getDescripcion(){
        return this.descripcion;
    }


    public int getId(){
        return this.id;
    }

    //Setters
    public void setNombre(String nuevoNombre){
        this.nombre = nuevoNombre;
    }

    public void setDescripcion(String nuevaDescripcion){
        this.descripcion = nuevaDescripcion;
    }

    public void setId(int nuevoId){
        this.id = nuevoId;
    }



}