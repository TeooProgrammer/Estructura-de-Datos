package tp3;
import java.io.*;
import java.util.Arrays;

public class PuntoA{  
    RandomAccessFile enteros;
    int[] tabla;
    int tam = 101; // Tamaño 
    
    public PuntoA(String fisico) {
        try {
            tabla = new int[tam];
            Arrays.fill(tabla, -1);
            enteros = new RandomAccessFile(fisico, "rw");
            if (enteros.length() > 0) {
                enteros.seek(enteros.length());
            }
            for (int i = 0; i < 100; i++) {
                int n = (int) (Math.random() * (9999 - 1000 + 1) + 1000);
                enteros.writeInt(n);
            }
            guardarEnTablaHash();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    void guardarEnTablaHash() {
        try {
            enteros.seek(0);
            while (enteros.getFilePointer() < enteros.length()) {
                int numero = enteros.readInt();
                int posicion = numero % tam;
                while (tabla[posicion] != -1) {
                    posicion = (posicion + 1) % tam;
                }
                tabla[posicion] = numero;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void mostrarElementos() {
        System.out.println("Contenido de la tabla:");
        for (int i = 0; i < tabla.length; i++) {
            if (tabla[i] != -1) {
                System.out.println("Posición " + i + ": " + tabla[i]);
            }
        }
    }
    public static void main(String[] args) {
        PuntoA enteros = new PuntoA("enteros2.dat");
        enteros.mostrarElementos();
    }
}