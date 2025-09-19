package tp2.No_Comporativo;

import java.util.Queue;
import java.util.LinkedList;

public class RadixSort {

    public static void radixSort(int[] vec) {
        int maxDigits = getMaxDigits(vec); // cantidad de dígitos del máximo elemento

        for (int d = 1; d <= maxDigits; d++) {
            // crea las urnas para los dígitos 0-9
            @SuppressWarnings("unchecked")
            Queue<Integer>[] urn = (Queue<Integer>[]) new LinkedList[10];
            for (int i = 0; i < urn.length; i++) {
                urn[i] = new LinkedList<>();
            }

            // coloca cada elemento en la urna correspondiente según el dígito d
            for (int v : vec) {
                urn[getKey(v, d)].add(v);
            }

            // recupera los elementos de las urnas en el arreglo
            int j = 0;
            for (int i = 0; i < urn.length; i++) {
                while (!urn[i].isEmpty()) {
                    vec[j++] = urn[i].remove();
                }
            }
        }
    }

    // obtiene el dígito d (1=unidad, 2=decena, 3=centena, etc.) de un número
    private static int getKey(int value, int d) {
        return (value / (int) Math.pow(10, d - 1)) % 10;
    }

    // obtiene la cantidad de dígitos del número más grande en el arreglo
    private static int getMaxDigits(int[] vec) {
        int max = vec[0];
        for (int v : vec) {
            if (v > max) max = v;
        }
        return Integer.toString(max).length();
    }
}
