package grafos;

public class Principal {
	public static void main(String[] arguments) {
		Mapa mapa = new Mapa(4);
		
		mapa.insertarNodo(1); // Comandante Luis Piedrabuena
		mapa.insertarNodo(2); // Río Gallegos
		mapa.insertarNodo(3); // Santa Cruz
		mapa.insertarNodo(4); // Barcelona (España)
		
		mapa.agregarConexion(1, 2, 2); // Para conectarse con Río Gallegos, primero debe pasar por Santa Cruz.
		mapa.agregarConexion(1, 3, 1);
		mapa.agregarConexion(2, 3, 1);
		
		// Lo mismo, pero con las rutas al revés (es un multigrafo ya que la conexión es en ambos sentidos).
		mapa.agregarConexion(2, 1, 2);
		mapa.agregarConexion(3, 1, 1);
		mapa.agregarConexion(3, 2, 1);
		
		System.out.println("CASO DE PRUEBA 1.1:");
		System.out.println("> ¿Existe conexión entre Comandante Luis Piedrabuena y Río Gallegos? (Resultado esperado: Sí):");
		
		if (mapa.existeConexion(1, 2)) {
			System.out.println("Sí");
		} else {
			System.out.println("No");
		}
		
		System.out.println("\nCASO DE PRUEBA 1.2:");
		System.out.println("> ¿Existe conexión entre Río Gallegos y Comandante Luis Piedrabuena? (Resultado esperado: Sí):");
		
		if (mapa.existeConexion(2, 1)) {
			System.out.println("Sí");
		} else {
			System.out.println("No");
		}
		
		System.out.println("\nCASO DE PRUEBA 2:");
		System.out.println("> ¿Existe conexión entre Río Gallegos y Santa Cruz? (Resultado esperado: Sí):");
		
		if (mapa.existeConexion(2, 3)) {
			System.out.println("Sí");
		} else {
			System.out.println("No");
		}
		
		System.out.println("\nCASO DE PRUEBA 3:");
		System.out.println("> ¿Existe conexión entre Río Gallegos y Barcelona? (Resultado esperado: No):");
		
		if (mapa.existeConexion(2, 4)) {
			System.out.println("Sí");
		} else {
			System.out.println("No. A menos que se tome un avión");
		}
		
		System.out.println("\nCASO DE PRUEBA 4:");
		System.out.println("> ¿Existe conexión entre Santa Cruz y Barcelona? (Resultado esperado: No):");
		
		if (mapa.existeConexion(3, 4)) {
			System.out.println("Sí");
		} else {
			System.out.println("No (ídem CP3)");
		}
		
		System.out.println("\nCASO DE PRUEBA 5:");
		System.out.println("> ¿Existe conexión entre Comandante Luis Piedrabuena y Barcelona? (Resultado esperado: No):");
		
		if (mapa.existeConexion(1, 4)) {
			System.out.println("Sí");
		} else {
			System.out.println("No (ídem CP3)");
		}
		
		System.out.println("\nSe pueden hacer todavía más casos de prueba para abarcar a todos los casos, pero con las que se hicieron debería bastar.");
	}
}
