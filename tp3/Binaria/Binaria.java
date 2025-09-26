package tp3.Binaria;

public class Binaria {
    
public static int binarySearch(int[] vec, int search) {
    int first = 0;
    int mid;
    int last = vec.length - 1;

    while (first <= last) {
        mid = (first + last) / 2;

        if (vec[mid] == search) {
            return mid;
        } else if (vec[mid] > search) {
            last = mid - 1;
        } else {
            first = mid + 1;
        }
    }
    return -1;
}

}
