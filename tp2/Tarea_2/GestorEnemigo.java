package tp2.Tarea_2;

import java.util.ArrayList;
import java.util.List;

public class GestorEnemigo {
    private List<Enemigo> enemigos;

    public GestorEnemigo() {
        this.enemigos = new ArrayList<>();
    }

    public void agregarEnemigo(Enemigo enemigo) {
        enemigos.add(enemigo);
    }

    public List<Enemigo> getEnemigos() {
        return enemigos;
    }

    public void OrdenarPorPeligrosidad() {
        Enemigo[] array = enemigos.toArray(new Enemigo[0]);
        int n = array.length;

    // construir el heap máximo
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }

    // sacar elementos del heap de a uno
        for (int i = n - 1; i >= 0; i--) {
        // intercambiar la raíz (maximo) con el último elemento
            Enemigo temp = array[0];
            array[0] = array[i];
            array[i] = temp;

        // llamar heapify en el heap reducido
        heapify(array, i, 0);
        }

    // cargar en la lista en orden descendente
        enemigos.clear();
        for (int i = array.length - 1; i >= 0; i--) { 
            enemigos.add(array[i]);
        }
    }

    // ordena el subarbol en forma de max heap considerando el indice
    private void heapify(Enemigo[] array, int n, int i) {
        int mayor = i; 
        int izq = 2 * i + 1;
        int der = 2 * i + 2;

        if (izq < n && array[izq].getPeligrosidad() > array[mayor].getPeligrosidad()) {
            mayor = izq;
        }

        if (der < n && array[der].getPeligrosidad() > array[mayor].getPeligrosidad()) {
            mayor = der;
        }

        if (mayor != i) {
            Enemigo temp = array[i];
            array[i] = array[mayor];
            array[mayor] = temp;
            heapify(array, n, mayor);
        }
    }

}


