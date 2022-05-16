package TBD.labTBD.Models;

public class Institucion {
    private int id;
    private String nombre;
    private String coordinador;
    private String clave_Coordinador;

    //Getters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCoordinador() {
        return coordinador;
    }

    public String getClaveCoordinador() {
        return this.clave_Coordinador;
    }

    //Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCoordinador(String coordinador) {
        this.coordinador = coordinador;
    }

    public void setClaveCoordinador(String clave_Coordinador) {
        this.clave_Coordinador = clave_Coordinador;
    }

}
