package Ordenamientos;

public class ordenamientos {

    //MergeSort
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
        while (aptr <= center & bptr <= right) {
            if (vec[aptr] < vec[bptr]) {
                tmp[cptr++] = vec[aptr++]; 
            }else {
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


    //Heapsort
    public static void heapSort(int[] vec) {
        int index;
        for (index = vec.length - 1; index >= 0; index--) {
            reHeapDown(vec, index, vec.length);
        }
        for (index = vec.length - 1; index > 0; index--) {
            interChange(vec, 0, index);
            reHeapDown(vec, 0, index);
        }
    }

    private static void reHeapDown(int vec[], int length, int index) {
        boolean done = false;
        int aux = vec[length];
        int parent = length;
        int child = 2 * (length + 1) - 1;

        while (child < index & !done) {
            if (child < index - 1) {
                if (vec[child] < vec[child + 1]) {
                    child++;
                }
            }
            if (aux >= vec[child]) {
                done = true; 
            }else {
                vec[parent] = vec[child];
                parent = child;
                child = 2 * (parent + 1) - 1;
            }
        }
        vec[parent] = aux;
    }


    //Quicksort
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
            }else {
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


    //Shellsort
    public static void shellSort(int[] vec) {
        int interval = vec.length / 2;
        while (interval > 0) {
            for (int i = interval; i < vec.length; i++) {
                int j = i - interval;
                while (j >= 0) {
                    if (vec[j] <= vec[j + interval]) {
                        j = 0; 
                    }else {
                        interChange(vec, j, j + interval);
                    }
                    j -= interval;
                }
            }
            interval /= 2;
        }
    }

    //Shakersort
    public static void shakerSort(int[] vec) {
        int left = 1;
        int right = vec.length - 1;
        int aux = vec.length - 1;
        do {
            for (int i = right; i >= left; i--) {
                if (vec[i - 1] > vec[i]) {
                    interChange(vec, i - 1, i);
                    aux = i;
                }
            }
            left = aux + 1;
            for (int i = left; i <= right; i++) {
                if (vec[i - 1] > vec[i]) {
                    interChange(vec, i - 1, i);
                    aux = i;
                }
            }
            right = aux - 1;
        } while (left < right);
    }

    //interchange
    private static void interChange(int[] vec, int left, int right) {
        int aux = vec[left];
        vec[left] = vec[right];
        vec[right] = aux;
    }
}
