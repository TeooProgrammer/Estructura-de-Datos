package Parcial1.p3;
import java.util.ArrayList; // agregar para el desbordamiento
//import java.util.LinkedList; // agregar para las tablas hash abiertas

//import tp3.ej3.FuncionesHash;
//import tp3.ej3.HashTable;

//import java.util.ArrayList;
 // AGREGAR PARA EL DESBORDAMIENTO
class TablaHashDesbordamiento {
    int[] tablaHash;
    ArrayList<Integer> desbordamiento; // arreglo para almacenar valores que no entran en la tabla principal
    int tam = 12;

    public TablaHashDesbordamiento() {
        this.tablaHash = new int[this.tam];
        desbordamiento = new ArrayList<>();
        for (int i = 0; i < this.tam; i++) {
            tablaHash[i] = -1;
        }
    }

    public TablaHashDesbordamiento(int tam) {
        this.tam = 12;
        tablaHash = new int[tam];
        for (int i = 0; i < tam; i++) {
                tablaHash[i] = -1;  // -1 indica posición vacía
            }
        }

    private int hash(int clave) {
        return clave % this.tam;
    }

    void addDesbordamiento(int numero) {
        int hash = hash(numero);
        if (tablaHash[hash] == -1) {
            tablaHash[hash] = numero;
        } else {
            desbordamiento.add(numero);
        }
    }

    boolean searchDesbordamiento(int numero) {
        int hash = hash(numero);
        if (tablaHash[hash] == numero) {
            return true;
        } else {
            return desbordamiento.contains(numero);
        }
    
    }

    private int hash3(int key) {
        return key=7-(key%7);  // Tercera función hash
    }

    public static void main(String[] args) {
    TablaHashDesbordamiento tablaHash = new TablaHashDesbordamiento(2);
    int[] keys={845, 601};

    tablaHash.searchDesbordamiento(288);
    tablaHash.searchDesbordamiento(996);
    tablaHash.addDesbordamiento(845);
    tablaHash.addDesbordamiento(601);

     for (int key : keys) {
        tablaHash.hash3(key);
    }
  
}
}



