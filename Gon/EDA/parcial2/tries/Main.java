package tries;

public class Main {
	
	/*
	 * Nota: El punto F está respondido en la hoja de las consignas del examen
	 * parcial profe, se la dejé al profesor Daniel y le avisé que había
	 * respondido ahí.
	 */

	public static void main(String[] args) {
		ArbolTrie arbolTrie = new ArbolTrie();
		
		arbolTrie.insertarPalabra("Ariel");
		arbolTrie.insertarPalabra("Arbol");
		arbolTrie.insertarPalabra("Aro");
		arbolTrie.insertarPalabra("Arial");
		arbolTrie.insertarPalabra("Arcoiris");
		
		System.out.println("Cantidad actual de palabras: " + arbolTrie.contarPalabras()); // Punto A.
		System.out.println("Cantidad actual de prefijos: " + arbolTrie.contarPrefijos()); // Punto E.
		
		System.out.println("\nBuscar prefijos (AR):"); // Punto C.
		arbolTrie.buscarPrefijos("ar");
		
		System.out.println("\nBuscar prefijos (ARI):"); // Punto C.
		arbolTrie.buscarPrefijos("ari");
		
		System.out.println("\nMostrar palabras:"); // Punto B.
		arbolTrie.mostrarPalabras();
		
		System.out.println("\nBuscar palabra (ariel):");
		if (arbolTrie.buscarPalabra("ariel")) {
			System.out.println("PALABRA ENCONTRADA.");
		} else {
			System.out.println("PALABRA NO ENCONTRADA.");
		}
		
		System.out.println("\nBuscar palabra (pepe):");
		if (arbolTrie.buscarPalabra("pepe")) {
			System.out.println("PALABRA ENCONTRADA.");
		} else {
			System.out.println("PALABRA NO ENCONTRADA.");
		}
	}

}
