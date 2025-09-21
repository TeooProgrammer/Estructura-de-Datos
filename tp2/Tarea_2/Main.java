package tp2.Tarea_2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GestorEnemigo gestor = new GestorEnemigo();

        System.out.print("¿cuantos enemigos vas a ingresar?: ");
        int cantidad = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < cantidad; i++) {
            System.out.println("enemigo #" + (i + 1));
            System.out.print("nombre: ");
            String nombre = sc.nextLine();

            System.out.print("nivel de peligrosidad: ");
            int peligrosidad = sc.nextInt();
            sc.nextLine(); 

            gestor.agregarEnemigo(new Enemigo(nombre, peligrosidad));
        }

        System.out.println("\n--- ordenando enemigos por peligrosidad ---");
        gestor.OrdenarPorPeligrosidad();

        System.out.println("\nenemigos ordenados (de mayor a menor):");
        for (Enemigo e : gestor.getEnemigos()) {
            System.out.println("atacando a " + e.getNombre() +
                               " con peligrosidad " + e.getPeligrosidad());
        }

        sc.close();
    }
}
