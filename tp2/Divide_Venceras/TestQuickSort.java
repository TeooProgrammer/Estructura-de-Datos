package tp2.Divide_Venceras;

public class TestQuickSort {
    public static void main(String[] args) {
        int[] arr = { 9, 3, 7, 1, 6, 2, 8, 5, 4 }; // arreglo de ejemplo

        System.out.println("Arreglo original:");
        printArray(arr);

        quickSort(arr, 0, arr.length - 1);

        System.out.println("Arreglo ordenado con QuickSort:");
        printArray(arr);
    }

    // Método para imprimir el arreglo
    private static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // Método QuickSort
    private static void quickSort(int[] vec, int first, int last) {
        int center = (first + last) / 2;
        if (first < last) {
            center = division(vec, first, last, center);
            quickSort(vec, first, center);
            if (center == first) {
                quickSort(vec, center + 1, last);
            } else {
                quickSort(vec, center, last);
            }
        }
    }

    // Método division
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

    // Método interChange
    private static void interChange(int[] vec, int i, int j) {
        int temp = vec[i];
        vec[i] = vec[j];
        vec[j] = temp;
    }
}
