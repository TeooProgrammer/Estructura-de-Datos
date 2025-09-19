package tp2.Divide_Venceras;

public class HeapSort {
    public static void heapSort(int[] vec) {
    int index;

    // Construcción del heap
    for (index = vec.length - 1; index >= 0; index--) {
        reHeapDown(vec, index, vec.length);
    }

    // Extracción y reordenamiento
    for (index = vec.length - 1; index > 0; index--) {
        interChange(vec, 0, index);
        reHeapDown(vec, 0, index);
    }
}

private static void reHeapDown(int[] vec, int parent, int size) {
    boolean done = false;
    int aux = vec[parent];
    int child = 2 * (parent + 1) - 1; // hijo izquierdo

    while (child < size && !done) {
        // elegir el hijo mayor
        if (child < size - 1 && vec[child] < vec[child + 1]) {
            child++;
        }
        if (aux >= vec[child]) {
            done = true;
        } else {
            vec[parent] = vec[child];
            parent = child;
            child = 2 * (parent + 1) - 1;
        }
    }
    vec[parent] = aux;
}

public static void interChange(int[] vec, int i, int j) {
    int temp = vec[i];
    vec[i] = vec[j];
    vec[j] = temp;
}

}
