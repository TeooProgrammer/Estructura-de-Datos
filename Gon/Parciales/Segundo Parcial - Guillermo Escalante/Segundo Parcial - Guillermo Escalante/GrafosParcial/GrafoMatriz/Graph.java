import java.util.Enumeration;
import java.util.HashSet;
import java.util.Vector;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Graph
{
    private Vertex[] vertices;
    private int vertexPosition;
    private int[][] edges;
    private int vertexQuantity;
    private int inf = Integer.MAX_VALUE;
    private int [][] matriz_recorrido;

    Graph(int quantity)
        {
        vertexQuantity=quantity;
        vertices=new Vertex[vertexQuantity];
        vertexPosition=0;
        edges=new int[vertexQuantity][vertexQuantity];
        edges = cargarMatriz();
        matriz_recorrido = new int[vertexQuantity][vertexQuantity];
        for(int i = 0; i<quantity;i++) {
            for(int j = 0; j<quantity; j++) {
            matriz_recorrido[i][j] = j+1;
            }
            }
        }

    public void insertVertex(Object element)
        {
        vertices[vertexPosition]=new Vertex();
        vertices[vertexPosition].setElement(element);
        vertexPosition++;
        }

    public Object obtenerVertice(int index){
        return vertices[index].getElement();
    }

    public void insertEdge2(Object originElement, Object finishElement, int weight)
        {
        int originPosition=getVertexOrder(originElement);
        int finishPosition=getVertexOrder(finishElement);
        if (originPosition != -1 && finishPosition != -1) { // Se verifica que existan nodos en las posiciones recibidas.
            edges[originPosition][finishPosition]=weight;
		}
    }

    public int getVertexOrder(Object element)
        {
        int position=0, order=-1;
        boolean found=false;
        while(position<vertexQuantity && found==false)
            {
            if(vertices[position].getElement().equals(element)) 
                {
                found=true;
                order=position;
                }
            position++;
            }
        return order;
    }

    private Enumeration adjacents(Integer element) //Funciona creo
        {
        Vector <Object> vertices1=new Vector<Object>();
            for(int i = 0; i<edges.length; i++){
                if(edges[element][i] != Integer.MAX_VALUE)
                    vertices1.addElement(i);
        }
        return vertices1.elements();
    }
    
    public void Matriz(){
        for(int i = 0; i < edges.length; i++){
            for(int j = 0; j < edges.length; j++ ){
                System.out.print("["+edges[i][j]+"] ");
            } 
            System.out.println();
        }
    }


    private int[][] cargarMatriz(){
        int [][] matriz= edges;
        for(int i=0;i<vertexQuantity;i++){
            for(int j=0;j<vertexQuantity;j++){
                if(matriz[i][j]==0){
                    matriz[i][j]=inf;
                }
            }
        }
        return matriz;
    }

    
    public Vector<Integer> dijkstraAlgorithm(Object vertex) {
        return dijkstra(getVertexOrder(vertex));
    }
    
    private Vector<Integer> dijkstra(int vertice) {
        int duv, u, du;
		Vector<Integer> distance = new Vector<Integer>(vertexQuantity);
		Vector<Integer> toVisit = new Vector(vertexQuantity);
		
		for(int vs = 0; vs<vertexQuantity; vs++) {
            if(vs == vertice) 
            duv = 0;
            else 
            duv = Integer.MAX_VALUE;
            
            distance.insertElementAt(duv, vs);
            toVisit.addElement(vs);
		}
		
		while(!toVisit.isEmpty()) {
			u = minimum(distance, toVisit.iterator());
			toVisit.removeElement(u);
			du = distance.get(u);
			if(du!=inf) {
				Enumeration<Integer> adj = adjacents(u);
				while(adj.hasMoreElements()) {
					int w = adj.nextElement();
					if(toVisit.contains(w)) {
						int cuw = edges[u][w];
						if(du+cuw<distance.get(w)) {
							distance.set(w, du+cuw);
						}
					}
				}
			}
		}
		
		return distance;
	}



    public Vector<Integer> rutaMinima(Object vertexStart, Object vertexEnd) { //Ruta Minima
        return dijkstraRut(getVertexOrder(vertexStart), getVertexOrder(vertexEnd) );
    }

    private Vector<Integer> dijkstraRut(int verticeStart, int vertexEnd) {
        int [] predecesor = new int[vertexQuantity];
		int duv, u, du;
		Vector<Integer> distance = new Vector<Integer>(vertexQuantity);
		Vector<Integer> toVisit = new Vector(vertexQuantity);
		
		for(int vs = 0; vs<vertexQuantity; vs++) {
			if(vs == verticeStart) 
					duv = 0;
              else 
					duv = Integer.MAX_VALUE;

				distance.insertElementAt(duv, vs);
				toVisit.addElement(vs);
                predecesor[vs] = -1;
		}
		
		while(!toVisit.isEmpty()) {
			u = minimum(distance, toVisit.iterator());
			toVisit.removeElement(u);
			du = distance.get(u);
			if(du!=inf) {
				Enumeration<Integer> adj = adjacents(u);
				while(adj.hasMoreElements()) {
					int w = adj.nextElement();
					if(toVisit.contains(w)) {
						int cuw = edges[u][w];
						if(du+cuw<distance.get(w)) {
							distance.set(w, du+cuw);
                            predecesor[w] = u;
						}
					}
				}
			}
		}
        
        Vector <Integer> rutaMinima = new Vector<>();
        int actual = vertexEnd;
        while (actual != -1) {
            rutaMinima.insertElementAt(actual, 0);
            actual = predecesor[actual];
        }
		return rutaMinima;
	}

public int minimaEntrePares(Object vertexStart, Object vertexEnd) { //Distancia minima
        return dijkstraMin(getVertexOrder(vertexStart), getVertexOrder(vertexEnd) );
    }

    private int dijkstraMin(int verticeStart, int vertexEnd) {
        int [] predecesor = new int[vertexQuantity];
		int duv, u, du;
		Vector<Integer> distance = new Vector<Integer>(vertexQuantity);
		Vector<Integer> toVisit = new Vector(vertexQuantity);
		
		for(int vs = 0; vs<vertexQuantity; vs++) {
			if(vs == verticeStart) 
					duv = 0;
              else 
					duv = Integer.MAX_VALUE;

				distance.insertElementAt(duv, vs);
				toVisit.addElement(vs);
                predecesor[vs] = -1;
		}
		
		while(!toVisit.isEmpty()) {
			u = minimum(distance, toVisit.iterator());
			toVisit.removeElement(u);

            if(u == vertexEnd){
                return distance.get(vertexEnd);
            }
			du = distance.get(u);
			if(du!=inf) {
				Enumeration<Integer> adj = adjacents(u);
				while(adj.hasMoreElements()) {
					int w = adj.nextElement();
					if(toVisit.contains(w)) {
						int cuw = edges[u][w];
						if(du+cuw<distance.get(w)) {
							distance.set(w, du+cuw);
                            predecesor[w] = u;
						}
					}
				}
			}
		}
        
		return -1 ;
	}

    private int minimum(Vector<Integer> distance, Iterator<Integer> toVisit) { 
		int vertex, vertexMinimum = toVisit.next();
		int distanceValue, distanceMinimum = distance.get(vertexMinimum);
		
		while(toVisit.hasNext()) {
			vertex = toVisit.next();
			distanceValue = distance.get(vertex);
			if(distanceValue < distanceMinimum) {
				vertexMinimum = vertex;
				distanceMinimum = distanceValue;
			}
		}
		return vertexMinimum;
	}


    

}