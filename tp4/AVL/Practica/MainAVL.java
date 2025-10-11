package tp4.AVL.Practica;
import java.util.Scanner;

public class MainAVL {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArbolAVL arbol = new ArbolAVL();

        System.out.println("=====================================");
        System.out.println("🌳 Árbol AVL Interactivo");
        System.out.println("El árbol se dibuja después de cada operación, mostrando la Clave y el Factor de Equilibrio (FE).");
        System.out.println("=====================================");

        while (true) {
            System.out.println("\n--- Menú ---");
            System.out.println("1. Insertar clave");
            System.out.println("2. Eliminar clave");
            System.out.println("3. Salir");
            System.out.print("Opción: ");

            if (sc.hasNextInt()) {
                int opcion = sc.nextInt();
                int clave;

                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese clave a insertar: ");
                        if (sc.hasNextInt()) {
                            clave = sc.nextInt();
                            arbol.insertar(clave);
                        } else {
                            System.out.println("Entrada inválida.");
                            sc.next();
                        }
                        break;
                    case 2:
                        System.out.print("Ingrese clave a eliminar: ");
                        if (sc.hasNextInt()) {
                            clave = sc.nextInt();
                            arbol.eliminar(clave);
                        } else {
                            System.out.println("Entrada inválida.");
                            sc.next();
                        }
                        break;
                    case 3:
                        System.out.println("\n=====================================");
                        System.out.println("Finalizado. Árbol AVL resultante:");
                        arbol.dibujar();
                        System.out.println("=====================================");
                        sc.close();
                        return;
                    default:
                        System.out.println("Opción no válida.");
                }
            } else {
                System.out.println("Entrada inválida. Ingrese un número de opción.");
                sc.next(); 
            }
        }
    }
}