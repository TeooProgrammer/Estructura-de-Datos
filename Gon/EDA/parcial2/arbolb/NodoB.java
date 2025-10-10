package arbolb;

public class NodoB {
	private Claves valores;
	private NodoB[] hijos;
	private int aridad, tamano;
	
	public NodoB(int aridad) {
		this.aridad = aridad;
		this.tamano = 0;
		this.hijos = new NodoB[aridad];
	}
	
	public NodoB(int aridad, Claves valores) {
		this.aridad = aridad;
		this.tamano = 0;
		this.hijos = new NodoB[aridad];
		this.valores = valores;
	}
	
	public Claves getValores() {
		return this.valores;
	}
	
	public void setValores(Claves valores) {
		this.valores = valores;
	}
	
	public NodoB getHijo(int indice) {
		if (indice < 0 || indice >= this.tamano) {
			throw new IndexOutOfBoundsException("ERROR: El índice recibido (" + indice + ") no está dentro del rango permitido.");
		}
		
		return (NodoB) this.hijos[indice];
	}
	
	public NodoB agregarHijo(NodoB hijo) {
		if (this.tamano == this.aridad) {
			return null;
		} else {
			Claves valoresTemporal = hijo.getValores();
			NodoB nodoTemporal = new NodoB(this.aridad, valoresTemporal);
			this.hijos[this.tamano++] = nodoTemporal;
			
			return nodoTemporal;
		}
	}
	
	public NodoB agregarHijo(NodoB hijo, int indice) {
		Claves valoresTemporal = hijo.getValores();
		
		if (indice < 0 || indice >= this.tamano) {
			System.out.println("\nAVISO: Se recibió un índice fuera del rango permitido. Se utilizará el método agregarHijo(NodoB) en lugar de agregarHijo(NodoB, int).\n");
			this.agregarHijo(hijo);
		}
		
		for (int i = this.tamano; i > indice; i--) {
			this.hijos[i] = this.hijos[i - 1];
		}
		
		NodoB nodoTemporal = new NodoB(aridad, valoresTemporal);
		
		this.hijos[indice] = nodoTemporal;
		this.tamano++;
		
		return nodoTemporal;
	}
	
	public NodoB eliminarHijo(int indice) {
		if (indice < 0 || indice >= this.tamano) {
			throw new IndexOutOfBoundsException("ERROR: El índice recibido (" + indice + ") no está dentro del rango permitido.");
		}
		
		NodoB nodoTemporal = (NodoB) this.hijos[indice];
		
		for (int i = indice; i < this.tamano - 1; i ++) {
			this.hijos[i] = this.hijos[i + 1],
		}
		
		this.tamano--;
		
		return nodoTemporal;
	}
	
	public void eliminarHijo(NodoB hijo) {
		if (this.tamano > 0) {
			for (int i = 0; i < this.tamano; i++) {
				if ((NodoB) this.hijos[i] == hijo) {
					this.eliminarHijo(i);
				}
			}
		}
	}
	
	public int grado() {
		return this.tamano;
	}
	
}
