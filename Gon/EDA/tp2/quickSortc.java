public class quickSortc {
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
            int center = division(vec, first, last);
            System.out.print("Paso " + (pasos++) + ": ");
            printArray(vec);
            quickSort(vec, first, center - 1); 
            quickSort(vec, center + 1, last); 
        }
    }

    private static int division(int[] vec, int first, int last) {
        int center = (first + last) / 2;

        int pivotIndex = mediana(vec, first, center, last);
        int pivot = vec[pivotIndex];

        System.out.println("Pivot: " + pivot);

        interChange(vec, pivotIndex, last);

        int left = first;
        int right = last - 1;
        while (left <= right) {
            while (left <= right && vec[left] < pivot) {
                left++;
            }
            while (left <= right && vec[right] > pivot) {
                right--;
            }
            if (left < right) {
                System.out.println("Intercambio " + vec[left] + " con " + vec[right]);
                interChange(vec, left, right); 
                left++;
                right--;
            }
        }

        System.out.println("Cambiando el pivot en la posicion " + left);
        interChange(vec, left, last); 
        return left; 
    }
    private static int mediana(int[] vec, int first, int center, int last) {
        if (vec[first] > vec[center]) {
            if (vec[first] < vec[last]) {
                return first;  
            } else if (vec[center] > vec[last]) {
                return center;  
            } else {
                return last;  
            }
        } else {
            if (vec[center] < vec[last]) {
                return center;
            } else if (vec[first] > vec[last]) {
                return first;  
            } else {
                return last; 
            }
        }
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
