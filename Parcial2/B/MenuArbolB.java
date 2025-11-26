package Parcial2.B;

import java.util.Scanner;

public class MenuArbolB {
    public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    BTree bTree = null;
     
    bTree = new BTree(5);
    System.out.println("Árbol B creado con aridad = 5");


    String claveInsertar = scanner.nextLine();
    bTree.addKey(claveInsertar);
    System.out.println("Clave '" + 20  + "' insertada");
    
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
    
    }

}


