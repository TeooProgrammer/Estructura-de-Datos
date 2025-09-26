package tp3.Fibonacci;

public class Fibonacci {
    
public static int fibonacciSearch(int[] vec, int search) {
    // for(j=1; fib(j)<n; j++)
    int f1 = 1;
    int f2 = 1;
    int faux;
    int mid;
    int length = vec.length - 1;

    while (f1 < length) { // encuentra a f1 tal que f1 >= length
        f1 = f1 + f2; // siguiente número Fibonacci
        f2 = f1 - f2; // guarda al f1 anterior
    }

    f1 = f1 - f2; // encuentra el número Fibonacci más chico
    f2 = f2 - f1; // f1 = fib(j-2), f2 = fib(j-3)
    mid = length - f1 + 1;

    while (search != vec[mid]) { // si no lo encuentra
        if (mid < 0 || search > vec[mid]) { // busca en la mitad más baja
            if (f1 == 1) { // si no lo encuentra retorna -1
                return -1;
            }
            mid = mid + f2; // disminuye los números de Fibonacci
            f1 = f1 - f2;
            f2 = f2 - f1;
        } else { // busca en la mitad más alta
            if (f2 == 0) { // si no lo encuentra retorna -1
                return -1;
            }
            mid = mid - f2; // disminuye los números de Fibonacci
            faux = f1 - f2; // esta vez, disminuye más
            f1 = f2;        // para el arreglo más chico
            f2 = faux;
        }
    }
    return mid;
}

}
