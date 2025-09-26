package tp3.Secuencial;

public class Secuencial {
    
public static int sequentialSearch(int[] vec, int search) {
    int pos = 0;
    while (pos < vec.length) {
        if (vec[pos] == search) {
            return pos;
        }
        pos++;
    }
    return -1;
}

}
