package tp3;
import java.io.*;
public class ArchivoDeEnterosM{
    RandomAccessFile enteros;
    int[] tablaHash;
    int tam = 101; // tamaño
    public ArchivoDeEnterosM(String fisico){
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
    private int medioCuadrado(int clave){
        int cuadrado = clave * clave;
        String cuadradoStr = String.valueOf(cuadrado);
        int medioInicio = (cuadradoStr.length() / 2) - 1;
        int medioFin = medioInicio + 2;
        int digitosCentrales = Integer.parseInt(cuadradoStr.substring(medioInicio, medioFin));
        return digitosCentrales % tam;
    }
    void insertarEnTabla(int numero){
        int hash = medioCuadrado(numero);
        while (tablaHash[hash] != -1){
            hash = (hash + 1) % tam;
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
        ArchivoDeEnterosM archivo = new ArchivoDeEnterosM("enteros.dat");
        archivo.mostrarElementos();
    }
}
