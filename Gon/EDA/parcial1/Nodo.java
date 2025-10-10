package parcial1;
class Nodo {
    private String dni;
    private String nombre; // Puedes agregar otros atributos si es necesario
    private Nodo siguiente;

    public Nodo(String dni, String nombre) {
        this.dni = dni;
        this.nombre = nombre;
        this.siguiente = null;
    }

    public String getDni() {
        return this.dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Nodo getSiguiente() {
        return this.siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

}
