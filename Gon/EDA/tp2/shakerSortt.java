class shakerSortt{
    public static void main(String[] args) {
        int[] vec = {2, 124, 23, 5, 89, -1, 44, 643, 34, 76, 43, 0};
        System.out.print("Arreglo inicial:     ");
        printArray(vec);
        shakerSort(vec);
        System.out.println("Arreglo final: ");
        printArray(vec);
    }
    public static void shakerSort(int[] vec) {
        int left = 1;
        int right = vec.length - 1;
        int aux = vec.length - 1;
        do {
            for (int i = right; i >= left; i--) {
                if (vec[i - 1] > vec[i]) {
                    interChange(vec, i - 1, i);
                    printArray(vec); // Imprimir arreglo después de cada intercambio
                    aux = i;
                }
            }
            left = aux + 1;
            for (int i = left; i <= right; i++) {
                if (vec[i - 1] > vec[i]) {
                    interChange(vec, i - 1, i);
                    printArray(vec); // Imprimir arreglo después de cada intercambio
                    aux = i;
                }
            }
            right = aux - 1;
        } while (left < right);
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
