package tp7;
import java.util.*;
public class ArbolB {
    NodoB raiz;
    int aridad;
    public ArbolB(int aridad){
        this.aridad = aridad + 1;
        raiz = null;
    }
    public void agregarLlave(Object llave){
        if (raiz == null){
            NodoB nuevoNodo = new NodoB(aridad + 1);
            Llaves llaves = new Llaves(aridad);
            llaves.agregarLlave(llave);
            nuevoNodo.cambiarDato(llaves);
            raiz = nuevoNodo;
        }
        NodoB nodoActual = raiz;
        Llaves llavesActuales = raiz.obtenerDato();
        int indice;
        Stack nodos = new Stack();
        while (nodoActual.grado() > 0){
            if (llave instanceof String){
                indice = llavesActuales.buscar((String) llave); 
            }else {
                indice = llavesActuales.buscar((Integer) llave);
            }
            if (indice == -1){
                return;
            }
            nodos.push(nodoActual);
            nodoActual = nodoActual.obtenerHijo(indice);
            llavesActuales = nodoActual.obtenerDato();
        }
        if (llave instanceof String){
            indice = llavesActuales.buscar((String) llave); 
        }else {
            indice = llavesActuales.buscar((Integer) llave);
        }
        if (indice == -1){
            return;
        }
        llavesActuales.agregarLlave(llave);
        while (llavesActuales.size == aridad){
            int medio = (aridad + 1) / 2 - 1;
            llave = llavesActuales.llaveEn(medio);
            llavesActuales.borrarLlave(llave);
            NodoB nuevoNodo = new NodoB(aridad + 1);
            Llaves nuevasLlaves = new Llaves(aridad);
            nuevoNodo.cambiarDato(nuevasLlaves);
            for (int i = 0; i < (aridad + 1) / 2 - 1; i++){
                Object llaveTemp = llavesActuales.llaveEn(0);
                nuevasLlaves.agregarLlave(llaveTemp);
                llavesActuales.borrarLlave(llaveTemp);
            }
            if (nodoActual.grado() > 0){
                for (int i = 0; i <= (aridad + 1) / 2 - 1; i++){
                    NodoB hijo = nodoActual.obtenerHijo(0);
                    nuevoNodo.agregarHijo(hijo, i);
                    nodoActual.borrarHijo(0);
                }
            }
            if (nodoActual == raiz){
                raiz = new NodoB(aridad + 1);
                Llaves llaveTemps = new Llaves(aridad);
                llaveTemps.agregarLlave(llave);
                raiz.cambiarDato(llaveTemps);
                raiz.agregarHijo(nuevoNodo, 0);
                raiz.agregarHijo(nodoActual, 1);
                return;

            }

            nodoActual = (NodoB) nodos.pop();

            llavesActuales = nodoActual.obtenerDato();
            indice = llavesActuales.agregarLlave(llave);
            nodoActual.agregarHijo(nuevoNodo, indice);
        }
    }

    public boolean buscarLlave(Object llave){
        int comparaciones = 0;
        if (raiz == null){
            return false;
        }
        NodoB nodoActual = raiz;
        Llaves llavesActuales = raiz.obtenerDato();
        int indice;
        if (llave instanceof String){
            indice = llavesActuales.buscar((String) llave); 
        }else {
            indice = llavesActuales.buscar((Integer) llave);
        }
        while (nodoActual.grado() > 0){
            if (indice == -1){
                if (llave instanceof String){
                    comparaciones = comparaciones + llavesActuales.buscar((String) llave) + 1;
                } else {
                    comparaciones = comparaciones + llavesActuales.buscar((Integer) llave) + 1;
                    return true;
                }
            }
            comparaciones = comparaciones + llavesActuales.size;
            nodoActual = nodoActual.obtenerHijo(indice);
            llavesActuales = nodoActual.obtenerDato();
            if (llave instanceof String){
                indice = llavesActuales.buscar((String) llave); 
            }else {
                indice = llavesActuales.buscar((Integer) llave);
            }
        }
        if (indice != -1){
            comparaciones = comparaciones + llavesActuales.size;
            return false;
        } else {
            if (llave instanceof String){
                comparaciones = comparaciones
                        + llavesActuales.buscar((String) llave) + 1; 
            }else {
                comparaciones = comparaciones
                        + llavesActuales.buscar((Integer) llave) + 1;
            }
            return true;
        }
    }

    public boolean borrarLlave(Object llave){
        if (raiz == null){
            return false;
        }
        NodoB nodoActual = raiz;
        Llaves llavesActuales = raiz.obtenerDato();
        int indice;
        if (llave instanceof String){
            indice = llavesActuales.buscar((String) llave); 
        }else {
            indice = llavesActuales.buscar((Integer) llave);
        }
        Stack nodos = new Stack();
        while (nodoActual.grado() > 0){
            if (indice == -1){
                break;
            }
            nodos.push(nodoActual);
            nodoActual = nodoActual.obtenerHijo(indice);
            llavesActuales = nodoActual.obtenerDato();
            if (llave instanceof String){
                indice = llavesActuales.buscar((String) llave); 
            }else {
                indice = llavesActuales.buscar((Integer) llave);
            }
        }
        if (indice == -1 && nodoActual.grado() > 0){
            llavesActuales.borrarLlave(llave);
            if (llave instanceof String){
                indice = llavesActuales.buscar((String) llave); 
            }else {
                indice = llavesActuales.buscar((Integer) llave);
            }
            nodos.push(nodoActual);
            Llaves llaveTemps;
            if (indice < nodoActual.grado() - 1){
                nodoActual = nodoActual.obtenerHijo(indice + 1);
                while (nodoActual.grado() > 0){
                    nodos.push(nodoActual);
                    nodoActual.obtenerHijo(0);
                    nodoActual = nodoActual.obtenerHijo(0);
                }
                llaveTemps = nodoActual.obtenerDato();
                llave = llaveTemps.llaveEn(0);
            } else {
                nodoActual = nodoActual.obtenerHijo(indice);
                while (nodoActual.grado() > 0){
                    nodos.push(nodoActual);
                    nodoActual = nodoActual.obtenerHijo(nodoActual.grado() - 1);
                }
                llaveTemps = nodoActual.obtenerDato();
                llave = llaveTemps.llaveEn(llaveTemps.size - 1);
            }
            llavesActuales.agregarLlave(llave);
            llaveTemps.borrarLlave(llave);
            llavesActuales = llaveTemps;
        } else {
            if (indice == -1){
                llavesActuales.borrarLlave(llave); 
            }else {
                return false;

                    }}
        while (nodoActual != raiz
                && llavesActuales.size < (aridad + 1) / 2 - 1){
            NodoB padre = (NodoB) nodos.peek();
            nodos.pop();
            Llaves llavesPadre = padre.obtenerDato();
            for (int i = 0; i < padre.grado(); i++){
                if (nodoActual == padre.obtenerHijo(i)){
                    indice = i;
                    break;
                }
            }
            if (indice > 0){
                NodoB hermanoIzq = padre.obtenerHijo(indice - 1);
                Llaves llavesIzq = hermanoIzq.obtenerDato();
                Object llaveTemp = llavesPadre.llaveEn(indice - 1);
                llavesPadre.borrarLlave(llaveTemp);
                if (llavesIzq.size >= (aridad + 1) / 2){ //right rotation
                    Object llaveMovida = llavesIzq
                            .llaveEn(llavesIzq.size - 1);
                    llavesIzq.borrarLlave(llaveMovida);
                    llavesPadre.agregarLlave(llaveMovida);
                    llavesActuales.agregarLlave(llaveTemp);
                    if (nodoActual.grado() > 0){
                        NodoB nodoMovido = hermanoIzq.obtenerHijo(hermanoIzq
                                .grado() - 1);
                        hermanoIzq.borrarHijo(nodoMovido);
                        nodoActual.agregarHijo(nodoMovido, 0);
                    }
                    return true;
                } else { //node merge
                    llavesIzq.agregarLlave(llaveTemp);
                    for (int i = 0; i < llavesActuales.size; i++){
                        llavesIzq.agregarLlave(llavesActuales.llaveEn(i));
                        if (nodoActual.grado() > 0){
                            NodoB nodoTemp = nodoActual.obtenerHijo(i);
                            hermanoIzq
                                    .agregarHijo(nodoTemp, hermanoIzq.grado());
                        }
                    }
                    if (nodoActual.grado() > 0){
                        hermanoIzq.agregarHijo(nodoActual
                                .obtenerHijo(nodoActual.grado() - 1));
                    }
                    padre.borrarHijo(indice);
                    nodoActual = padre;
                    llavesActuales = llavesPadre;
                    continue;
                }
            } else {
                NodoB hermanoDer = padre.obtenerHijo(indice + 1);
                Llaves llaveDer = hermanoDer.obtenerDato();
                Object llaveTemp = llavesPadre.llaveEn(indice);
                llavesPadre.borrarLlave(llaveTemp);
                if (llaveDer.size >= (aridad + 1) / 2){
                    Object llaveMovida = llaveDer.llaveEn(0);
                    llaveDer.borrarLlave(llaveMovida);
                    llavesPadre.agregarLlave(llaveMovida);
                    llavesActuales.agregarLlave(llaveTemp);
                    if (nodoActual.grado() > 0){
                        NodoB nodoMovido = hermanoDer.obtenerHijo(0);
                        hermanoDer.borrarHijo(nodoMovido);
                        nodoActual.agregarHijo(nodoMovido, nodoActual.grado());
                    }
                    return true;
                } else {
                    llavesActuales.agregarLlave(llaveTemp);
                    for (int i = 0; i < llaveDer.size; i++){
                        llavesActuales.agregarLlave(llaveDer.llaveEn(i));
                        if (nodoActual.grado() > 0){
                            NodoB nodoTemp = hermanoDer.obtenerHijo(i);
                            nodoActual.agregarHijo(nodoTemp);
                        }
                    }
                    if (nodoActual.grado() > 0){
                        nodoActual.agregarHijo(hermanoDer
                                .obtenerHijo(hermanoDer.grado() - 1));
                    }
                    padre.borrarHijo(indice + 1);
                    nodoActual = padre;
                    llavesActuales = llavesPadre;
                    continue;
                }
            }
        }//while
        if (nodoActual == raiz){
            if (raiz.obtenerDato().size == 0){
                if (raiz.grado() > 0){
                    raiz = raiz.obtenerHijo(0); 
                }else {
                    raiz = null;
                }
            }
        }
        return true;
    }
    NodoB obtenerRaiz(){
        return raiz;
    }

    void elementoRaiz(){
        Llaves llave = raiz.obtenerDato();
        for (int i = 0; i < llave.size; i++){
            System.out.print(llave.llaveEn(i) + " ");
        }
    }

    public void muestra(){
        Transversal nodos = new Transversal(this);

        System.out.println("\nArbolB:");
        while (nodos.hasMoreElements()){
            NodoB tempNode = (NodoB) nodos.nextElement();
            Llaves llave = tempNode.obtenerDato();

            for (int i = 0; i < llave.size; i++){
                System.out.print(llave.llaveEn(i) + " ");
            }

        }
    }

    public void muestraValor(int a, int c){
        Transversal nodos = new Transversal(this);

        System.out.println("\nArbolB:");
        while (nodos.hasMoreElements()){
            NodoB tempNode = (NodoB) nodos.nextElement();
            Llaves llave = tempNode.obtenerDato();

            for (int i = 0; i < llave.size; i++){
                if ((Integer) llave.llaveEn(i) < c && (Integer) llave.llaveEn(i) > a){
                    System.out.print(llave.llaveEn(i) + " ");
                }
            }
        }
    }

    public void muestraHojas(){
        Transversal nodos = new Transversal(this);

        System.out.println("\nArbolB: Hojas");
        while (nodos.hasMoreElements()){
            NodoB tempNode = (NodoB) nodos.nextElement();
            //
            if (tempNode.esHoja()){
                Llaves llave = tempNode.obtenerDato();
                for (int i = 0; i < llave.size; i++){
                    System.out.print(llave.llaveEn(i) + " ");
                }
            }

        }
    }
    public void muestraNoHojas(){
        Transversal nodos = new Transversal(this);

        System.out.println("\nArbolB: No hojas");
        while (nodos.hasMoreElements()){
            NodoB tempNode = (NodoB) nodos.nextElement();
            //
            if (!tempNode.esHoja()){
                Llaves llave = tempNode.obtenerDato();
                for (int i = 0; i < llave.size; i++){
                    System.out.print(llave.llaveEn(i) + " ");
                }
            }

        }
    }
    public int cantidadLlaves(){
        Transversal nodos = new Transversal(this);
        int cont = 0;
        while (nodos.hasMoreElements()){
            NodoB tempNode = (NodoB) nodos.nextElement();
            cont = cont + tempNode.obtenerDato().cantidadElementos();
        }
        return cont;
    }
    public int cantidadNodos(){
        Transversal nodos = new Transversal(this);

        int cont = 0;
        while (nodos.hasMoreElements()){
            cont++;
            nodos.nextElement();
        }
        return cont;
    }
}
