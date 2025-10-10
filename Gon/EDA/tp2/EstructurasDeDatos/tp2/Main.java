package EstructurasDeDatos.tp2;
public class Main{
    public static void main(String[] args) {
        int[] vec = {2, 124, 23, 5, -1, 89, 44, 643, 34, 76, 43, 0};
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
        int center = (first + last) / 2;
        if (first < last) {
            center = division(vec, first, last, center);
            System.out.print("Paso " + (pasos++) + ": ");
            printArray(vec);
            quickSort(vec, first, center);
            if (center == first) {
                quickSort(vec, center + 1, last); 
            } else {
                quickSort(vec, center, last);
            }
        }
    }

    private static int division(int[] vec, int first, int last, int center) {
        int left, right, data;
        data = vec[center];
        left = first;
        right = last;
        do {
            while (left < right && vec[left] < data) {
                left++;
            }
            while (left < right && vec[right] > data) {
                right--;
            }
            if (left < right) {
                interChange(vec, left, right);
                left++;
                right--;
            }
        } while (left < right);
        if (left < right) {
            int pos = right;
            right = left;
            left = pos;
        }
        return left;
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