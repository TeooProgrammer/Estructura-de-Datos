package tp2.Avanzados;

public class ShakeSort {

public static void shakerSort(int[] vec) {
    int left = 1;
    int right = vec.length - 1;
    int aux = vec.length - 1;

    do {
        // Barrido de derecha a izquierda
        for (int i = right; i >= left; i--) {
            if (vec[i - 1] > vec[i]) {
                interChange(vec, i - 1, i);
                aux = i;
            }
        }
        left = aux + 1;

        // Barrido de izquierda a derecha
        for (int i = left; i <= right; i++) {
            if (vec[i - 1] > vec[i]) {
                interChange(vec, i - 1, i);
                aux = i;
            }
        }
        right = aux - 1;

    } while (left < right);
}

public static void interChange(int[] vec, int i, int j) {
    int temp = vec[i];
    vec[i] = vec[j];
    vec[j] = temp;
}

}
