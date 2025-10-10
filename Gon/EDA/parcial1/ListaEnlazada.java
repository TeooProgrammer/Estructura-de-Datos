package parcial1;
class ListaEnlazada {
    private Nodo cabeza;

    public void agregar(String dni, String nombre) {
        Nodo nuevo = new Nodo(dni, nombre);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo actual = cabeza;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
    }

    // Método para combinar las tres listas
    public static ListaEnlazada combinarListas(ListaEnlazada lista1, ListaEnlazada lista2, ListaEnlazada lista3) {
        ListaEnlazada nuevaLista = new ListaEnlazada();

        Nodo actual = lista1.cabeza;
        while (actual != null) {
            nuevaLista.agregar(actual.getDni(), actual.getNombre());
            actual = actual.getSiguiente();
        }

        actual = lista2.cabeza;
        while (actual != null) {
            nuevaLista.agregar(actual.getDni(), actual.getNombre());
            actual = actual.getSiguiente();
        }

        actual = lista3.cabeza;
        while (actual != null) {
            nuevaLista.agregar(actual.getDni(), actual.getNombre());
            actual = actual.getSiguiente();
        }

        return nuevaLista;
    }

    // Método para convertir la lista enlazada a un array para aplicar Shellsort
    public String[] toArray() {
        int size = 0;
        Nodo actual = cabeza;
        while (actual != null) {
            size++;
            actual = actual.getSiguiente();
        }

        String[] array = new String[size];
        actual = cabeza;
        int index = 0;
        while (actual != null) {
            array[index++] = actual.getDni(); // Puedes ajustar esto si necesitas más información
            actual = actual.getSiguiente();
        }

        return array;
    }

    // Método para crear la lista enlazada desde el array
    public void fromArray(String[] array, String[] nombres) {
        for (int i = 0; i < array.length; i++) {
            agregar(array[i], nombres[i]); // Agregar el nombre correspondiente
        }
    }

    // Método para mostrar la lista
    public void mostrar() {
        Nodo actual = cabeza;
        while (actual != null) {
            System.out.println("DNI: " + actual.getDni() + ", Nombre: " + actual.getNombre());
            actual = actual.getSiguiente();
        }
    }

    public Nodo getCabeza() {
        return this.cabeza;
    }

    public void setCabeza(Nodo cabeza) {
        this.cabeza = cabeza;
    }

}