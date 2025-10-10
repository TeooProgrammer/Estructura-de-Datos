import java.util.ArrayList;
import java.util.Random;

public class Principal {

	public static void main (String[] args) {
	
		Lista listaUno = new Lista();
		Lista listaDos = new Lista();
		Lista listaTres = new Lista();
		Random generadorDeNotas = new Random();
		
		listaUno.insertarNodo(new Alumno(38792177, generadorDeNotas.nextInt(9) + 1, "Ariel Machini")); // generadorDeNotas.nextInt(9) + 1 = Un número entero del 1 (por eso el + 1 [0 + 1 = 1]) al 10 (9 + 1 = 10).
		listaUno.insertarNodo(new Alumno(40103161, generadorDeNotas.nextInt(9) + 1, "Cinthia Lima"));
		listaUno.insertarNodo(new Alumno(12345678, generadorDeNotas.nextInt(9) + 1, "Diego Portillo"));
		
		listaDos.insertarNodo(new Alumno(23456789, generadorDeNotas.nextInt(9) + 1, "Francisco Estrada"));
		listaDos.insertarNodo(new Alumno(34123456, generadorDeNotas.nextInt(9) + 1, "Nicolás Sartini"));
		listaDos.insertarNodo(new Alumno(39987654, generadorDeNotas.nextInt(9) + 1, "Emiliano Agüero"));
		
		listaTres.insertarNodo(new Alumno(41432123, generadorDeNotas.nextInt(9) + 1, "Santiago Farinola"));
		listaTres.insertarNodo(new Alumno(36765432, generadorDeNotas.nextInt(9) + 1, "Vanina Gola"));
		listaTres.insertarNodo(new Alumno(34234567, 10, "Franco Trinidad")); // Franco siempre saca diez, ¡es un genio!
		
		System.out.println(">>> Elementos de la PRIMER LISTA (listados EN ORDEN):");
		
		ArrayList<Alumno> listaAlumnos = new ArrayList<Alumno>(); // De paso vamos agregando los alumnos a la lista resultante del ejercicio.
		Iterador iterador = listaUno.getIterador();
		
		while (iterador.quedanNodos()) {
			
			Alumno alumnoActual = iterador.getActual().getAlumno();
			
			System.out.println("\nNombre: " + alumnoActual.getNombre());
			System.out.println("Nota: " + alumnoActual.getNota());
			System.out.println("N.° de DNI: " + alumnoActual.getDni());
			
			listaAlumnos.add(alumnoActual);
			
			iterador.avanzar();
			
		}
		
		System.out.println("\n>>> Elementos de la SEGUNDA LISTA (listados EN ORDEN):");
		
		iterador = listaDos.getIterador();
		
		while (iterador.quedanNodos()) {
			
			Alumno alumnoActual = iterador.getActual().getAlumno();
			
			System.out.println("\nNombre: " + alumnoActual.getNombre());
			System.out.println("Nota: " + alumnoActual.getNota());
			System.out.println("N.° de DNI: " + alumnoActual.getDni());
			
			listaAlumnos.add(alumnoActual);
			
			iterador.avanzar();
			
		}
		
		System.out.println("\n>>> Elementos de la TERCER LISTA (listados EN ORDEN):");
		
		iterador = listaTres.getIterador();
		
		while (iterador.quedanNodos()) {
			
			Alumno alumnoActual = iterador.getActual().getAlumno();
			
			System.out.println("\nNombre: " + alumnoActual.getNombre());
			System.out.println("Nota: " + alumnoActual.getNota());
			System.out.println("N.° de DNI: " + alumnoActual.getDni());
			
			listaAlumnos.add(alumnoActual);
			
			iterador.avanzar();
			
		}
		
		System.out.println("\nAhora se procederá a generar una nueva lista (de tipo ArrayList<Alumno> ordenada por el método SHELLSORT):"); // ¡Mentira! Ya se generó. Ahora se va a ordenar. :-)
		
		Shellsort.ordenar(listaAlumnos);
		
		System.out.print("\n[ ");
		
		for (Alumno alumnoEnLista : listaAlumnos) {
			if (! alumnoEnLista.equals(listaAlumnos.get(listaAlumnos.size() - 1)))
				System.out.print(alumnoEnLista.getDni() + " | "); // Sólo se imprime el número de DNI porque al fin y al cabo es la parte más importante del alumno para este caso (ejercicio).
			else
				System.out.println(alumnoEnLista.getDni() + " ]");
		}
	
	}

}
