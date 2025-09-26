package tp3.Interpolacion;

public class Interpolacion {
    
public static int interpolationSearch(int[] vec, int search) {
    int first = 0;
    int mid;
    int last = vec.length - 1;

    while (search >= vec[first] && search <= vec[last]) {
        mid = first + (int) Math.abs(Math.floor(
            (double) (search - vec[first]) * (last - first) / (vec[last] - vec[first])
        ));

        if (search == vec[mid]) {
            return mid;
        } else if (search < vec[mid]) {
            last = mid - 1;
        } else {
            first = mid + 1;
        }
    }
    return -1;
}

}
