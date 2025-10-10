public class quickSorta {
    public static void main(String[] args) {
        int[] vec = {40, 5, 20, 45, 30, 15, 25, 10, 35, 0};
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
            int center = division(vec, first, last); 
            System.out.print("Paso " + (pasos++) + ": ");
            printArray(vec);
            quickSort(vec, first, center - 1);
            quickSort(vec, center + 1, last); 
        }
    }
    private static int division(int[] vec, int first, int last) {
        int left = first + 1;
        int right = last;
        int pivot = vec[first];  
        System.out.println("Pivote seleccionado: " + pivot);
        while (left <= right) {
            while (left <= right && vec[left] < pivot) {
                System.out.println("Comparando " + vec[left] + " < " + pivot + ": true");
                left++;
            }
            if (left <= right) {
                System.out.println("Comparando " + vec[left] + " < " + pivot + ": false");
            }

            while (left <= right && vec[right] > pivot) {
                System.out.println("Comparando " + vec[right] + " > " + pivot + ": true");
                right--;
            }
            if (left <= right) {
                System.out.println("Comparando " + vec[right] + " > " + pivot + ": false");
            }
            if (left < right) {
                System.out.println("Intercambiando " + vec[left] + " con " + vec[right]);
                interChange(vec, left, right);
                left++;
                right--;
            }
        }
        System.out.println("Colocando el pivote en la posición " + right);
        interChange(vec, first, right);
        return right; 
    }
    private static void interChange(int[] vec, int left, int right) {
        int aux = vec[left];
        vec[left] = vec[right];
        vec[right] = aux;
    }
    private static void printArray(int[] vec) {
        for (int i : vec) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
