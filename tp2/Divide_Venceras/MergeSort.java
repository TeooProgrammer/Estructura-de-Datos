package tp2.Divide_Venceras;

public class MergeSort {
    public static void mergeSort(int[] vec) {
    int[] tmp = new int[vec.length];
    mergeSort(vec, tmp, 0, vec.length - 1);
}

private static void mergeSort(int[] vec, int[] tmp, int left, int right) {
    if (left < right) {
        int center = (right + left) / 2;
        mergeSort(vec, tmp, left, center);
        mergeSort(vec, tmp, center + 1, right);
        merge(vec, tmp, left, center, right);
    }
}

private static void merge(int[] vec, int[] tmp, int left, int center, int right) {
    int aptr = left;
    int bptr = center + 1;
    int cptr = left;

    while (aptr <= center && bptr <= right) {
        if (vec[aptr] < vec[bptr]) {
            tmp[cptr++] = vec[aptr++];
        } else {
            tmp[cptr++] = vec[bptr++];
        }
    }

    while (aptr <= center) {
        tmp[cptr++] = vec[aptr++];
    }

    while (bptr <= right) {
        tmp[cptr++] = vec[bptr++];
    }

    for (int i = left; i <= right; i++) {
        vec[i] = tmp[i];
    }
}

}
