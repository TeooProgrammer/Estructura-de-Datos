public class Nodo {
	
	private Alumno alumno;
	private Nodo nodoSiguiente;
	
	public Nodo (Alumno alumno) {
		this.alumno = alumno;
		this.nodoSiguiente = null;
	}
	
	public void setAlumno (Alumno alumno) {
		this.alumno = alumno;
	}
	
	public void setNodoSiguiente (Nodo nodoSiguiente) {
		this.nodoSiguiente = nodoSiguiente;
	}
	
	public Alumno getAlumno () {
		return this.alumno;
	}
	
	public Nodo getNodoSiguiente () {
		return this.nodoSiguiente;
	}
	
}
