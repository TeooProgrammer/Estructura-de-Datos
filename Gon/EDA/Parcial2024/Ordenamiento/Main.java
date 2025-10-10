package Parcial2024.Ordenamiento;

public class Main {
    public static void main(String[] args) {
        int[] vec = {40, 5, 20, 45, 30, 15, 25, 10, 35, 0};
        System.out.print("Arreglo inicial:     ");
        printArray(vec);
        QuickSort.quickSort(vec);
        System.out.println("Arreglo final: ");
        printArray(vec);
    }
    private static void printArray(int[] vec) {
        for (int i : vec) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}