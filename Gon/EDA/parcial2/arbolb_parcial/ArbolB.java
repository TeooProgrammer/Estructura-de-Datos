package arbolb_parcial;

import java.util.*;

public class ArbolB {

    NodoB raiz;

    int aridad;//es el valor m igual a 2n

    public ArbolB(int aridad){
        this.aridad = aridad + 1;
        raiz = null;
    }

    public void agregarLlave(Object llave){
        if (raiz == null){
            NodoB nuevoNodo = new NodoB(aridad + 1);
            Llaves llaves = new Llaves(aridad);
            //int aux=llaves.tamanio;
            llaves.agregarLlave(llave);
            nuevoNodo.cambiarDato(llaves);
            raiz = nuevoNodo;
        }
        //encuentra una hoja para insertar la llave. La hoja sera referenciada
        // por el nodoActual
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
        //insert llave into nodoActual
        //int aux=llavesActuales.tamanio;
        llavesActuales.agregarLlave(llave);
        //si un nodo contiene "aridad" numero de llaves, dividir el nodo en dos.
        //Repetir el paso para su padre y otros antencesores si es necesario
        while (llavesActuales.tamanio == aridad){
            int medio = (aridad + 1) / 2 - 1;
            llave = llavesActuales.llaveEn(medio);
            llavesActuales.borrarLlave(llave);
            NodoB nuevoNodo = new NodoB(aridad + 1);
            Llaves nuevasLlaves = new Llaves(aridad);
            nuevoNodo.cambiarDato(nuevasLlaves);
            //move keys from nodoActual to nuevoNodo
            for (int i = 0; i < (aridad + 1) / 2 - 1; i++){
                Object llaveTemp = llavesActuales.llaveEn(0);
                nuevasLlaves.agregarLlave(llaveTemp);
                llavesActuales.borrarLlave(llaveTemp);
            }
            //move children
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
                //raiz.hijos[0]=nuevoNodo;
                //raiz.hijos[1]=nodoActual;
                raiz.agregarHijo(nuevoNodo, 0);
                raiz.agregarHijo(nodoActual, 1);
                //raiz.tamanio=2;
                return;

            }

            nodoActual = (NodoB) nodos.pop();

            llavesActuales = nodoActual.obtenerDato();
            indice = llavesActuales.agregarLlave(llave);
            nodoActual.agregarHijo(nuevoNodo, indice);
            //nodoActual.hijos[indice]=nuevoNodo;
            //nodoActual.tamanio=indice; //
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
            comparaciones = comparaciones + llavesActuales.tamanio;
            nodoActual = nodoActual.obtenerHijo(indice);
            llavesActuales = nodoActual.obtenerDato();
            if (llave instanceof String){
                indice = llavesActuales.buscar((String) llave); 
            }else {
                indice = llavesActuales.buscar((Integer) llave);
            }
        }
        if (indice != -1){
            comparaciones = comparaciones + llavesActuales.tamanio;
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
        //busca una llave en el ArbolB
        while (nodoActual.grado() > 0){
            if (indice == -1) //	return true;
            {
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
        if (indice == -1 && nodoActual.grado() > 0){ //llave is in a non-leaf node
            llavesActuales.borrarLlave(llave);
            if (llave instanceof String){
                indice = llavesActuales.buscar((String) llave); 
            }else {
                indice = llavesActuales.buscar((Integer) llave);
            }
            nodos.push(nodoActual);
            Llaves llaveTemps;
            if (indice < nodoActual.grado() - 1){ //finds inorder succesor
                nodoActual = nodoActual.obtenerHijo(indice + 1);
                while (nodoActual.grado() > 0){
                    nodos.push(nodoActual);
                    nodoActual.obtenerHijo(0);//codigo original
                    nodoActual = nodoActual.obtenerHijo(0);
                }
                llaveTemps = nodoActual.obtenerDato();
                llave = llaveTemps.llaveEn(0);
            } else { //finds inorder predecessor
                nodoActual = nodoActual.obtenerHijo(indice);
                while (nodoActual.grado() > 0){
                    nodos.push(nodoActual);
                    nodoActual = nodoActual.obtenerHijo(nodoActual.grado() - 1);
                }
                llaveTemps = nodoActual.obtenerDato();
                llave = llaveTemps.llaveEn(llaveTemps.tamanio - 1);
            }
            llavesActuales.agregarLlave(llave);
            llaveTemps.borrarLlave(llave);
            llavesActuales = llaveTemps;
        } else {
            if (indice == -1){
                llavesActuales.borrarLlave(llave); 
            }else {
                return false; //llave is not in BTree

                    }}
        while (nodoActual != raiz
                && llavesActuales.tamanio < (aridad + 1) / 2 - 1){
            NodoB padre = (NodoB) nodos.peek();
            nodos.pop();
            Llaves llavesPadre = padre.obtenerDato();
            //find the indice of nodoActual in the children array of padreNode
            for (int i = 0; i < padre.grado(); i++){
                if (nodoActual == padre.obtenerHijo(i)){
                    indice = i;
                    break;
                }
            }
            if (indice > 0){ // nodoActual has left sibling
                NodoB hermanoIzq = padre.obtenerHijo(indice - 1);
                Llaves llavesIzq = hermanoIzq.obtenerDato();
                Object llaveTemp = llavesPadre.llaveEn(indice - 1);
                llavesPadre.borrarLlave(llaveTemp);
                if (llavesIzq.tamanio >= (aridad + 1) / 2){ //right rotation
                    Object llaveMovida = llavesIzq
                            .llaveEn(llavesIzq.tamanio - 1);
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
                    for (int i = 0; i < llavesActuales.tamanio; i++){
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
            } else { //nodoActual has right sibling
                NodoB hermanoDer = padre.obtenerHijo(indice + 1);
                Llaves llaveDer = hermanoDer.obtenerDato();
                Object llaveTemp = llavesPadre.llaveEn(indice);
                llavesPadre.borrarLlave(llaveTemp);
                if (llaveDer.tamanio >= (aridad + 1) / 2){ //left rotation
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
                    for (int i = 0; i < llaveDer.tamanio; i++){
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
            if (raiz.obtenerDato().tamanio == 0){
                if (raiz.grado() > 0){
                    raiz = raiz.obtenerHijo(0); 
                }else {
                    raiz = null;
                }
            }
        }
        return true;
    }

    /*    boolean borrarLlave(Object llave)
    {
    	if(raiz==null)
    		return false;
    	NodoB currentNode=raiz;
    	Llaves currentKeys=raiz.obtenerDato();
    	//
    	int index;
    	if (llave instanceof String)
    		index = currentKeys.buscar((String) llave);
    	else
    		index = currentKeys.buscar((Integer) llave);

    	Stack nodes=new Stack();
    	//busca una llave en el ArbolB
    	while(currentNode.grado()>0)
    	{
    		if(index==-1)
    			return true;
    		nodes.push(currentNode);
    		currentNode=currentNode.obtenerHijo(index);
    		currentKeys=currentNode.obtenerDato();
    		//
    		if (llave instanceof String)
    			index = currentKeys.buscar((String) llave);
    		else
    			index = currentKeys.buscar((Integer) llave);
    	}
    	if(index==-1 && currentNode.grado()>0)
    	{//key is in a non-leaf node
    		currentKeys.borrarLlave(llave);
    		//
    		if (llave instanceof String)
    			index = currentKeys.buscar((String) llave);
    		else
    			index = currentKeys.buscar((Integer) llave);

    		nodes.push(currentNode);
    		Llaves tempKeys;
    		if(index<currentNode.grado() - 1)
    		{//finds inorder succesor
    			currentNode=currentNode.obtenerHijo(index + 1);
    			while(currentNode.grado()>0)
    			{
    				nodes.push(currentNode);
    				currentNode.obtenerHijo(0);
    			}
    			tempKeys=currentNode.obtenerDato();
    			llave=tempKeys.llaveEn(0);
    		}
    		else
    		{//finds inorder predecessor
    			currentNode=currentNode.obtenerHijo(index);
    			while(currentNode.grado()>0)
    			{
    				nodes.push(currentNode);
    				currentNode=currentNode.obtenerHijo(currentNode.grado() - 1);
    			}
    			tempKeys=currentNode.obtenerDato();
    			llave=tempKeys.llaveEn(tempKeys.tamanio - 1);
    		}
    		currentKeys.agregarLlave(llave);
    		tempKeys.borrarLlave(llave);
    		currentKeys=tempKeys;
    	}
    	else
    	{
    		if(index==-1)
    			currentKeys.borrarLlave(llave);
    		else
    			return false; //llave is not in BTree
    	}
    	while(currentNode!=raiz && currentKeys.tamanio<(aridad + 1) / 2 - 1)
    	{
    		NodoB parent=(NodoB)nodes.peek();
    		nodes.pop();
    		Llaves parentKeys=parent.obtenerDato();
    		//find the index of currentNode in the children array of parentNode
    		for(int i=0; i<parent.grado(); i++)
    		{
    			if(currentNode==parent.obtenerHijo(i))
    			{
    				index=i;
    				break;
    			}
    		}
    		if(index>0)
    		{// currentNode has left sibling
    			NodoB leftSibling=parent.obtenerHijo(index - 1);
    			Llaves leftKeys=leftSibling.obtenerDato();
    			Object tempKey=parentKeys.llaveEn(index - 1);
    			parentKeys.borrarLlave(tempKey);
    			if(leftKeys.tamanio>=(aridad + 1) / 2)
    			{//right rotation
    				Object movedKey=leftKeys.llaveEn(leftKeys.tamanio - 1);
    				leftKeys.borrarLlave(movedKey);
    				parentKeys.agregarLlave(movedKey);
    				currentKeys.agregarLlave(tempKey);
    				if(currentNode.grado()>0)
    				{
    					NodoB movedNode=leftSibling.obtenerHijo(leftSibling.grado() - 1);
    					leftSibling.borrarHijo(movedNode);
    					currentNode.agregarHijo(movedNode,0);
    				}
    				return true;
    			}
    			else
    			{//node merge
    				leftKeys.agregarLlave(tempKey);
    				for(int i=0; i<currentKeys.tamanio; i++)
    				{
    					leftKeys.agregarLlave(currentKeys.llaveEn(i));
    					if(currentNode.grado()>0)
    					{
    						NodoB tempNode=currentNode.obtenerHijo(i);
    						leftSibling.agregarHijo(tempNode,leftSibling.grado());
    					}
    				}
    				if(currentNode.grado()>0)
    					leftSibling.agregarHijo(currentNode.obtenerHijo(currentNode.grado() - 1));
    				parent.borrarHijo(index);
    				currentNode=parent;
    				currentKeys=parentKeys;
    				continue;
    			}
    		}
    		else
    		{//currentNode has right sibling
    			NodoB rightSibling=parent.obtenerHijo(index - 1);
    			Llaves rightKeys=rightSibling.obtenerDato();
    			Object tempKey=parentKeys.llaveEn(index - 1);
    			parentKeys.borrarLlave(tempKey);
    			if(rightKeys.tamanio>=(aridad + 1) / 2)
    			{//left rotation
    				Object movedKey=rightKeys.llaveEn(0);
    				rightKeys.borrarLlave(movedKey);
    				parentKeys.agregarLlave(movedKey);
    				currentKeys.agregarLlave(tempKey);
    				if(currentNode.grado()>0)
    				{
    					NodoB movedNode=rightSibling.obtenerHijo(0);
    					rightSibling.borrarHijo(movedNode);
    					currentNode.agregarHijo(movedNode,currentNode.grado());
    				}
    				return true;
    			}
    			else
    			{
    				currentKeys.agregarLlave(tempKey);
    				for(int i=0; i<currentKeys.tamanio; i++)
    				{
    					currentKeys.agregarLlave(rightKeys.llaveEn(i));
    					if(currentNode.grado()>0)
    					{
    						NodoB tempNode=rightSibling.obtenerHijo(i);
    						currentNode.agregarHijo(tempNode);
    					}
    				}
    				if(currentNode.grado()>0)
    					currentNode.agregarHijo(rightSibling.obtenerHijo(rightSibling.grado() - 1));
    				parent.borrarHijo(index + 1);
    				currentNode=parent;
    				currentKeys=parentKeys;
    				continue;
    			}
    		}
    	}//while
    	if(currentNode==raiz)
    		if(raiz.obtenerDato().tamanio==0)
    			if(raiz.grado()>0)
    				raiz=raiz.obtenerHijo(0);
    			else
    				raiz=null;
    	return true;
    }*/
    /**
     * *****************************************************
     */
    NodoB obtenerRaiz(){
        return raiz;
    }

    void elementoRaiz(){
        Llaves llave = raiz.obtenerDato();
        for (int i = 0; i < llave.tamanio; i++){
            System.out.print(llave.llaveEn(i) + " ");
        }
    }

    public void muestra(){
        Transversal nodos = new Transversal(this);

        System.out.println("\nArbolB:");
        while (nodos.hasMoreElements()){
            NodoB tempNode = (NodoB) nodos.nextElement();
            Llaves llave = tempNode.obtenerDato();

            for (int i = 0; i < llave.tamanio; i++){
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

            for (int i = 0; i < llave.tamanio; i++){
                if ((Integer) llave.llaveEn(i) < c && (Integer) llave.llaveEn(i) > a){
                    System.out.print(llave.llaveEn(i) + " ");
                }
            }
        }
    }

    /**
     * **************************************************
     */
    public void muestraHojas(){
        Transversal nodos = new Transversal(this);

        System.out.println("\nArbolB: Hojas");
        while (nodos.hasMoreElements()){
            NodoB tempNode = (NodoB) nodos.nextElement();
            //
            if (tempNode.esHoja()){
                Llaves llave = tempNode.obtenerDato();
                for (int i = 0; i < llave.tamanio; i++){
                    System.out.print(llave.llaveEn(i) + " ");
                }
            }

        }
    }

    /**
     * *****************************************************
     */
    public void muestraNoHojas(){
        Transversal nodos = new Transversal(this);

        System.out.println("\nArbolB: No hojas");
        while (nodos.hasMoreElements()){
            NodoB tempNode = (NodoB) nodos.nextElement();
            //
            if (!tempNode.esHoja()){
                Llaves llave = tempNode.obtenerDato();
                for (int i = 0; i < llave.tamanio; i++){
                    System.out.print(llave.llaveEn(i) + " ");
                }
            }

        }
    }

    /**
     * **************************************************
     */
    public int cantidadLlaves(){
        Transversal nodos = new Transversal(this);
        int cont = 0;
        while (nodos.hasMoreElements()){
            NodoB tempNode = (NodoB) nodos.nextElement();
            cont = cont + tempNode.obtenerDato().cantidadElementos();
        }
        return cont;
    }

    /**
     * *********************************************
     */
    public int cantidadNodos(){
        Transversal nodos = new Transversal(this);

        int cont = 0;
        while (nodos.hasMoreElements()){
            cont++;
            nodos.nextElement();
        }
        return cont;
    }

    /**
     * ***********************************************
     */
    private int altura(NodoB nodo){
        if (nodo.esHoja()){
            return 0; 
        }else {
            return altura(nodo.obtenerHijo(0)) + 1;
        }
    }

    public int altura(){
        return altura(raiz);
    }

    /**
     * **************************************************
     * ejercicio 6 - tp7
     */
    public void inorden(){
        Stack pila = new Stack();
        NodoB actual = raiz;
        if (actual.tamanio == 0){
            actual.obtenerDato().mostrarLlaves(); 
        }else {
            pila.add(actual);
        }
        while (!pila.isEmpty()){
            actual = (NodoB) pila.pop();
            actual.obtenerDato().mostrarLlaves();
            if (actual.grado() > 0){
                for (int i = 0; i < actual.aridad; i++){
                    if (actual.obtenerHijo(i) != null){
                        pila.add(actual.obtenerHijo(i));
                    }
                }
            }
        }
    }
}
