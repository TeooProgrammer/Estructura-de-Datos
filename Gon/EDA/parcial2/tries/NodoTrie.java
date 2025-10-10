package tries;

public class NodoTrie {
	
	NodoTrie[] caracteres;
	
	public NodoTrie(){
		caracteres = new NodoTrie[ArbolTrie.ULTIMA_POSICION + 1];
	}
	
	public NodoTrie getValorEn(int posicion){
		if (posicion >= caracteres.length || posicion < 0){
			return null;
		}
		
		return caracteres[posicion];
	}
	
	
	public void setValorEn(int posicion, NodoTrie nodoNuevo){
		if (posicion < caracteres.length && posicion >= 0){
			caracteres[posicion] = nodoNuevo;
		} else {
			System.err.println("E: El valor no pudo ser establecido porque el índice recibido es inválido.");
		}
	}
	
}