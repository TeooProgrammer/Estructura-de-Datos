package tp4.AVL.Practica;
/**
 * Representa un nodo en un Árbol AVL.
 * Almacena la clave, el factor de equilibrio (fe) y los enlaces a los hijos.
 */
public class NodoAVL {
    private int clave;
    private int fe; // factor de equilibrio (Altura Derecho - Altura Izquierdo)
    private NodoAVL izquierdo;
    private NodoAVL derecho;

    public NodoAVL(int clave) {
        this.clave = clave;
        this.fe = 0;
    }

    // Getters y Setters
    public int getClave() { return clave; }
    public void setClave(int clave) { this.clave = clave; }

    public int getFe() { return fe; }
    public void setFe(int fe) { this.fe = fe; }

    public NodoAVL getIzquierdo() { return izquierdo; }
    public void setIzquierdo(NodoAVL izquierdo) { this.izquierdo = izquierdo; }

    public NodoAVL getDerecho() { return derecho; }
    public void setDerecho(NodoAVL derecho) { this.derecho = derecho; }
}
