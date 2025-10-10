public class Alumno {
	
	private int nota;
	private long dni;
	private String nombre;
	
	public Alumno (int dni, int nota, String nombre) {
		this.dni = dni;
		this.nota = nota;
		this.nombre = nombre;
	}
	
	public void setDni (long dni) {
		this.dni = dni;
	}
	
	public void setNota (int nota) {
		this.nota = nota;
	}
	
	public void setNombre (String nombre) {
		this.nombre = nombre;
	}
	
	public long getDni () {
		return this.dni;
	}
	
	public int getNota () {
		return this.nota;
	}
	
	public String getNombre () {
		return this.nombre;
	}
	
}
