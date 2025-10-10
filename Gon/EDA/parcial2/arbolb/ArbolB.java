package arbolb;

import java.util.Stack;

public class ArbolB {
	private NodoB raiz;
	private int aridad;
	
	public ArbolB(int aridad) {
		this.aridad = aridad;
		this.raiz = null;
	}
	
	public void agregarClave(Object clave) {
		if (this.raiz == null) {
			NodoB nodoNuevo = new NodoB(this.aridad + 1);
			Claves valores = new Claves(this.aridad);
			int auxiliar = valores.getTamano();
			
			valores.agregarClave(clave);
			nodoNuevo.setValores(valores);
			this.raiz = nodoNuevo;
			return; // Con return se sale del método.
		}
		
		NodoB nodoActual = this.raiz;
		Claves valoresActuales = this.raiz.getValores();
		int indice;		
		Stack<Object> pilaNodos = new Stack<Object>();
		
		while (nodoActual.grado() > 0) {
			indice = valoresActuales.buscar(clave);
			
			if (indice == -1) {
				return;
			}
			
			pilaNodos.push(nodoActual);
			nodoActual = nodoActual.getHijo(indice);
			valoresActuales = nodoActual.getValores();
		}
		
		indice = valoresActuales.buscar(clave);
		
		if (indice == -1) {
			return;
		}
		
		int auxiliar = valoresActuales.getTamano();
		
		valoresActuales.agregarClave(clave);
		
		while (valoresActuales.getTamano() == this.aridad) {
			int mid = (this.aridad + 1) / 2 - 1;
			clave = valoresActuales.claveEn(mid);
			clavesAcuales
		}
	}
}
