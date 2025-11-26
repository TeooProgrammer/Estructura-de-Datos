package Parcial2.Grafos;

import java.util.Vector;
import java.util.Iterator;

public class Graph {
    private Vertex[] vertices;
    private int vertexPosition;
    private int vertexQuantity;
    private boolean[][] edges; // matriz de adyacencia para determinar las aristas
    private int[][] adjacencyMatrix; // matriz para peso de las aristas
    private final int INFINITO = Integer.MAX_VALUE;

    Graph(int quantity) {
        vertexQuantity = quantity;
        vertices = new Vertex[vertexQuantity];
        vertexPosition = 0;
        edges = new boolean[vertexQuantity][vertexQuantity];
        adjacencyMatrix = new int[vertexQuantity][vertexQuantity];

        // inicializar la matriz de adyacencia con INFINITO
        for (int i = 0; i < vertexQuantity; i++) {
            for (int j = 0; j < vertexQuantity; j++) {
                adjacencyMatrix[i][j] = (i == j) ? 0 : INFINITO;
            }
        }
    }

    public void insertVertex(Object element) {
        vertices[vertexPosition] = new Vertex();
        vertices[vertexPosition].setElement(element);
        vertexPosition++;
    }

    public void insertEdge(Object originElement, Object finishElement, int cost) {
        int originPosition = getVertexOrder(originElement);
        int finishPosition = getVertexOrder(finishElement);
        edges[originPosition][finishPosition] = true;
        adjacencyMatrix[originPosition][finishPosition] = cost;
    }

    private int getVertexOrder(Object element) {
        int position = 0, order = -1;
        boolean found = false;
        while (position < vertexQuantity && found==false) {
            if (vertices[position].getElement().equals(element)) {
                found = true;
                order = position;
            }
            position++;
        }
        return order;
    }

    // Algoritmo de Dijkstra
    public Vector<Integer> dijkstraAlgorithm(Object vertex) {
        return dijkstra(getVertexOrder(vertex));
    }

    private Vector<Integer> dijkstra(int vertex) {
        Vector<Integer> distance = new Vector<>(vertexQuantity);
        Vector<Integer> toVisit = new Vector<>(vertexQuantity);
        int[] way = new int[vertexQuantity];

        for (int vs = 0; vs < vertexQuantity; vs++) {
            distance.add((vs == vertex) ? 0 : INFINITO);
            toVisit.add(vs);
            way[vs] = -1; // inicializar el arreglo de caminos con -1
        }

        while (!toVisit.isEmpty()) {
            Integer u = minimum(distance, toVisit.iterator());
            toVisit.remove(u);
            int du = ((Integer)distance.get(u.intValue())).intValue();

            if (du != INFINITO) {
                for (int w = 0; w < vertexQuantity; w++) {
                    if (edges[u][w] && toVisit.contains(w)) {
                        int cuw = adjacencyMatrix[u][w];
                        if (du + cuw < distance.get(w)) {
                            distance.set(w, du + cuw);
                            way[w] = u; // se guarda el predecesor en el camino
                        }
                    }
                }
            }
        }

        // muestra el camino desde el vértice inicial a cada vértice
        for (int i = 0; i < vertexQuantity; i++) {
            System.out.print("Camino a " + vertices[i].getElement() + ": ");
            printPath(i, way);
            System.out.println(" con distancia " + distance.get(i));
        }
        
        return distance;
    }

    private Integer minimum(Vector<Integer> distance, Iterator<Integer> toVisitI) {
        Integer vertexMinimum = (Integer)toVisitI.next();
        int distanceMinimum = ((Integer)distance.get(vertexMinimum.intValue())).intValue();

        while (toVisitI.hasNext()) {
            Integer vertex = toVisitI.next();
            int distanceValue = distance.get(vertex);
            if (distanceValue < distanceMinimum) {
                vertexMinimum = vertex;
                distanceMinimum = distanceValue;
            }
        }
        return vertexMinimum;
    }

    // método para imprimir el camino usando el arreglo de predecesores
    private void printPath(int vertex, int[] way) {
        if (way[vertex] == -1) {
            System.out.print(vertices[vertex].getElement() + " ");
        } else {
            printPath(way[vertex], way);
            System.out.print(vertices[vertex].getElement() + " ");
        }
    }


}
