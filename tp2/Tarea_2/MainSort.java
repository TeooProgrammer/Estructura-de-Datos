package tp2.Tarea_2;

import java.util.Scanner;
import java.util.Arrays;

public class MainSort {

    public static void mergeSort(Enemigo[] arr, int clave, boolean ascendente) {
        if (arr.length <= 1) return;

        int mid = arr.length / 2;
        Enemigo[] left = Arrays.copyOfRange(arr, 0, mid);
        Enemigo[] right = Arrays.copyOfRange(arr, mid, arr.length);

        mergeSort(left, clave, ascendente);
        mergeSort(right, clave, ascendente);

        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (comparar(left[i], right[j], clave, ascendente) <= 0) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }

        while (i < left.length) arr[k++] = left[i++];
        while (j < right.length) arr[k++] = right[j++];
    }

    private static int comparar(Enemigo a, Enemigo b, int clave, boolean ascendente) {
        int resultado = 0;
        switch (clave) {
            case 0: // nombre
                resultado = a.getNombre().compareTo(b.getNombre());
                break;
            case 1: // peligrosidad
                resultado = Long.compare(a.getPeligrosidad(), b.getPeligrosidad());
                break;
        }
        return ascendente ? resultado : -resultado;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Cantidad de enemigos: ");
        int n = sc.nextInt();
        sc.nextLine(); // limpiar buffer

        Enemigo[] enemigos = new Enemigo[n];

        for (int i = 0; i < n; i++) {
            System.out.println("Enemigo #" + (i + 1));
            System.out.print("Nombre: ");
            String nombre = sc.nextLine().toLowerCase().strip(); // minúsculas y sin espacios
            System.out.print("Nivel de peligrosidad: ");
            int peligrosidad = sc.nextInt();
            sc.nextLine(); // limpiar buffer
            enemigos[i] = new Enemigo(nombre, peligrosidad);
        }

        System.out.println("\nElegir atributo para ordenar:");
        System.out.println("0 = Nombre, 1 = Peligrosidad");
        int clave = sc.nextInt();
        sc.nextLine();

        System.out.print("Orden ascendente? (true/false): ");
        boolean ascendente = sc.nextBoolean();

        mergeSort(enemigos, clave, ascendente);

        System.out.println("\nEnemigos ordenados:");
        for (Enemigo e : enemigos) {
            System.out.println(e.getNombre() + " - " + e.getPeligrosidad());
        }

        sc.close();
    }
}
