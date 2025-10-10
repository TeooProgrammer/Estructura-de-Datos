public class quickSortb {
    public static void main(String[] args) {
        int[] vec = {2, 124, 23, 5, 89, -1, 44, 643, 34, 76, 43, 0};
        System.out.print("Arreglo inicial:     ");
        printArray(vec);
        quickSort(vec);
        System.out.println("Arreglo final: ");
        printArray(vec);
    }

    private static int pasos = 1; // Variable para contar los pasos

    public static void quickSort(int[] vec) {
        quickSort(vec, 0, vec.length - 1);
    }

    private static void quickSort(int[] vec, int first, int last) {
        if (first < last) {
            int center = division(vec, first, last); // Usar el último elemento como pivote
            System.out.print("Paso " + (pasos++) + ": ");
            printArray(vec);
            quickSort(vec, first, center - 1); // Ordenar parte izquierda
            quickSort(vec, center + 1, last);  // Ordenar parte derecha
        }
    }

    // Método modificado para tomar el último elemento como pivote
    private static int division(int[] vec, int first, int last) {
        int left = first;
        int right = last - 1;
        int pivot = vec[last];  // El pivote ahora es el último elemento del segmento

        System.out.println("Pivote seleccionado: " + pivot);

        // Proceso de partición
        while (left <= right) {
            // Mover el puntero 'left' hacia la derecha hasta encontrar un valor mayor o igual al pivote
            while (left <= right && vec[left] < pivot) {
                left++;
            }
            // Mover el puntero 'right' hacia la izquierda hasta encontrar un valor menor o igual al pivote
            while (left <= right && vec[right] > pivot) {
                right--;
            }
            // Intercambiar si no se han cruzado
            if (left < right) {
                System.out.println("Intercambiando " + vec[left] + " con " + vec[right]);
                interChange(vec, left, right);
                left++;
                right--;
            }
        }
        // Colocar el pivote en su posición correcta
        System.out.println("Colocando el pivote en la posición " + left);
        interChange(vec, left, last);
        return left;  // La nueva posición del pivote
    }

    private static void interChange(int[] vec, int left, int right) {
        int aux = vec[left];
        vec[left] = vec[right];
        vec[right] = aux;
    }

    // Método para imprimir el vector en cada paso
    private static void printArray(int[] vec) {
        for (int i : vec) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
