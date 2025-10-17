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
    
    public busquedas(String fisico){
        tablaHash = new int[tam];
        for (int i = 0; i < tam; i++){
            tablaHash[i] = -1;
        }
    }

    private int hash(int clave){
        return (7 * clave + 1) % 10;
    }

    void addRehashingC(int numero){
        int hash = hash(numero);
        int i = 1;
        while (tablaHash[hash] != -1){
            hash = (hash + i * i) % tam; //rehashing cuadrático
            i++;
        }
        tablaHash[hash] = numero;
    }

    public void addSequential(int value) {
        int place = getHash(value);
        if (tablaHash[place] != 0) {
            do {
                place = getNextPlace(place);
            } while (tablaHash[place] != 0);
        }
        tablaHash[place] = value;
    }

        private int getHash(int value) {
        return value % tablaHash.length;
    }

    private int getNextPlace(int place) {
        place++;
        if (place == tablaHash.length) {
            place = 0;
        }
        return place;
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

    public static void main(String[] args) {
   
   

        tablaHash.addRehashingC(125);

               /*case "modulo" -> FuncionesHash.modulo(clave, tamaño);
                case "medio" -> FuncionesHash.medioCuadrado(clave, tamaño);
                case "truncamiento" -> FuncionesHash.truncamiento(clave, tamaño);
                case "desplazamiento" -> FuncionesHash.desplazamiento(clave, tamaño);
                case "plegamiento" -> FuncionesHash.plegamiento(clave, tamaño);
                default -> throw new IllegalArgumentException("Función no válida");
            }; /* */
            tabla.insertar(clave, pos);
        }

        System.out.println("----- FUNCIÓN: " + tipo.toUpperCase() + " -----");
        System.out.println("Colisiones totales: " + tabla.getColisiones());
        System.out.println("Comparaciones totales: " + tabla.getComparaciones());
        System.out.printf("Promedio de comparaciones por inserción: %.2f%n",
                (double) tabla.getComparaciones() / 100);
        System.out.printf("Ocupación de la tabla: %.2f%%%n%n",
                (100.0 * tabla.getOcupadas() / tamaño));
    }
}
