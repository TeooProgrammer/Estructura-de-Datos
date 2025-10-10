package tp4.AVL.Practica;
import java.util.Scanner;

public class MainAVL {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArbolAVL arbol = new ArbolAVL();

        System.out.println("=====================================");
        System.out.println("🌳 Árbol AVL Interactivo");
        System.out.println("El árbol se dibuja después de cada inserción, mostrando la Clave y el Factor de Equilibrio (FE).");
        System.out.println("=====================================");
        System.out.println("Ingrese claves enteras para insertar (-1 para terminar):");

        // Ejemplo de pruebas de rotaciones: 10, 20, 30 (simple izquierda)
        // Ejemplo de pruebas de rotaciones: 30, 10, 20 (doble izquierda-derecha)
        
        while (true) {
            System.out.print("Clave: ");
            if (sc.hasNextInt()) {
                int clave = sc.nextInt();
                if (clave == -1) break;
                arbol.insertar(clave);
            } else {
                System.out.println("Entrada inválida. Ingrese un número entero.");
                sc.next(); // Consume la entrada inválida
            }
        }

        System.out.println("\n=====================================");
        System.out.println("Finalizado. Árbol AVL resultante:");
        arbol.dibujar();
        System.out.println("=====================================");
        sc.close();
    }
}