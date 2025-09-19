package tp2.Basico;

public class basico{

public static void selectionSort(int[] vec) {
    for (int i = 0; i < vec.length - 1; i++) {
        int pos = i;
        for (int j = i + 1; j < vec.length; j++) {
            if (vec[j] < vec[pos]) {
                pos = j;
            }
        }
        interChange(vec, pos, i);
    }
}

public static void bubbleSort(int[] vec) {
    for (int i = vec.length - 1; i >= 1; i--) {
        for (int j = 0; j <= i - 1; j++) {
            if (vec[j] > vec[j + 1]) {
                interChange(vec, j, j + 1);
            }
        }
    }
}

public static void insertionSort(int[] vec) {
    for (int i = 1; i < vec.length; i++) {
        int aux = vec[i];
        int j = i - 1;
        while (j >= 0 && vec[j] > aux) {
            vec[j + 1] = vec[j];
            j -= 1;
        }
        vec[j + 1] = aux;
    }
}

public static void interChange(int[] vec, int i, int j) {
    int temp = vec[i];
    vec[i] = vec[j];
    vec[j] = temp;
}

public static void bubbleImprovedSort(int[] vec) {
    int i = vec.length - 1;
    boolean ordered;
    do {
        ordered = true;
        for (int j = 0; j <= i - 1; j++) {
            if (vec[j] > vec[j + 1]) {
                interChange(vec, j, j + 1);
                ordered = false;
            }
        }
        i--;
    } while (i >= 1 && !ordered);
}



}