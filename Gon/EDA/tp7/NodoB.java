package tp7;
public class NodoB {
    Llaves dato;
    NodoB[] hijos;
    int aridad;
    int size;
    NodoB(int aridad) {
        this.aridad = aridad;
        size = 0;
        hijos = new NodoB[aridad];
    }
    NodoB(int aridad, Llaves dato) {
        this.aridad = aridad;
        size = 0;
        hijos = new NodoB[aridad];
        this.dato = dato;
    }
    void cambiarDato(Llaves dato) {
        this.dato = dato;
    }
    Llaves obtenerDato() {
        return dato;
    }
    NodoB obtenerHijo(int indice) {
        if (indice >= size || indice < 0) {
            return null;
        }
        return (NodoB) hijos[indice];
    }
    NodoB agregarHijo(NodoB dato) {
        if (size == aridad) {
            return null; 
        }else {
            Llaves temp = dato.obtenerDato();
            NodoB nodoTemp = new NodoB(aridad, temp);
            hijos[size++] = dato;
            return nodoTemp;
        }
    }
    NodoB agregarHijo(NodoB dato, int indice) {
        Llaves temp = dato.obtenerDato();
        if (indice < 0 || indice >= size) {
            return agregarHijo(dato);
        }
        for (int i = size; i > indice; i--) {
            hijos[i] = hijos[i - 1];
        }
        NodoB nodoTemp = new NodoB(aridad, temp);
        //hijos[indice]=nodoTemp;
        hijos[indice] = dato;
        size++;
        return nodoTemp;
    }
    NodoB borrarHijo(int indice) {
        if (indice < 0 || indice >= size) {
            return null;
        }
        NodoB nodoTemp = (NodoB) hijos[indice];
        for (int i = indice + 1; i <= size - 1; i++) {
            hijos[i - 1] = hijos[i];
        }
        size--;
        return nodoTemp;
    }
    void borrarHijo(NodoB dato) {
        if (size > 0) {
            int j;
            for (int i = 0; i < size; i++) {
                if ((NodoB) hijos[i] == dato) {
                    borrarHijo(i);
                }
            }
        }
    }
    int grado() {
        return size;
    }
    public boolean esHoja() {
        return (hijos[0] == null);
    }
}
