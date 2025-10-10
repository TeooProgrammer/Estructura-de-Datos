package grafos;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

public class Grafo {
	
	/* Componentes del grafo */
	private Nodo[] nodos;
	protected int[][] matrizAdyacencia;

	/* Propiedades inherentes del grafo */
	private int posicionNodo;
	private int cantidadNodos;

	protected final static int SIN_CONEXION = Integer.MAX_VALUE;
	
	public Grafo(int cantidadNodos){
		this.cantidadNodos = cantidadNodos;
		nodos = new Nodo[cantidadNodos];
		posicionNodo = 0;
		matrizAdyacencia = new int[cantidadNodos][cantidadNodos];
		matrizAdyacencia = cargarMatriz();
	}
	
	public void insertarNodo(Integer valor){
		this.nodos[this.posicionNodo] = new Nodo();
		this.nodos[this.posicionNodo].setValor(valor);
		
		this.posicionNodo++;
	}
	
	public void agregarConexion(Integer valorNodoOrigen, Integer valorNodoDestino, int pesoConexion){
		int posicionOrigen = getPosicionNodo(valorNodoOrigen);
		int posicionDestino = getPosicionNodo(valorNodoDestino);
		
		if (posicionOrigen != -1 && posicionDestino != -1) { // Se verifica que existan nodos en las posiciones recibidas.
			this.matrizAdyacencia[posicionOrigen][posicionDestino] = pesoConexion;
		}
	}
	
	private int getPosicionNodo(Integer valor){
		int indice = 0;
		int posicionNodo = -1;
		boolean fueEncontrado = false;
		
		while (indice < this.cantidadNodos && fueEncontrado == false){
			if (nodos[indice].getValor().equals(valor)){
				fueEncontrado = true;
				posicionNodo = indice;
			}
			
			indice++;
		}
		
		return posicionNodo;
	}
	
	/* El algoritmo de Dijkstra */
	public void algoritmoDijkstra(){ 
		System.out.print("	");
		for (int cont=0;cont<cantidadNodos;cont++){
			System.out.print((nodos[cont].getValor())+"	|	");
			
		}
		System.out.println("\n--------------------------------------------"
				+ "-----------------------------------------------------");
		
		for (int posicion=0; posicion<cantidadNodos; posicion++) {
			System.out.print((nodos[posicion].getValor())+":	");
			Vector<Integer> caminos = dijkstra(posicion);
			
			for (int i=0; i<cantidadNodos; i++) {
				if (caminos.elementAt(i) != SIN_CONEXION){
					System.out.print(caminos.elementAt(i)+"	|	");
				}else{
					System.out.print("-	|	");
				}
			}
			
			System.out.println();
		}
	}
	
	private Vector<Integer> dijkstra(int vertice){ 
		int vs;
		Vector<Integer> distancia = new Vector<Integer>(cantidadNodos); 
		Vector<Integer> aVisitar = new Vector<Integer>(cantidadNodos);
		
		for (vs = 0; vs < cantidadNodos; vs++) {
			Integer duv = null;
			
			if (vs == vertice){
				duv = new Integer(0);//Donde sea igual se pone cero. Es decir   
			}else{ 
				duv = new Integer(SIN_CONEXION);
			}
			
			distancia.insertElementAt(duv, vs); //Se rellena el vector de  distancia con Max.value
			aVisitar.addElement(new Integer(vs)); //Se rellena el vector aVisitar con los nodos del grafo(n1,n2,n3...)
		}
		
		while(!aVisitar.isEmpty()) {
			Integer verticeMinimo = verticeMinimo(distancia, aVisitar.iterator()); //Busco el vertice minimo para 
			aVisitar.removeElement(verticeMinimo);			
			int du = ((Integer)distancia.get(verticeMinimo.intValue()));
			if(du != SIN_CONEXION) {
				Enumeration<Integer> adyacentes = adjacents(verticeMinimo); 
				while(adyacentes.hasMoreElements()) { 
					Integer verticeAdyacente = (Integer)adyacentes.nextElement(); 
					if(aVisitar.contains(verticeAdyacente)) { 
						//int cuw = getEdge(u, w).getCoste(); 
						int valorVertice = matrizAdyacencia[verticeMinimo][verticeAdyacente];
						if(du + valorVertice < ((Integer)distancia.get(verticeAdyacente.intValue())).intValue()) 
							distancia.set(verticeAdyacente.intValue(), new Integer(du + valorVertice)); 
					} 
				}
			}
		}
		return distancia; 
	}
	
	/**
	 * Este método devuelve un vertice minimo a partir de los vectores
	 * de distancia y aVisitar pasados por parametro.
	 * @param distancia vector con las distancias entre vertices rellenado con MAX.Value
	 * @param aVisitar vector con los nodos a visitar
	 * @return el vértice con la distancia minima
	 */
	private Integer verticeMinimo(Vector<Integer> distancia, Iterator<Integer> aVisitar){ 
		System.out.println("\n"+distancia.toString()+"\n");

		Integer vertice;
		Integer verticeMinimo = (Integer)aVisitar.next(); //
		int valorDistancia, distanciaMinima=((Integer)distancia.get(verticeMinimo.intValue())); 
		while(aVisitar.hasNext()) { 
			vertice=(Integer)aVisitar.next(); 
			valorDistancia=((Integer)distancia.get(vertice.intValue())).intValue(); 
			if(valorDistancia < distanciaMinima) { 
				verticeMinimo=vertice; 
				distanciaMinima=valorDistancia; 
			} 
		} 
		return verticeMinimo; 
	}
	
	/**
	 * Este método devuelve un lista de los elemento adyacentes de
	 * un vértice dado.
	 * @param element
	 * @return una lista de los elementos adyacentes de un elemento
	 */
	private Enumeration<Integer> adjacents(Integer element) {
	 	Vector<Integer> vertices=new Vector<Integer>();
	 	for(int i=0; i<cantidadNodos; i++){
	 		if (matrizAdyacencia[element][i] != SIN_CONEXION){
	 			vertices.add(i);
	 		}
	 	}
	 	return vertices.elements();
	}
	
	/**
	 * Este método muestra el estado de la matriz de adyacencia
	 * del grafo.
	 */
	public void mostrarMatrizAdyacencia(){
		System.out.print("	");
		for (int cont=0;cont<cantidadNodos;cont++){
			if(cont<posicionNodo){
				System.out.print((nodos[cont].getValor())+"	|	");
			}else{
				System.out.print("X | ");
			}
		}
		System.out.println();
		System.out.println("--------------------------------------------"
				+ "-----------------------------------------------------");
		for(int i=0;i<cantidadNodos;i++){
			if(i<posicionNodo){
				System.out.print((nodos[i].getValor())+":	");
			}else{
				System.out.print("X:	");
			}
			for(int j=0;j<cantidadNodos;j++){
				if (matrizAdyacencia[i][j] != SIN_CONEXION)
					System.out.print(matrizAdyacencia[i][j]+"	|	");
				else
					System.out.print("-	|	");
			}
			System.out.println("\n");
		}
	}
	
	/**
	 * Este método carga toda la matriz de adyacencia, para que no
	 * surgan problemas a la hora de trabajar con ella entre los
	 * vertices donde no exista conexión.
	 * @return una matriz con el valor numeroEntero en todas
	 * sus posiciones
	 */
	private int[][] cargarMatriz(){
		int [][] matriz= matrizAdyacencia;
		for(int i=0;i<cantidadNodos;i++){
			for(int j=0;j<cantidadNodos;j++){
				if(matriz[i][j]==0){
					matriz[i][j]=SIN_CONEXION;
				}
			}
		}
		return matriz;
	}
}
