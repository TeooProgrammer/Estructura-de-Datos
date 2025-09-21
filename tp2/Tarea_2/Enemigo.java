package tp2.Tarea_2;

public class Enemigo {

    private String nombre;
    private int peligrosidad;

    public Enemigo(String nombre, int peligrosidad) {
        this.nombre = nombre;
        this.peligrosidad = peligrosidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPeligrosidad() {
        return peligrosidad;
    }

    public void setPeligrosidad(int peligrosidad) {
        this.peligrosidad = peligrosidad;
    }

    @Override
    public String toString() {
        return "{" +
            " nombre='" + getNombre() + "'" +
            ", peligrosidad='" + getPeligrosidad() + "'" +
            "}";
    }


}

