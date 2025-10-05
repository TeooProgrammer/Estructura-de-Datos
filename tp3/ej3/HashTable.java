package tp3.ej3;

public class HashTable {

    private final int[] tabla;
    private final int tamaño;
    private int colisiones;
    private int comparaciones;
    private int ocupadas;

    public HashTable(int tamaño) {
        this.tamaño = tamaño;
        this.tabla = new int[tamaño];
        this.colisiones = 0;
        this.comparaciones = 0;
        this.ocupadas = 0;
    }

    public void insertar(int clave, int pos) {
        int intentos = 0;

        // primera comparacion (posicion inicial)
        comparaciones++;

        while (tabla[pos] != 0) { // mientras haya colision
            pos = (pos + 1) % tamaño; // rehashing lineal
            colisiones++;
            comparaciones++; // otra comparacion
            intentos++;

            if (intentos > tamaño) return; // evita bucles infinitos
        }

        tabla[pos] = clave;
        ocupadas++;
    }

    public int getColisiones() {
        return colisiones;
    }

    public int getComparaciones() {
        return comparaciones;
    }

    public int getOcupadas() {
        return ocupadas;
    }
}
