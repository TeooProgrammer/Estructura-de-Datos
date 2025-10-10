package tp4.AVL.Practica;

/**
 * Implementa las operaciones del Árbol AVL, incluyendo inserción, rotaciones,
 * cálculo del factor de equilibrio y una función de dibujo clara en la consola.
 */
public class ArbolAVL {
    private NodoAVL raiz;

    public NodoAVL getRaiz() { return raiz; }

    // --- LÓGICA DE INSERCIÓN Y BALANCEO ---

    /**
     * Inserta una clave en el árbol y realiza el balanceo si es necesario.
     */
    public void insertar(int clave) {
        System.out.println("\n--- Insertando " + clave + " ---");
        raiz = insertarRec(raiz, clave);
        dibujar();
    }

    private NodoAVL insertarRec(NodoAVL nodo, int clave) {
        if (nodo == null) return new NodoAVL(clave);

        if (clave < nodo.getClave()) {
            nodo.setIzquierdo(insertarRec(nodo.getIzquierdo(), clave));
        } else if (clave > nodo.getClave()) {
            nodo.setDerecho(insertarRec(nodo.getDerecho(), clave));
        } else return nodo; // Clave duplicada, no hacer nada

        // 1. Actualizar Factor de Equilibrio (FE)
        nodo.setFe(altura(nodo.getDerecho()) - altura(nodo.getIzquierdo()));

        // 2. Verificar y realizar el balanceo
        return balancear(nodo);
    }

    // --- LÓGICA DE ROTACIONES Y ALTURA ---

    private NodoAVL balancear(NodoAVL nodo) {
        int fe = nodo.getFe();

        // Caso 1: Desbalance a la DERECHA (FE > 1)
        if (fe > 1) {
            // Rotación Izquierda Simple (Caso DD)
            if (nodo.getDerecho().getFe() >= 0) {
                System.out.println("-> Rotación simple izquierda en " + nodo.getClave());
                return rotacionIzquierda(nodo);
            } 
            // Rotación Derecha-Izquierda Doble (Caso DR)
            else {
                System.out.println("-> Rotación doble derecha-izquierda en " + nodo.getClave());
                nodo.setDerecho(rotacionDerecha(nodo.getDerecho()));
                return rotacionIzquierda(nodo);
            }
        }

        // Caso 2: Desbalance a la IZQUIERDA (FE < -1)
        if (fe < -1) {
            // Rotación Derecha Simple (Caso II)
            if (nodo.getIzquierdo().getFe() <= 0) {
                System.out.println("-> Rotación simple derecha en " + nodo.getClave());
                return rotacionDerecha(nodo);
            } 
            // Rotación Izquierda-Derecha Doble (Caso IL)
            else {
                System.out.println("-> Rotación doble izquierda-derecha en " + nodo.getClave());
                // ERROR CORREGIDO: Usar getIzquierdo() en lugar de getClquierdo()
                nodo.setIzquierdo(rotacionIzquierda(nodo.getIzquierdo()));
                return rotacionDerecha(nodo);
            }
        }
        return nodo; // Ya está balanceado
    }

    private NodoAVL rotacionIzquierda(NodoAVL x) {
        NodoAVL y = x.getDerecho();
        NodoAVL t2 = y.getIzquierdo();
        y.setIzquierdo(x);
        x.setDerecho(t2);
        x.setFe(altura(x.getDerecho()) - altura(x.getIzquierdo()));
        y.setFe(altura(y.getDerecho()) - altura(y.getIzquierdo()));
        return y;
    }

    private NodoAVL rotacionDerecha(NodoAVL y) {
        NodoAVL x = y.getIzquierdo();
        NodoAVL t2 = x.getDerecho();
        x.setDerecho(y);
        y.setIzquierdo(t2);
        y.setFe(altura(y.getDerecho()) - altura(y.getIzquierdo()));
        x.setFe(altura(x.getDerecho()) - altura(x.getIzquierdo()));
        return x;
    }

    private int altura(NodoAVL nodo) {
        if (nodo == null) return 0;
        return 1 + Math.max(altura(nodo.getIzquierdo()), altura(nodo.getDerecho()));
    }

    // --- FUNCIÓN DE DIBUJO PROLIJO MEJORADA ---

    /**
     * Dibuja el árbol completo en la consola, girado 90 grados a la izquierda,
     * para una visualización clara de las ramas.
     */
    public void dibujar() {
        System.out.println("🌳 Estado del Árbol AVL (Clave/FE) [Vista Lateral]:");
        if (raiz == null) {
            System.out.println("  (vacío)");
            return;
        }
        // Empezamos dibujando desde la raíz con un prefijo vacío
        dibujarRec(raiz, "");
    }

    /**
     * Método recursivo para dibujar el árbol. 
     * El orden de impresión es: Derecha, Raíz, Izquierda.
     */
    private void dibujarRec(NodoAVL nodo, String prefijo) {
        if (nodo != null) {
            // 1. Dibuja la rama DERECHA (Se verá ARRIBA del nodo actual)
            dibujarRec(nodo.getDerecho(), prefijo + "│  ");

            // 2. Imprime el NODO actual (con su prefijo de rama)
            String rama = (prefijo.isEmpty()) ? "" : "+--"; // Raíz sin rama
            String nodoInfo = nodo.getClave() + " (FE:" + nodo.getFe() + ")";
            
            System.out.println(prefijo + rama + nodoInfo);

            // 3. Dibuja la rama IZQUIERDA (Se verá ABAJO del nodo actual)
            dibujarRec(nodo.getIzquierdo(), prefijo + "│  ");
        }
    }
}