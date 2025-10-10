package parcial1;
public class Main2 {
    public static void shellSort(String[] vec) {
        int interval = vec.length / 2; // Comenzar con un intervalo de la mitad del tamaño del array
        while (interval > 0) {
            // Realiza la ordenación para cada subarray de tamaño 'interval'
            for (int i = interval; i < vec.length; i++) {
                String temp = vec[i]; // Almacenar el valor actual
                int j = i;

                // Comparar y mover los elementos del subarray
                while (j >= interval && vec[j - interval].compareTo(temp) > 0) {
                    vec[j] = vec[j - interval]; // Mover el elemento hacia adelante
                    j -= interval; // Mover hacia atrás en el subarray
                }
                vec[j] = temp; // Colocar el elemento actual en su posición correcta
            }
            interval /= 2; // Reducir el intervalo
        }
    }

    public static void main(String[] args) {
        ListaEnlazada listaMateria1 = new ListaEnlazada();
        listaMateria1.agregar("23456789", "Ana");
        listaMateria1.agregar("45678901", "Lucía");
        
        ListaEnlazada listaMateria2 = new ListaEnlazada();
        listaMateria2.agregar("12345678", "Juan");
        listaMateria2.agregar("67890123", "Luis");
        
        ListaEnlazada listaMateria3 = new ListaEnlazada();
        listaMateria3.agregar("34567890", "Pedro");
        listaMateria3.agregar("56789012", "María");

        // Combinar las listas
        ListaEnlazada listaCombinada = ListaEnlazada.combinarListas(listaMateria1, listaMateria2, listaMateria3);

        // Convertir a array y aplicar Shellsort
        String[] dniArray = listaCombinada.toArray();
        String[] nombresArray = new String[dniArray.length];

        // Guardar nombres correspondientes en un array
        Nodo actual = listaCombinada.getCabeza();
        int index = 0;
        while (actual != null) {
            nombresArray[index++] = actual.getNombre(); // Guardar el nombre
            actual = actual.getSiguiente();
        }

        // Ordenar solo el array de DNIs
        shellSort(dniArray);

        // Crear una nueva lista enlazada ordenada
        ListaEnlazada listaOrdenada = new ListaEnlazada();
        listaOrdenada.fromArray(dniArray, nombresArray); // Usar el array de nombres

        // Mostrar la lista ordenada
        System.out.println("Lista de estudiantes ordenada por DNI:");
        listaOrdenada.mostrar();
    }
}