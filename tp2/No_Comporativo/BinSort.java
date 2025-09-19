package tp2.No_Comporativo;

import java.util.Queue;
import java.util.LinkedList;

public class BinSort {

    public static void binSort(int[] vec) {
        if (vec.length == 0) return;

        // calcular valor máximo para determinar el tamaño de las urnas
        int max = getMax(vec);

        @SuppressWarnings("unchecked")
        Queue<Integer>[] urn = (Queue<Integer>[]) new LinkedList[max + 1];

        // inicializar cada urna
        for (int i = 0; i <= max; i++) {
            urn[i] = new LinkedList<>();
        }

        // colocar cada elemento en su urna correspondiente
        for (int v : vec) {
            urn[v].add(v);
        }

        // recuperar los elementos de las urnas en el arreglo
        int j = 0;
        for (int i = 0; i <= max; i++) {
            while (!urn[i].isEmpty()) {
                vec[j++] = urn[i].remove();
            }
        }
    }

    // calcula el valor máximo del arreglo
    private static int getMax(int[] vec) {
        int max = vec[0];
        for (int v : vec) {
            if (v > max) max = v;
        }
        return max;
    }
}

