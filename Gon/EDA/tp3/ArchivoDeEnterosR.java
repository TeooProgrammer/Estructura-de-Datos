package tp3;
import java.io.*;
public class ArchivoDeEnterosR{
    RandomAccessFile enteros;
    int[] tablaHash;
    int tam = 101; // tamaño
    public ArchivoDeEnterosR(String fisico){
        tablaHash = new int[tam];
        for (int i = 0; i < tam; i++){
            tablaHash[i] = -1;
        }
        try{
            enteros = new RandomAccessFile(fisico, "rw");
            if (enteros.length() > 0){
                enteros.seek(enteros.length());
            }
            for (int i = 0; i < 100; i++){
                int n = (int) (Math.random() * (9999 - 1000 + 1) + 1000);
                enteros.writeInt(n);
            }
            guardarEnteros();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    private void guardarEnteros(){
        try{
            enteros.seek(0);
            while (enteros.getFilePointer() < enteros.length()){
                int num = enteros.readInt();
                insertarEnTabla(num);
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    private int hash1(int clave){
        return (7 * clave + 1) % tam; //H1(K) = (7K + 1) % 10
    }
    private int hash2(int clave){
        return 1 + (clave % (tam - 1)); //H2(K) = 1 + (K % (tam - 1))
    }
    void insertarEnTabla(int numero){
        int hash = hash1(numero);
        int incremento = hash2(numero);
        int i = 0;
        while (tablaHash[hash] != -1){
            hash = (hash + incremento) % tam;
            i++;
        }
        tablaHash[hash] = numero;
    }
    public void mostrarElementos(){
        System.out.println("Elementos:");
        for (int i = 0; i < tam; i++){
            if (tablaHash[i] != -1){
                System.out.println("Posicion " + i + ": " + tablaHash[i]);
            }
        }
    }
    public static void main(String[] args) {
        ArchivoDeEnterosR archivo = new ArchivoDeEnterosR("enteros.dat");
        archivo.mostrarElementos();
    }
}