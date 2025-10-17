package Parcial1.p3;
import java.util.ArrayList; // agregar para el desbordamiento
import java.util.LinkedList; // agregar para las tablas hash abiertas

import tp3.ej3.FuncionesHash;
import tp3.ej3.HashTable;

import java.util.ArrayList;
 // AGREGAR PARA EL DESBORDAMIENTO
class TablaHashDesbordamiento {
    static int[] tablaHash;
    ArrayList<Integer> desbordamiento; // arreglo para almacenar valores que no entran en la tabla principal
    static int tam = 12;

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

    private static int hash(int clave) {
        return clave % TablaHashDesbordamiento.getTam();
    }

    public static int getTam(){
        return tam;
    }

    static void addDesbordamiento(int numero) {
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
        TablaHashDesbordamiento tablaHash = new TablaHashDesbordamiento(10);

        int numero1 =845;
        int numero2=601;

        TablaHashDesbordamiento.addDesbordamiento(numero1);
        TablaHashDesbordamiento.addDesbordamiento(numero2);

        

}
  
}




