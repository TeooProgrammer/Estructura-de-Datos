package Parcial2.V;

import java.util.Scanner;

public class MenuArbolB {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BTree bTree = null;
        while (true) {
            System.out.println("\n--- Menú Árbol B ---");
            System.out.println("1. Crear Árbol B (aridad = 5)");
            System.out.println("2. Insertar clave");
            System.out.println("3. Borrar clave");
            System.out.println("4. Buscar clave");
            System.out.println("5. Mostrar Árbol B");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    bTree = new BTree(5);
                    System.out.println("Árbol B creado con aridad = 5");
                    break;
                case 2:
                    if (bTree == null) {
                        System.out.println("Tiene que crear un Árbol B antes de insertar");
                        break;
                    }
                    System.out.print("Ingrese la clave a insertar: ");
                    String claveInsertar = scanner.nextLine();
                    bTree.addKey(claveInsertar);
                    System.out.println("Clave '" + claveInsertar + "' insertada");
                    break;
                case 3:
                    if (bTree == null) {
                        System.out.println("Tiene que crear un Árbol B antes de borrar");
                        break;
                    }
                    System.out.print("Ingrese la clave a borrar: ");
                    String claveBorrar = scanner.nextLine();
                    boolean eliminado = bTree.deleteKey(claveBorrar);
                    if (eliminado) {
                        System.out.println("Clave '" + claveBorrar + "' eliminada");
                    } else {
                        System.out.println("Clave '" + claveBorrar + "' no encontrada");
                    }
                    break;
                case 4:
                    if (bTree == null) {
                        System.out.println("Tiene que crear un Árbol B antes de buscar");
                        break;
                    }
                    System.out.print("Ingrese la clave a buscar: ");
                    String claveBuscar = scanner.nextLine();
                    boolean encontrada = bTree.searchKey(claveBuscar);
                    if (encontrada) {
                        System.out.println("Clave '" + claveBuscar + "' encontrada");
                    } else {
                        System.out.println("Clave '" + claveBuscar + "' no encontrada");
                    }
                    break;
                case 5:
                    if (bTree == null) {
                        System.out.println("Tiene que crear un Árbol B antes de mostrar");
                        break;
                    }
                    System.out.println("Contenido del Árbol B:");
                    Transversal transversal = new Transversal(bTree);
                    while (transversal.hasMoreElements()) {
                        BNode nodo = (BNode) transversal.nextElement();
                        Keys keys = nodo.getData();
                        for (int i = 0; i < keys.size; i++) {
                            System.out.print(keys.keyAt(i) + " ");
                        }
                        System.out.println();
                    }
                    break;
                case 6:
                    System.out.println("Saliendo del programa...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opcion no valida. Intente nuevamente");
            }
        }
    }
}

