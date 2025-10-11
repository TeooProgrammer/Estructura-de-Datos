package tp4.AVL.Practica;

/**
 * Implementa las operaciones del Árbol AVL, incluyendo inserción, eliminación, rotaciones,
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
        // Se asume que la altura se calcula correctamente cada vez.
        nodo.setFe(altura(nodo.getDerecho()) - altura(nodo.getIzquierdo()));

        // 2. Verificar y realizar el balanceo
        return balancear(nodo);
    }

    // --- NUEVOS MÉTODOS DE ELIMINACIÓN Y BALANCEO ---

    /**
     * Elimina una clave del árbol y realiza el rebalanceo si es necesario.
     */
    public void eliminar(int clave) {
        if (raiz == null) {
            System.out.println("\n--- ERROR: Árbol vacío. No se puede eliminar " + clave + " ---");
            return;
        }
        System.out.println("\n--- Eliminando " + clave + " ---");
        int alturaInicial = altura(raiz);
        raiz = eliminarRec(raiz, clave);
        
        // Si la raíz no es nula, y el árbol cambió de altura o se rotó, lo dibujamos.
        // Una forma simple es verificar si la raíz es nula o si la altura cambió.
        if (raiz != null || alturaInicial > 0) {
            dibujar();
        } else {
            System.out.println("Árbol vacío.");
        }
    }

    private NodoAVL eliminarRec(NodoAVL nodo, int clave) {
        if (nodo == null) return null; // Clave no encontrada

        // 1. Buscar el nodo a eliminar (similar a un BST normal)
        if (clave < nodo.getClave()) {
            nodo.setIzquierdo(eliminarRec(nodo.getIzquierdo(), clave));
        } else if (clave > nodo.getClave()) {
            nodo.setDerecho(eliminarRec(nodo.getDerecho(), clave));
        } else {
            // 2. ¡Nodo encontrado! (Lógica de eliminación de BST)

            // Caso 1 y 2: 0 o 1 hijo
            if (nodo.getIzquierdo() == null) {
                return nodo.getDerecho(); // Devuelve el hijo derecho (puede ser null)
            } else if (nodo.getDerecho() == null) {
                return nodo.getIzquierdo(); // Devuelve el hijo izquierdo
            }

            // Caso 3: 2 hijos
            // Encontramos el sucesor: el nodo más pequeño en el subárbol derecho.
            NodoAVL sucesor = encontrarMinimo(nodo.getDerecho());
            
            // Reemplazamos el nodo actual con el sucesor.
            nodo.setClave(sucesor.getClave()); 

            // Eliminamos recursivamente el sucesor del subárbol derecho (el sucesor 
            // no tendrá más de 1 hijo, por lo que el caso se simplifica).
            nodo.setDerecho(eliminarRec(nodo.getDerecho(), sucesor.getClave()));
        }

        // Si el nodo se hizo nulo (eliminación de una hoja o nodo con 1 hijo),
        // simplemente devolvemos null (esto sucede al retornar de un caso 1 o 2).
        if (nodo == null) return null;

        // 3. Actualizar Factor de Equilibrio (FE) y Balancear (lógica AVL)
        // Se ejecuta al regresar por la recursividad, desde el punto de eliminación 
        // hasta la raíz.
        nodo.setFe(altura(nodo.getDerecho()) - altura(nodo.getIzquierdo()));

        return balancear(nodo);
    }

    /**
     * Encuentra el nodo con la clave más pequeña en el subárbol dado (el más a la izquierda).
     */
    private NodoAVL encontrarMinimo(NodoAVL nodo) {
        NodoAVL actual = nodo;
        while (actual.getIzquierdo() != null) {
            actual = actual.getIzquierdo();
        }
        return actual;
    }

    // ---------------------------------------------
    // --- LÓGICA DE ROTACIONES Y ALTURA (SIN CAMBIOS) ---
    // ---------------------------------------------

    private NodoAVL balancear(NodoAVL nodo) {
        int fe = nodo.getFe();

        // Caso 1: Desbalance a la DERECHA (FE > 1)
        if (fe > 1) {
            // Rotación Izquierda Simple (Caso DD)
            if (nodo.getDerecho().getFe() >= 0) {
                System.out.println("-> ROTACIÓN SIMPLE IZQUIERDA en el nodo " + nodo.getClave());
                return rotacionIzquierda(nodo);
            } 
            // Rotación Derecha-Izquierda Doble (Caso DR)
            else {
                System.out.println("-> ROTACIÓN DOBLE DERECHA-IZQUIERDA en el nodo " + nodo.getClave());
                nodo.setDerecho(rotacionDerecha(nodo.getDerecho()));
                return rotacionIzquierda(nodo);
            }
        }

        // Caso 2: Desbalance a la IZQUIERDA (FE < -1)
        if (fe < -1) {
            // Rotación Derecha Simple (Caso II)
            if (nodo.getIzquierdo().getFe() <= 0) {
                System.out.println("-> ROTACIÓN SIMPLE DERECHA en el nodo " + nodo.getClave());
                return rotacionDerecha(nodo);
            } 
            // Rotación Izquierda-Derecha Doble (Caso IL)
            else {
                System.out.println("-> ROTACIÓN DOBLE IZQUIERDA-DERECHA en el nodo " + nodo.getClave());
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
        
        // Actualización de FE después de la rotación (Importante para eliminar)
        // Se usa la función altura() para recalcular de forma segura.
        x.setFe(altura(x.getDerecho()) - altura(x.getIzquierdo()));
        y.setFe(altura(y.getDerecho()) - altura(y.getIzquierdo()));
        
        return y;
    }

    private NodoAVL rotacionDerecha(NodoAVL y) {
        NodoAVL x = y.getIzquierdo();
        NodoAVL t2 = x.getDerecho();
        x.setDerecho(y);
        y.setIzquierdo(t2);
        
        // Actualización de FE después de la rotación (Importante para eliminar)
        // Se usa la función altura() para recalcular de forma segura.
        y.setFe(altura(y.getDerecho()) - altura(y.getIzquierdo()));
        x.setFe(altura(x.getDerecho()) - altura(x.getIzquierdo()));
        
        return x;
    }

    private int altura(NodoAVL nodo) {
        if (nodo == null) return 0;
        // La altura se calcula de forma recursiva, lo que hace que los FE se calculen 
        // correctamente en 'balancear' y 'rotacion'.
        return 1 + Math.max(altura(nodo.getIzquierdo()), altura(nodo.getDerecho()));
    }

    // ---------------------------------------------
    // --- FUNCIÓN DE DIBUJO PROLIJO MEJORADA (SIN CAMBIOS) ---
    // ---------------------------------------------

    /**
     * Dibuja el árbol completo en la consola, girado 90 grados a la izquierda,
     * para una visualización clara de las ramas.
     */
    public void dibujar() {
        System.out.println("🌳 Estado del Árbol AVL (Clave/FE) [Vista Lateral]:");
        if (raiz == null) {
            System.out.println("  (vacío)");
            return;
        }
        dibujarRec(raiz, "");
    }

    private void dibujarRec(NodoAVL nodo, String prefijo) {
        if (nodo != null) {
            // 1. Dibuja la rama DERECHA (Se verá ARRIBA del nodo actual)
            dibujarRec(nodo.getDerecho(), prefijo + "│  ");

            // 2. Imprime el NODO actual (con su prefijo de rama)
            String rama = (prefijo.isEmpty()) ? "" : "+--"; 
            String nodoInfo = nodo.getClave() + " (FE:" + nodo.getFe() + ")";
            
            System.out.println(prefijo + rama + nodoInfo);

            // 3. Dibuja la rama IZQUIERDA (Se verá ABAJO del nodo actual)
            dibujarRec(nodo.getIzquierdo(), prefijo + "│  ");
        }
    }
}