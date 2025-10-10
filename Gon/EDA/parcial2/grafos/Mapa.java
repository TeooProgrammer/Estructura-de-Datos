package grafos;

public class Mapa extends Grafo {

	public Mapa(int cantidadNodos) {
		super(cantidadNodos);
	}

	public boolean existeConexion(int ciudadOrigen, int ciudadDestino) {
		if (this.matrizAdyacencia[ciudadOrigen - 1][ciudadDestino - 1] != SIN_CONEXION) {
			return true; // Existe conexión entre ciudades.
		} else {
			return false; // No existe conexión entre ciudades.
		}
	}
}
