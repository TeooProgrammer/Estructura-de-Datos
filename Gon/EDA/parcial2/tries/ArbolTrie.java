package tries;

public class ArbolTrie {
	
	private NodoTrie nodoRaiz;
	private int cantidad;
	
	/* Inmutables, a menos que alguien quiera agregar la Ñ */
	protected final static int PRIMER_POSICION = 0;
	protected final static int ULTIMA_POSICION = 26;
	
	public ArbolTrie(){
		this.nodoRaiz = null;
	}
	
	public void insertarPalabra(String palabra) {
		palabra = palabra.toLowerCase();
		
		NodoTrie nodoAuxiliar;
		int i = 0;
		int posicion;
		
		if (nodoRaiz == null){
			nodoRaiz = new NodoTrie();
		}
		
		nodoAuxiliar = nodoRaiz;
		
		while (i < palabra.length()){
			
			posicion = getPosicion(palabra.charAt(i));
			
			if (posicion == -1 || posicion > ULTIMA_POSICION) {
				break;
			} else {
				if (nodoAuxiliar.getValorEn(posicion) == null){
					nodoAuxiliar.setValorEn(posicion, new NodoTrie());
				}
				
				nodoAuxiliar = nodoAuxiliar.getValorEn(posicion);
				
				i++;
			}
		}
		
		nodoAuxiliar.setValorEn(PRIMER_POSICION, nodoAuxiliar);
	}
	
	public boolean buscarPalabra(String palabra) { 
		palabra = palabra.toLowerCase();
		
		NodoTrie nodoAuxiliar = this.nodoRaiz; 
		int i = 0;
		
		if (nodoAuxiliar == null) { // Si no hay raíz, no hay palabras.
			return false;
		}
		
		int posicion;
		
		while (i < palabra.length()) {
			posicion = getPosicion(palabra.charAt(i));
			
			if (posicion == -1 || posicion > ULTIMA_POSICION){
				break;
			} else {
				if (nodoAuxiliar.getValorEn(posicion) != null) {
					nodoAuxiliar = nodoAuxiliar.getValorEn(posicion);
					
					i++;
				} else { 
					return false;
				}
			}
		}
		
		if (nodoAuxiliar.getValorEn(PRIMER_POSICION) == nodoAuxiliar) {
			return true;
		} else {
			return false; 
		}
	}
	
	
	
	public void mostrarPalabras(){
		this.mostrarPalabras(this.nodoRaiz, "");
	}
	
	private void mostrarPalabras(NodoTrie nodo, String imp){	
		for (int i = 0; i <= ULTIMA_POSICION; i++) {
			if (nodo.getValorEn(i)!=null){
				if (i==0) {
					System.out.println(imp);
				} else {
					this.mostrarPalabras(nodo.getValorEn(i), imp+(recuperarCaracter(i)));
				}
			}
		}
	}
	
	public int contarPalabras(){
		this.cantidad = 0;
		contarPalabras(nodoRaiz);
		
		return this.cantidad;
	}
	
	private void contarPalabras(NodoTrie nodo){	
		for (int i = 0; i <= ULTIMA_POSICION; i++){
			
			if (nodo.getValorEn(i) != null){
				
				if (i == 0){
					cantidad++;
				} else {
					this.contarPalabras(nodo.getValorEn(i));
				}
			}
		}
	}
	
	public int contarPrefijos(){
		this.cantidad = 0;
		this.contarPrefijos(nodoRaiz);
		
		return this.cantidad;
	}
	
	private void contarPrefijos(NodoTrie nodo){
		boolean control = false;
		
		for (int i = 0; i <= ULTIMA_POSICION; i++){
			if (nodo.getValorEn(i) != null) {
				if (i != 0) {
					this.cantidad++;
					this.contarPrefijos(nodo.getValorEn(i));
					
					if (control) {
						this.cantidad++;
					}
				} else {
					this.cantidad--;
					control = true;
				}
			}
		}
	}
	
	public void buscarPrefijos(String bus){
		this.buscarPrefijos(nodoRaiz, "", bus);
	}
	
	private void buscarPrefijos(NodoTrie nodo, String imp , String bus){
		boolean ok = false;
		
		for (int i = 0; i <= ULTIMA_POSICION; i++) {
			if (nodo.getValorEn(i) != null) {
				if (i == 0) {
					if (!ok && (imp.length() > bus.length()) && (imp.substring(0, bus.length()).equals(bus))) {
						ok=true;
						System.out.println(imp);
					}
				} else {
					this.buscarPrefijos(nodo.getValorEn(i), imp+(recuperarCaracter(i)),bus);
				}
			}
		}
	}
	
	public static int getPosicion(char c){
		switch(c) {
			case '@': return 0; 
			case 'a': return 1; 
			case 'b': return 2; 
			case 'c': return 3;
			case 'd': return 4;
			case 'e': return 5; 
			case 'f': return 6;
			case 'g': return 7;
			case 'h': return 8; 
			case 'i': return 9; 
			case 'j': return 10;
			case 'k': return 11;
			case 'l': return 12; 
			case 'm': return 13;
			case 'n': return 14;
			case 'o': return 15;
			case 'p': return 16;
			case 'q': return 17; 
			case 'r': return 18; 
			case 's': return 19;
			case 't': return 20;
			case 'u': return 21; 
			case 'v': return 22; 
			case 'w': return 23; 
			case 'x': return 24; 
			case 'y': return 25; 
			case 'z': return 26; 
		}
		
		return -1;
	}
	
	public static int getPosicionAlt(char c){
		Character caux=new Character(c);
		
		if (c=='@') {
			return 0; 
		} else {
			if (c>='a' & c<='z') {
				return caux.hashCode()-96;
			} else {
				return -1; 
			}
		}
	}
	
	public static char recuperarCaracter(int val){
		char c='a';
		
		for (int i = 1; i < val; i++) {
			c++;
		}
		
		return c;
	}
	
}