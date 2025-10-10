public class Iterador {
	
	private Nodo nodoCabeza;
	
	public Iterador (Nodo nodoCabeza) {
		this.nodoCabeza = nodoCabeza;
	}
	
	public Nodo getActual () {
		return nodoCabeza;
	}
	
	public void avanzar () {
		nodoCabeza = nodoCabeza.getNodoSiguiente();
	}
	
	public boolean quedanNodos () {
		return nodoCabeza != null;
	}
	
}
