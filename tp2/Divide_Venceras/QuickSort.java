package tp2.Divide_Venceras;

public class QuickSort {
    public static void quickSort(int[] vec) {
    quickSort(vec, 0, vec.length - 1);
}

private static void quickSort(int[] vec, int first, int last) {
    int center = (first + last) / 2;
    if (first < last) {
        center = division(vec, first, last, center);
        quickSort(vec, first, center);
        if (center == first) {
            quickSort(vec, center + 1, last);
        } else {
            quickSort(vec, center, last);
        }
    }
}

private static int division(int[] vec, int first, int last, int center) {
    int left, right, data;
    data = vec[center];
    left = first;
    right = last;

    do {
        while (left < right && vec[left] < data) {
            left++;
        }
        while (left < right && vec[right] > data) {
            right--;
        }
        if (left < right) {
            interChange(vec, left, right);
            left++;
            right--;
        }
    } while (left < right);

    if (left < right) {
        int pos = right;
        right = left;
        left = pos;
    }

    return left;
}

public static void interChange(int[] vec, int i, int j) {
    int temp = vec[i];
    vec[i] = vec[j];
    vec[j] = temp;
}


}
