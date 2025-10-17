package Parcial1.p3;

import java.util.Random;

import tp3.ej3.FuncionesHash;
import tp3.ej3.HashTable;

public class Main3 {
     public static void main(String[] args) {
        int tamañoTabla = 103; // numero primo cercano a 100

        System.out.println("========== PRUEBA DE FUNCIONES HASH ==========\n");

        probarFuncion(datos, tamañoTabla, "restas");
        probarFuncion(datos, tamañoTabla, "modulo");
        probarFuncion(datos, tamañoTabla, "medio");
        probarFuncion(datos, tamañoTabla, "truncamiento");
        probarFuncion(datos, tamañoTabla, "desplazamiento");
        probarFuncion(datos, tamañoTabla, "plegamiento");
    }

    public static void probarFuncion(int[] datos, int tamaño, String tipo) {
        HashTable tablaHash = new HashTable(tamaño);

        for (int clave : datos) {
            int pos = switch (tipo) {
                case "insertar" -> FuncionesHash.addSequential(125);
                case "modulo" -> FuncionesHash.modulo(clave, tamaño);
                case "medio" -> FuncionesHash.medioCuadrado(clave, tamaño);
                case "truncamiento" -> FuncionesHash.truncamiento(clave, tamaño);
                case "desplazamiento" -> FuncionesHash.desplazamiento(clave, tamaño);
                case "plegamiento" -> FuncionesHash.plegamiento(clave, tamaño);
                default -> throw new IllegalArgumentException("Función no válida");
            };
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
