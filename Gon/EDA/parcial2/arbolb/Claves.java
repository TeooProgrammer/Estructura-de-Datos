package arbolb;

public class Claves {
	
	private int aridad, tamano;
	private Object[] claves;
	
	public Claves(int aridad) {
		this.aridad = aridad;
		this.tamano = 0;
		this.claves = new Object[aridad];
	}
	
	public Object claveEn(int indice) {
		if (indice < 0 || indice >= this.tamano) {
			throw new IndexOutOfBoundsException("ERROR: El índice recibido (" + indice + ") no está dentro del rango permitido.");
		}
		
		return this.claves[indice];
	}
	
	public int buscar(Object clave) {
		for (int i = 0; i < this.tamano; i++) {
			int c = ((String) clave).compareTo((String) this.claves[i]);
			
			if (c < 0) {
				return i;
			} else if (c == 0) {
				return -1;
			}
		}
		
		return this.tamano;
	}
	
	public int agregarClave(Object clave) {
		int indice = buscar(clave);
		
		if (indice == -1) {
			return 1;
		}
		
		for (int j = this.tamano; j > indice; j--) {
			this.claves[j] = this.claves [j - 1];
		}
		
		this.claves[indice] = clave;
		this.tamano++;
		
		return indice;
	}
	
	public boolean eliminarClave(Object clave) {
		int indice = -1;
		
		for (int i = 0; i < this.tamano; i++) {
			int c = ((String) clave).compareTo((String) claves[i]);
			
			if (c < 0) {
				return false;
			} else if (c == 0) {
				indice = i;
				break;
			}
		}
		
		if (indice == -1) {
			return false;
		}
		
		for (int j = indice + 1; j < this.tamano; j++) {
			this.claves[j - 1] = this.claves[j];
		}
		
		this.tamano--;
		
		return true;
	}
	
	public int getAridad() {
		return this.aridad;
	}
	
	public void setAridad(int aridad) {
		this.aridad = aridad;
	}
	
	public int getTamano() {
		return this.tamano;
	}
	
	public Object[] getClaves() {
		return this.claves;
	}
	
}
