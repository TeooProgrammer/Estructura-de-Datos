package arbolb;

import java.util.ArrayList;

public class Transversal implements java.util.Enumeration {
	
	private ArrayList<Object> nodos;
	
	public Transversal(ArbolB arbolB) {
		this.nodos = new ArrayList<Object>();
		
		if (arbolB.getRaiz() != null) {
			this.nodos.add(arbolB.getRaiz());
		}
	}
	
	public boolean hasMoreElements() {
		return (this.nodos.size() != 0);
	}
	
	public Object nextElement() {
		NodoB nodoB = (NodoB) this.nodos.get(0);
		
		this.nodos.remove(0);
		
		for (int i = 0; i < nodoB.grado(); i++) {
			nodos.add(nodoB.getHijo(i));
		}
		
		return nodoB;
	}
	
}
