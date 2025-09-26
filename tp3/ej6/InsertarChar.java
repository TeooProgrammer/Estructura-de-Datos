package tp3.ej6;

import java.util.Arrays;
import java.util.Scanner;

class Nodo {
    private char dato;
    private Nodo sig;

    public Nodo(char dato) { this.dato = dato; this.sig = null; }

    public char getDato() { return dato; }
    public void setDato(char dato) { this.dato = dato; }
    public Nodo getSig() { return sig; }
    public void setSig(Nodo sig) { this.sig = sig; }
}

class NodoArbol {
    private char dato;
    private NodoArbol izq, der;

    public NodoArbol(char dato) { this.dato = dato; this.izq = null; this.der = null; }

    public char getDato() { return dato; }
    public void setDato(char dato) { this.dato = dato; }
    public NodoArbol getIzq() { return izq; }
    public void setIzq(NodoArbol izq) { this.izq = izq; }
    public NodoArbol getDer() { return der; }
    public void setDer(NodoArbol der) { this.der = der; }
}

public class InsertarChar {

    private char[] serie;

    public InsertarChar(char[] serie) { this.serie = serie.clone(); }

    // a) Arreglo original
    public void arregloOriginal() {
        System.out.println("Arreglo original: " + Arrays.toString(serie));
    }

    // b) Arreglo ordenado
    public void arregloOrdenado() {
        char[] ordenado = serie.clone();
        Arrays.sort(ordenado);
        System.out.println("Arreglo ordenado: " + Arrays.toString(ordenado));
    }

    // c) Lista enlazada original
    public Nodo listaOriginal() {
        Nodo cabeza = new Nodo(serie[0]);
        Nodo actual = cabeza;
        for (int i = 1; i < serie.length; i++) {
            actual.setSig(new Nodo(serie[i]));
            actual = actual.getSig();
        }
        return cabeza;
    }

    // d) Lista enlazada ordenada
    public Nodo listaOrdenada() {
        char[] ordenado = serie.clone();
        Arrays.sort(ordenado);
        Nodo cabeza = new Nodo(ordenado[0]);
        Nodo actual = cabeza;
        for (int i = 1; i < ordenado.length; i++) {
            actual.setSig(new Nodo(ordenado[i]));
            actual = actual.getSig();
        }
        return cabeza;
    }

    // e) Árbol binario de búsqueda
    public NodoArbol insertarArbol(NodoArbol raiz, char dato) {
        if (raiz == null) return new NodoArbol(dato);
        if (dato < raiz.getDato()) raiz.setIzq(insertarArbol(raiz.getIzq(), dato));
        else raiz.setDer(insertarArbol(raiz.getDer(), dato));
        return raiz;
    }

    public NodoArbol arbolBusqueda() {
        NodoArbol raiz = null;
        for (char c : serie) raiz = insertarArbol(raiz, c);
        return raiz;
    }

    // Mostrar lista enlazada
    public void mostrarLista(Nodo cabeza) {
        Nodo actual = cabeza;
        while (actual != null) {
            System.out.print(actual.getDato() + " -> ");
            actual = actual.getSig();
        }
        System.out.println("null");
    }

    // Mostrar árbol in-order
    public void mostrarInOrder(NodoArbol raiz) {
        if (raiz != null) {
            mostrarInOrder(raiz.getIzq());
            System.out.print(raiz.getDato() + " ");
            mostrarInOrder(raiz.getDer());
        }
    }

    // MAIN con switch-case y búsqueda
    public static void main(String[] args) {
        char[] serie = { 'c','I','6','&','M','0','o','(','Q','8','%','3','E','@','A','H','u','9' };
        InsertarChar ejercicio = new InsertarChar(serie);

        Scanner sc = new Scanner(System.in);

        System.out.println("=== Estructuras de datos disponibles ===");
        System.out.println("1 - Arreglo original");
        System.out.println("2 - Arreglo ordenado");
        System.out.println("3 - Lista enlazada original");
        System.out.println("4 - Lista enlazada ordenada");
        System.out.println("5 - Árbol BST");
        System.out.print("Elige qué estructura probar (1-5): ");
        int opcion = sc.nextInt();
        sc.nextLine(); // limpiar buffer

        System.out.print("Ingrese el carácter a buscar: ");
        char buscar = sc.nextLine().charAt(0);

        switch(opcion) {
            case 1:
                ejercicio.arregloOriginal();
                int pos = -1;
                for (int i = 0; i < serie.length; i++) {
                    if (serie[i] == buscar) { pos = i; break; }
                }
                System.out.println("Búsqueda de '" + buscar + "' en arreglo original: " + (pos != -1 ? "Posición " + pos : "No encontrado"));
                break;

            case 2:
                ejercicio.arregloOrdenado();
                char[] ordenado = serie.clone();
                Arrays.sort(ordenado);
                int izquierda = 0, derecha = ordenado.length - 1, mid;
                pos = -1;
                while (izquierda <= derecha) {
                    mid = (izquierda + derecha) / 2;
                    if (ordenado[mid] == buscar) { pos = mid; break; }
                    else if (ordenado[mid] < buscar) izquierda = mid + 1;
                    else derecha = mid - 1;
                }
                System.out.println("Búsqueda binaria de '" + buscar + "' en arreglo ordenado: " + (pos != -1 ? "Posición " + pos : "No encontrado"));
                break;

            case 3:
                Nodo listaOrig = ejercicio.listaOriginal();
                System.out.print("Lista original: ");
                ejercicio.mostrarLista(listaOrig);
                Nodo actual = listaOrig;
                boolean encontrado = false;
                while (actual != null) {
                    if (actual.getDato() == buscar) { encontrado = true; break; }
                    actual = actual.getSig();
                }
                System.out.println("Búsqueda de '" + buscar + "' en lista original: " + (encontrado ? "Encontrado" : "No encontrado"));
                break;

            case 4:
                Nodo listaOrd = ejercicio.listaOrdenada();
                System.out.print("Lista ordenada: ");
                ejercicio.mostrarLista(listaOrd);
                actual = listaOrd;
                encontrado = false;
                while (actual != null) {
                    if (actual.getDato() == buscar) { encontrado = true; break; }
                    actual = actual.getSig();
                }
                System.out.println("Búsqueda de '" + buscar + "' en lista ordenada: " + (encontrado ? "Encontrado" : "No encontrado"));
                break;

            case 5:
                NodoArbol arbol = ejercicio.arbolBusqueda();
                System.out.print("Recorrido in-order: ");
                ejercicio.mostrarInOrder(arbol);
                System.out.println();
                NodoArbol nodoActual = arbol;
                encontrado = false;
                while (nodoActual != null) {
                    if (buscar == nodoActual.getDato()) { encontrado = true; break; }
                    else if (buscar < nodoActual.getDato()) nodoActual = nodoActual.getIzq();
                    else nodoActual = nodoActual.getDer();
                }
                System.out.println("Búsqueda de '" + buscar + "' en BST: " + (encontrado ? "Encontrado" : "No encontrado"));
                break;

            default:
                System.out.println("Opción inválida.");
        }

        sc.close();
    }
}
