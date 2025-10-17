package Parcial1.p2;

public class Punto2 {
    static int pasadas = 0;
    static int intercambios = 0;

    public static void quickSort(int[] vec, int first, int last) {
        if (first < last) {
            pasadas++;
            int pivoteI = division(vec, first, last);
            quickSort(vec, first, pivoteI - 1);
            quickSort(vec, pivoteI + 1, last);
        }
    }

    // tomando el elemento medio como pivote
    private static int division(int[] vec, int first, int last) {
        int mid = (first + last) / 2; 
        int pivote = vec[mid];
        System.out.println("Pasada " + pasadas + " - Pivote: " + pivote);
        intercambio(vec, mid, last);

        int i = first - 1;

        for (int j = first; j < last; j++) {
            if (vec[j] <= pivote) {
                i++;
                intercambio(vec, i, j);
            }
        }

        intercambio(vec, i + 1, last);

        printArray(vec, "Vector despues de la pasada " + pasadas);
        return i + 1;
    }

    private static void intercambio(int[] vec, int i, int j) {
        int temp = vec[i];
        vec[i] = vec[j];
        vec[j] = temp;
        intercambios++;
    }

    private static void printArray(int[] vec, String mensaje) {
        System.out.println(mensaje + ":");
        for (int value : vec) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] vec = {16, 72, 23, 9, 1, 56, 12, 99, 40, 7};

        printArray(vec, "Vector original");
        quickSort(vec, 0, vec.length - 1);
        printArray(vec, "Vector ordenado");

        System.out.println("Número total de pasadas: " + pasadas);
        System.out.println("Número total de intercambios: " + intercambios);
    }
}