public class quickSortd {
    public static void main(String[] args) {
        int[] vec = {2, 124, 23, 5, 89, -1, 44, 643, 34, 76, 43, 0};
        System.out.print("Arreglo inicial:     ");
        mostrar(vec);
        quickSort(vec);
        System.out.println("Arreglo final: ");
        mostrar(vec);
    }

    private static int pasos = 1; // Variable para contar los pasos

    public static void quickSort(int[] vec) {
        quickSort(vec, 0, vec.length - 1);
    }

    private static void quickSort(int[] vec, int first, int last) {
        if (first < last) {
            int[] pivotes = division(vec, first, last);
            System.out.print("Paso " + (pasos++) + ": ");
            mostrar(vec);

            quickSort(vec, first, pivotes[0] - 1); 
            quickSort(vec, pivotes[0] + 1, pivotes[1] - 1); 
            quickSort(vec, pivotes[1] + 1, last); 
        }
    }

    private static int[] division(int[] vec, int first, int last) {
        if (vec[first] > vec[last]) {
            interChange(vec, first, last);
        }

        int pivote1 = vec[first];
        int pivote2 = vec[last];

        System.out.println("Pivotes : Pivote 1 = " + pivote1 + ", Pivote 2 = " + pivote2);

        int i = first + 1;
        int a = first + 1;
        int b = last - 1;

        while (i <= b) {
            if (vec[i] < pivote1) {
                interChange(vec, i++, a++);
            } else if (vec[i] > pivote2) {
                interChange(vec, i, b--);
            } else {
                i++;
            }
        }

        interChange(vec, first, --a);
        interChange(vec, last, ++b);

        return new int[]{a, b};
    }

    private static void interChange(int[] vec, int left, int right) {
        int aux = vec[left];
        vec[left] = vec[right];
        vec[right] = aux;
    }
    private static void mostrar(int[] vec) {
        for (int i : vec) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
