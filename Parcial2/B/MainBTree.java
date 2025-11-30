package Parcial2.B;

import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;

public class MainBTree {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Árbol B de orden N (máx. N claves por nodo) ===");
        System.out.print("Ingrese el orden N (N >= 3): ");
        int orden = sc.nextInt();

        if (orden < 3) {
            System.out.println("El orden mínimo es 3. Saliendo.");
            sc.close();
            return;
        }

        GestorArbolB gestor = new GestorArbolB(orden, sc);
        gestor.menu();

        sc.close();
    }
}

/* ============================================================ */
/* ======================   GESTOR   ========================== */
/* ============================================================ */

class GestorArbolB {

    private final ArbolB arbol;
    private final Scanner sc;
    private final int orden;

    public GestorArbolB(int orden, Scanner sc) {
        this.orden = orden;
        this.arbol = new ArbolB(orden);
        this.sc = sc;
    }

    public void menu() {
        int opcion;
        do {
            System.out.println("\n=== Menú Árbol B (orden = " + orden + ") ===");
            System.out.println("1) Insertar clave");
            System.out.println("2) Eliminar clave");
            System.out.println("3) Mostrar árbol (por niveles y detallado)");
            System.out.println("4) Mostrar claves en orden");
            System.out.println("0) Salir");
            System.out.print("Opción: ");
            while (!sc.hasNextInt()) {
                System.out.print("Ingrese un número: ");
                sc.next();
            }
            opcion = sc.nextInt();

            switch (opcion) {
                case 1 -> insertar();
                case 2 -> eliminar();
                case 3 -> mostrar();
                case 4 -> mostrarInOrder();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida");
            }
        } while (opcion != 0);
    }

    private void insertar() {
        System.out.print("Clave a insertar: ");
        int c = sc.nextInt();
        arbol.insertar(c);
        System.out.println("Insertado " + c);
    }

    private void eliminar() {
        System.out.print("Clave a eliminar: ");
        int c = sc.nextInt();
        arbol.eliminar(c);
        System.out.println("Eliminado (si existía) " + c);
    }

    private void mostrar() {
        System.out.println("\n=== Árbol por niveles ===");
        arbol.imprimirEstructura();

        System.out.println("\n=== Árbol detallado (para dibujarlo en la hoja) ===");
        arbol.imprimirDetallado();
    }

    private void mostrarInOrder() {
        System.out.println("\nClaves en orden:");
        arbol.imprimirEnOrden();
        System.out.println();
    }
}

/* ============================================================ */
/* ======================   NODO B   ========================== */
/* ============================================================ */

class NodoB {

    int[] claves;      // claves almacenadas
    NodoB[] hijos;     // punteros a hijos
    int n;             // cantidad de claves usadas
    boolean hoja;      // true si es hoja

    public NodoB(int orden, boolean hoja) {
        this.hoja = hoja;
        this.claves = new int[orden];        // máx N claves
        this.hijos = new NodoB[orden + 1];   // máx N+1 hijos
        this.n = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < n; i++) {
            sb.append(claves[i]);
            if (i < n - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}

/* ============================================================ */
/* =======================   ARBOL B   ======================== */
/* ============================================================ */

class ArbolB {

    private NodoB raiz;
    private final int orden;      // máx claves por nodo
    private final int maxClaves;  // = orden
    private final int minClaves;  // = ceil(orden/2) - 1

    public ArbolB(int orden) {
        this.orden = orden;
        this.maxClaves = orden;
        this.minClaves = (int) Math.ceil(orden / 2.0) - 1;
        this.raiz = new NodoB(orden, true);
    }

    /* ======================= INSERTAR ======================= */

    public void insertar(int clave) {
        // si la raíz está llena, partirla antes de bajar
        if (raiz.n == maxClaves) {
            NodoB nuevaRaiz = new NodoB(orden, false);
            nuevaRaiz.hijos[0] = raiz;
            split(nuevaRaiz, 0, raiz);
            raiz = nuevaRaiz;
        }
        insertarNoLleno(raiz, clave);
    }

    private void insertarNoLleno(NodoB nodo, int clave) {
        int i = nodo.n - 1;

        if (nodo.hoja) {
            // desplazar claves para hacer espacio
            while (i >= 0 && clave < nodo.claves[i]) {
                nodo.claves[i + 1] = nodo.claves[i];
                i--;
            }
            // evitar duplicados
            if (i >= 0 && nodo.claves[i] == clave) return;

            nodo.claves[i + 1] = clave;
            nodo.n++;
            return;
        }

        // buscar hijo donde cae la clave
        while (i >= 0 && clave < nodo.claves[i]) i--;
        i++; // hijo i

        // si el hijo está lleno, partirlo
        if (nodo.hijos[i].n == maxClaves) {
            split(nodo, i, nodo.hijos[i]);
            if (clave > nodo.claves[i]) i++;
        }

        insertarNoLleno(nodo.hijos[i], clave);
    }

    private void split(NodoB padre, int pos, NodoB lleno) {
        NodoB nuevo = new NodoB(orden, lleno.hoja);
        int mid = lleno.n / 2;

        nuevo.n = lleno.n - mid - 1;

        // copiar parte derecha al nuevo nodo
        for (int j = 0; j < nuevo.n; j++)
            nuevo.claves[j] = lleno.claves[mid + 1 + j];

        // copiar hijos si no es hoja
        if (!lleno.hoja) {
            for (int j = 0; j <= nuevo.n; j++)
                nuevo.hijos[j] = lleno.hijos[mid + 1 + j];
        }

        int subir = lleno.claves[mid];

        // reducir el nodo lleno a la parte izquierda
        lleno.n = mid;

        // mover hijos del padre para hacer espacio
        for (int j = padre.n; j >= pos + 1; j--)
            padre.hijos[j + 1] = padre.hijos[j];

        padre.hijos[pos + 1] = nuevo;

        // mover claves del padre
        for (int j = padre.n - 1; j >= pos; j--)
            padre.claves[j + 1] = padre.claves[j];

        padre.claves[pos] = subir;
        padre.n++;
    }

    /* ======================= ELIMINAR ======================= */

    public void eliminar(int clave) {
        if (raiz == null) return;

        eliminarRec(raiz, clave);

        // si la raíz quedó vacía y tiene hijos, bajamos un nivel
        if (raiz.n == 0 && !raiz.hoja)
            raiz = raiz.hijos[0];
    }

    private void eliminarRec(NodoB nodo, int clave) {
        int idx = 0;
        while (idx < nodo.n && clave > nodo.claves[idx]) idx++;

        if (idx < nodo.n && nodo.claves[idx] == clave) {
            // caso 1: la clave está en este nodo
            if (nodo.hoja) {
                // eliminar de hoja
                for (int i = idx; i < nodo.n - 1; i++)
                    nodo.claves[i] = nodo.claves[i + 1];
                nodo.n--;
                return;
            }

            // nodo interno
            if (nodo.hijos[idx].n > minClaves) {
                int pred = getMax(nodo.hijos[idx]);
                nodo.claves[idx] = pred;
                eliminarRec(nodo.hijos[idx], pred);
            } else if (nodo.hijos[idx + 1].n > minClaves) {
                int succ = getMin(nodo.hijos[idx + 1]);
                nodo.claves[idx] = succ;
                eliminarRec(nodo.hijos[idx + 1], succ);
            } else {
                fusionar(nodo, idx);
                eliminarRec(nodo.hijos[idx], clave);
            }
        } else {
            // caso 2: la clave NO está en este nodo
            if (nodo.hoja) return; // no existe

            boolean ultimo = (idx == nodo.n);

            if (nodo.hijos[idx].n == minClaves)
                llenar(nodo, idx);

            if (ultimo && idx > nodo.n)
                eliminarRec(nodo.hijos[idx - 1], clave);
            else
                eliminarRec(nodo.hijos[idx], clave);
        }
    }

    private int getMax(NodoB nodo) {
        while (!nodo.hoja)
            nodo = nodo.hijos[nodo.n];
        return nodo.claves[nodo.n - 1];
    }

    private int getMin(NodoB nodo) {
        while (!nodo.hoja)
            nodo = nodo.hijos[0];
        return nodo.claves[0];
    }

    private void llenar(NodoB padre, int idx) {
        if (idx > 0 && padre.hijos[idx - 1].n > minClaves)
            prestamoIzq(padre, idx);
        else if (idx < padre.n && padre.hijos[idx + 1].n > minClaves)
            prestamoDer(padre, idx);
        else {
            if (idx < padre.n)
                fusionar(padre, idx);
            else
                fusionar(padre, idx - 1);
        }
    }

    private void prestamoIzq(NodoB padre, int idx) {
        NodoB hijo = padre.hijos[idx];
        NodoB hermano = padre.hijos[idx - 1];

        for (int i = hijo.n - 1; i >= 0; i--)
            hijo.claves[i + 1] = hijo.claves[i];

        hijo.claves[0] = padre.claves[idx - 1];

        if (!hijo.hoja) {
            for (int i = hijo.n; i >= 0; i--)
                hijo.hijos[i + 1] = hijo.hijos[i];
            hijo.hijos[0] = hermano.hijos[hermano.n];
        }

        padre.claves[idx - 1] = hermano.claves[hermano.n - 1];

        hijo.n++;
        hermano.n--;
    }

    private void prestamoDer(NodoB padre, int idx) {
        NodoB hijo = padre.hijos[idx];
        NodoB hermano = padre.hijos[idx + 1];

        hijo.claves[hijo.n] = padre.claves[idx];

        if (!hijo.hoja)
            hijo.hijos[hijo.n + 1] = hermano.hijos[0];

        padre.claves[idx] = hermano.claves[0];

        for (int i = 1; i < hermano.n; i++)
            hermano.claves[i - 1] = hermano.claves[i];

        if (!hermano.hoja) {
            for (int i = 1; i <= hermano.n; i++)
                hermano.hijos[i - 1] = hermano.hijos[i];
        }

        hijo.n++;
        hermano.n--;
    }

    private void fusionar(NodoB padre, int idx) {
        NodoB hijo = padre.hijos[idx];
        NodoB hermano = padre.hijos[idx + 1];

        hijo.claves[hijo.n] = padre.claves[idx];

        for (int i = 0; i < hermano.n; i++)
            hijo.claves[hijo.n + 1 + i] = hermano.claves[i];

        if (!hijo.hoja) {
            for (int i = 0; i <= hermano.n; i++)
                hijo.hijos[hijo.n + 1 + i] = hermano.hijos[i];
        }

        hijo.n += hermano.n + 1;

        for (int i = idx + 1; i < padre.n; i++)
            padre.claves[i - 1] = padre.claves[i];

        for (int i = idx + 2; i <= padre.n; i++)
            padre.hijos[i - 1] = padre.hijos[i];

        padre.n--;
    }

    /* ======================= IMPRESIÓN ======================= */

    // por niveles (para ver forma general)
    public void imprimirEstructura() {
        if (raiz == null) {
            System.out.println("(árbol vacío)");
            return;
        }

        Queue<NodoB> cola = new LinkedList<>();
        cola.add(raiz);
        int nivel = 0;

        while (!cola.isEmpty()) {
            int size = cola.size();
            System.out.print("Nivel " + nivel + ": ");

            for (int i = 0; i < size; i++) {
                NodoB nodo = cola.poll();
                System.out.print(nodo + "   ");

                if (!nodo.hoja) {
                    for (int j = 0; j <= nodo.n; j++)
                        if (nodo.hijos[j] != null)
                            cola.add(nodo.hijos[j]);
                }
            }

            System.out.println();
            nivel++;
        }
    }

    // in-order (claves ordenadas)
    public void imprimirEnOrden() {
        imprimirEnOrdenRec(raiz);
    }

    private void imprimirEnOrdenRec(NodoB nodo) {
        if (nodo == null) return;

        if (nodo.hoja) {
            for (int i = 0; i < nodo.n; i++)
                System.out.print(nodo.claves[i] + " ");
        } else {
            for (int i = 0; i < nodo.n; i++) {
                imprimirEnOrdenRec(nodo.hijos[i]);
                System.out.print(nodo.claves[i] + " ");
            }
            imprimirEnOrdenRec(nodo.hijos[nodo.n]);
        }
    }

    /* ======================= IMPRESIÓN DETALLADA ======================= */

    public void imprimirDetallado() {
        if (raiz == null) {
            System.out.println("(árbol vacío)");
            return;
        }
        imprimirDetalladoRec(raiz, "raíz", 0);
    }

    private void imprimirDetalladoRec(NodoB nodo, String nombre, int nivel) {
        String indent = "  ".repeat(nivel);
        System.out.println(indent + "Nodo " + nombre + ": " + nodo);

        if (!nodo.hoja) {
            for (int i = 0; i <= nodo.n; i++) {
                if (nodo.hijos[i] != null) {
                    String nombreHijo = nombre.equals("raíz") ? String.valueOf(i) : nombre + "." + i;
                    imprimirDetalladoRec(nodo.hijos[i], nombreHijo, nivel + 1);
                }
            }
        }
    }
}
