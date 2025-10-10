import java.util.ArrayList;

public class Shellsort {

	public static void ordenar (ArrayList<Alumno> lista) {
		int intervalo = lista.size() / 2;
		
		while (intervalo > 0) {
			for (int i = intervalo; i < lista.size(); i++) {
				 int j = i - intervalo;
				 
				 while (j >= 0) {
					if (lista.get(j).getDni() <= lista.get(j + intervalo).getDni())
						j = 0;
					else
						intercambiar(lista, j, j + intervalo);
					
					j -= intervalo;
				}
			}
			
			intervalo /= 2;
		}
	}
	
	private static void intercambiar (ArrayList<Alumno> lista, int primeraPosicion, int segundaPosicion) {
		Alumno alumnoCastigado = lista.get(primeraPosicion); // Se manda al alumno a un rincón porque se coló en la fila de alumnos que está ordenada por n.° de DNI.
		
		lista.set(primeraPosicion, lista.get(segundaPosicion));
		lista.set(segundaPosicion, alumnoCastigado);
	}
	
}
