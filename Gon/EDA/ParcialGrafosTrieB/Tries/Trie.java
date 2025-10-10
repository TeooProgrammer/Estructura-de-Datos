package ParcialGrafosTrieB.Tries;

//Matriz
class Trie {
    private TrieNode nodoRaiz;
	private int cantidad;
	private int begin = 0;
	private int end = 26;
	private int nodos=0;
	public Trie(){
		this.nodoRaiz = null;
	}
	public void insertWord(String palabra) {
		palabra = palabra.toLowerCase();
		
		TrieNode aux;
		int i = 0;
		int pos;
		
		if (nodoRaiz == null){
			nodoRaiz = new TrieNode(end);
		}
		
		aux = nodoRaiz;
		
		while (i < palabra.length()){
			
			pos = getPosition(palabra.charAt(i));
			
			if (pos == -1 || pos > end) {
				break;
			} else {
				if (aux.getValueAt(pos) == null){
					aux.setValueAt(pos, new TrieNode(end));
					nodos++;
				}
				
				aux = aux.getValueAt(pos);
				
				i++;
			}
		}
		aux.setValueAt(begin, aux);
	}
	
	public boolean searchWord(String palabra) { 
		palabra = palabra.toLowerCase();
		
		TrieNode aux = this.nodoRaiz; 
		int i = 0;
		
		if (aux == null) {
			return false;
		}
		
		int pos;
		
		while (i < palabra.length()) {
			pos = getPosition(palabra.charAt(i));
			
			if (pos == -1 || pos > end){
				break;
			} else {
				if (aux.getValueAt(pos) != null) {
					aux = aux.getValueAt(pos);
					
					i++;
				} else { 
					return false;
				}
			}
		}
		
		if (aux.getValueAt(begin) == aux) {
			return true;
		} else {
			return false; 
		}
	}
	
	//Buscar parecidas
	public boolean buscarPalabrasParecidas(String palabra) { 
		palabra = palabra.toLowerCase();
		TrieNode aux = this.nodoRaiz; 
		int i = 0;
		
		if (aux == null) {
			return false;
		}
		
		int pos;
		while (i < palabra.length()) {
			pos = getPosition(palabra.charAt(i));
			if (pos == -1 || pos > end) {
				break;
			} else {
				if (aux.getValueAt(pos) != null) {
					aux = aux.getValueAt(pos);
					i++;
				} else{
					System.out.println("\nNo se encontró la palabra: "+palabra);
					buscarSimilares(palabra.substring(0, palabra.length() / 2));
					System.out.println();
					return false;
				}
			}
		}
		
		if (aux.getValueAt(begin) == aux) {
			return true;
		} else{
			System.out.println("\nNo se encontró la palabra: "+palabra);
			buscarSimilares(palabra.substring(0, palabra.length() / 2));
			System.out.println();
			return false; 
		}
	}
	private void buscarSimilares(String prefijo) {
		System.out.println("\nPalabras similares:");
		buscarPrefijos(this.nodoRaiz, "", prefijo);
	}
	//
	
	
	public void mostrarPalabras(){
		this.mostrarPalabras(this.nodoRaiz, "");
	}
	
	private void mostrarPalabras(TrieNode nodo, String imp){	
		for (int i = 0; i <= end; i++) {
			if (nodo.getValueAt(i)!=null){
				if (i==0) {
					System.out.println(imp);
				} else {
					this.mostrarPalabras(nodo.getValueAt(i), imp+(recuperarCaracter(i)));
				}
			}
		}
	}
	
	public int contarPalabras(){
		this.cantidad = 0;
		contarPalabras(nodoRaiz);
		
		return this.cantidad;
	}
	
	private void contarPalabras(TrieNode nodo){	
		for (int i = 0; i <= end; i++){
			
			if (nodo.getValueAt(i) != null){
				
				if (i == 0){
					cantidad++;
				} else {
					contarPalabras(nodo.getValueAt(i));
				}
			}
		}
	}
	public int contarPrefijos() {
		TrieNode aux=nodoRaiz;
		if (aux == null) {
			return 0;
		}
		return contarPrefijos(aux) - 1; // - 1 al descontar la raiz
	}
	
	private int contarPrefijos(TrieNode nodo) {
		if (nodo == null) {
			return 0;
		} 
		int prefijos = 0;
		int hijos = 0; 
		for (int i = 1; i <= end; i++){
			if (nodo.getValueAt(i) != null) {
				hijos++;
				prefijos += contarPrefijos(nodo.getValueAt(i));
			}
		}
		if (hijos > 1) {
			prefijos++;
		}
		return prefijos;
	}
	
	public void buscarPrefijos(String pref){
		this.buscarPrefijos(nodoRaiz, "", pref);
	}
	
	private void buscarPrefijos(TrieNode nodo, String imp , String pref){
		boolean ok = false;
		
		for (int i = 0; i <= end; i++) {
			if (nodo.getValueAt(i) != null) {
				if (i == 0) {
					if (!ok && (imp.length() > pref.length()) && (imp.substring(0, pref.length()).equals(pref))) {
						ok=true;
						System.out.println(imp);
					}
				} else {
					this.buscarPrefijos(nodo.getValueAt(i), imp+(recuperarCaracter(i)),pref);
				}
			}
		}
	}
	
	public static int getPosition(char c){
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
	 
	public static char recuperarCaracter(int val){
		char c='a';
		
		for (int i = 1; i < val; i++) {
			c++;
		}
		
		return c;
	}
	
	public int contarNodos() {
		return nodos;
	}
	
	
	public void contarAccesos(String palabra){
		int num=contarAccesosP(palabra);
		if (num==-1){
			System.out.println("La palabra "+palabra+" no se encuentra en el Trie");
		}else{
			System.out.println("La cantidad de accesos para "+ palabra+" es de: "+num);
		}
	}

	private int contarAccesosP(String palabra) {
		palabra = palabra.toLowerCase();
		TrieNode aux = nodoRaiz;
		int accesos = 0;
	
		if (aux == null) {
			return -1; 
		}
	
		for (int i = 0; i < palabra.length(); i++) {
			int pos = getPosition(palabra.charAt(i));
	
			if (pos == -1 || pos > end) {
				return -1;
			}
	
			if (aux.getValueAt(pos) == null) {
				return -1; // la palabra no existe en el Trie
			}
	
			aux = aux.getValueAt(pos);
			accesos++;
		}
	
		return accesos;
	}
		
}

