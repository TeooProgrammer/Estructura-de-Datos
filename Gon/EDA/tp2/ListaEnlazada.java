public class ListaEnlazada {
    private Nodo cabeza;

    public ListaEnlazada() {
        this.cabeza = null;
    }

    // Método para agregar un elemento al inicio de la lista
    public void agregarAlInicio(int valor) {
        Nodo nuevo = new Nodo(valor);
        nuevo.setSiguiente(cabeza);
        cabeza = nuevo;
    }

    // Método para buscar un elemento en la lista y moverlo al principio si se encuentra
    public boolean buscarYMoverAlInicio(int valor) {
        if (cabeza == null) return false;

        // Si el valor ya está en la cabeza, no se necesita mover
        if (cabeza.getValor() == valor) return true;

        Nodo actual = cabeza;
        Nodo previo = null;

        // Recorrer la lista para encontrar el valor
        while (actual != null && actual.getValor() != valor) {
            previo = actual;
            actual = actual.getSiguiente();
        }

        // Si no se encuentra el valor, retornar falso
        if (actual == null) return false;

        // Si se encuentra el valor, moverlo al inicio
        if (previo != null) {
            previo.setSiguiente(actual.getSiguiente());  // Desvincular el nodo encontrado
            actual.setSiguiente(cabeza);            // Vincular el nodo al inicio
            cabeza = actual;                      // Actualizar la cabeza
        }

        return true;
    }

    // Método para imprimir la lista
    public void imprimirLista() {
        Nodo actual = cabeza;
        while (actual != null) {
            System.out.print(actual.getValor() + " -> ");
            actual = actual.getSiguiente();
        }
        System.out.println("null");
    }
}