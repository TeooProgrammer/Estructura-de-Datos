package Parcial1.p3;
import java.util.ArrayList; // agregar para el desbordamiento
import java.util.LinkedList; // agregar para las tablas hash abiertas
import java.util.ArrayList;
public class Punto3 {

public class busquedas {
    int[] tablaHash;
    int tam = 12; // tamaño
    
    public busquedas(String fisico){
        tablaHash = new int[tam];
        for (int i = 0; i < tam; i++){
            tablaHash[i] = -1;
        }
    }


 // AGREGAR PARA EL DESBORDAMIENTO
class TablaHashDesbordamiento {
    int[] tablaHash;
    ArrayList<Integer> desbordamiento; // arreglo para almacenar valores que no entran en la tabla principal
    int tam = 101;

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
}

}
}
