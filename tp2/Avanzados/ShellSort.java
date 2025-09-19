package tp2.Avanzados;

public class ShellSort {

public static void shellSort(int[] vec) {
    int interval = vec.length / 2;

    while (interval > 0) {
        for (int i = interval; i < vec.length; i++) {
            int j = i - interval;
            while (j >= 0) {
                if (vec[j] <= vec[j + interval]) {
                    j = 0;
                } else {
                    interChange(vec, j, j + interval);
                    j -= interval;
                }
            }
        }
        interval /= 2;
    }
}
public static void interChange(int[] vec, int i, int j) {
    int temp = vec[i];
    vec[i] = vec[j];
    vec[j] = temp;
}

}
