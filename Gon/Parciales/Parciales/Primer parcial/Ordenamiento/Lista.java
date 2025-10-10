public class Lista {
	
	private Nodo primerNodo;
	
	public Lista () {
		this.primerNodo = null;
	}
	
	public void insertarNodo (Alumno alumno) {
		Nodo nuevoNodo = new Nodo(alumno);
		
		if (primerNodo == null) primerNodo = nuevoNodo; // La lista enlazada está vacía.
		else if (primerNodo.getAlumno().getDni() > nuevoNodo.getAlumno().getDni()) { // El DNI del alumno del nodo a insertar es menor que el del primer nodo de la lista enlazada.
			nuevoNodo.setNodoSiguiente(primerNodo);
			primerNodo = nuevoNodo;
		} else {
			Nodo nodoPuntero = primerNodo;
			
			while (nodoPuntero.getNodoSiguiente() != null && nodoPuntero.getNodoSiguiente().getAlumno().getDni() <= nuevoNodo.getAlumno().getDni()) nodoPuntero = nodoPuntero.getNodoSiguiente();
			
			nuevoNodo.setNodoSiguiente(nodoPuntero.getNodoSiguiente());
			nodoPuntero.setNodoSiguiente(nuevoNodo);
		}
	}
	
	public Iterador getIterador () {
		return new Iterador(primerNodo);
	}
}
