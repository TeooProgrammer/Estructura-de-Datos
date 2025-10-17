package Parcial1.p3;
import java.util.ArrayList; // agregar para el desbordamiento
import java.util.LinkedList; // agregar para las tablas hash abiertas

import tp3.ej3.FuncionesHash;
import tp3.ej3.HashTable;

import java.util.ArrayList;
public class Punto3 {

public class busquedas {
    int[] tablaHash;
    int tam = 12; // tamaño
    


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

    public static void main(String[] args) {
        tablaHash tablaHash = new tablaHash(10);

        int[] keys = {125, 228, 172, 358, 288, 347};

        
        for (int key : keys) {
            hashTable.insert(key);
            hashTable.hash3(key);

        }
        
        hashTable.display();
    }
}
  
}
}
}

