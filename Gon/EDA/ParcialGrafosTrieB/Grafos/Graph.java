package ParcialGrafosTrieB.Grafos;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;
import java.util.Iterator;

public class Graph {
    private Vertex[] vertices;
    private int vertexPosition;
    private boolean[][] edges;
    private int[][] costs;
    private int vertexQuantity;
    private static final int INFINITO = Integer.MAX_VALUE;

    Graph(int quantity) {
        vertexQuantity = quantity;
        vertices = new Vertex[vertexQuantity];
        vertexPosition = 0;
        edges = new boolean[vertexQuantity][vertexQuantity];
        costs = new int[vertexQuantity][vertexQuantity];

        for (int i = 0; i < vertexQuantity; i++) {
            for (int j = 0; j < vertexQuantity; j++) {
                if (i == j) {
                    costs[i][j] = 0;
                } else {
                    costs[i][j] = INFINITO;
                }
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
        costs[originPosition][finishPosition] = cost;
    }

    private int getVertexOrder(Object element) {
        int position = 0, order = -1;
        boolean found = false;
        while (position < vertexQuantity & found == false) {
            if (vertices[position].getElement().equals(element)) {
                found = true;
                order = position;
            }
            position++;
        }
        return order;
    }
    public void depthFirstSearch(Object element){
        Vector visited = new Vector(vertexQuantity);
        depthFirst(getVertexOrder(element), visited);
    }

    private void depthFirst(int element, Vector visited){
        System.out.print(vertices[element].getElement() + " ");
        visited.addElement(new Integer(element));
        Enumeration adjs = adjacents(new Integer(element));
        while (adjs.hasMoreElements()) {
            Integer adjsOther = (Integer) adjs.nextElement();
            if (!visited.contains(adjsOther)) {
                depthFirst(adjsOther.intValue(), visited);
            }
        }
    }

    public void breadhFirstSearch(Object element) {
        breadhFirst(getVertexOrder(element));
    }
    private void breadhFirst(int element) {
        Vector<Integer> visited = new Vector(vertexQuantity);
        Queue<Integer> explore = new LinkedList<>();
        explore.add(new Integer(element));
        visited.addElement(new Integer(element));
        do {
            Integer vertexOther = (Integer) explore.poll();
            System.out.print(vertices[vertexOther.intValue()].getElement() + " ");
            Enumeration adjs = adjacents(vertexOther);
            while (adjs.hasMoreElements()) {
                Integer adjsOther = (Integer) adjs.nextElement();
                if (!visited.contains(adjsOther)) {
                    explore.add(adjsOther);
                    visited.addElement(adjsOther);
                }
            }
        } while (!explore.isEmpty());
    }

    public ArrayList<Integer> dijkstraAlgorithm(Object vertex) {
        return dijkstra(getVertexOrder(vertex));
    }

    private ArrayList<Integer> dijkstra(int vertex) {
        int vs;
        ArrayList<Integer> distance = new ArrayList<>(vertexQuantity);
        ArrayList<Integer> toVisit = new ArrayList<>(vertexQuantity);

        for (vs = 0; vs < vertexQuantity; vs++) {
            if (vs == vertex) {
                distance.add(0); 
            } else {
                distance.add(INFINITO);
            }
            toVisit.add(vs);
        }

        while (!toVisit.isEmpty()) {
            Integer u = minimum(distance, toVisit.iterator());
            toVisit.remove(u);
            int du = distance.get(u);
            
            if (du != INFINITO) {
                Enumeration<Integer> adjs = adjacents(u);
                while (adjs.hasMoreElements()) {
                    Integer w = adjs.nextElement();
                    if (toVisit.contains(w)) {
                        int cuw = costs[u][w];
                        if (du + cuw < distance.get(w)) {
                            distance.set(w, du + cuw);
                        }
                    }
                }
            }
        }
        return distance;
    }

    private Integer minimum(ArrayList<Integer> distance, Iterator<Integer> toVisitI) {
        Integer vertexMinimum = toVisitI.next();
        int distanceMinimum = distance.get(vertexMinimum);

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

    private Enumeration<Integer> adjacents(Integer element) {
        Vector<Integer> adjVertices = new Vector<>();
        for (int i = 0; i < vertexQuantity; i++) {
            if (edges[element][i]) {
                adjVertices.add(i);
            }
        }
        return adjVertices.elements();
    }

    // floyd con matriz P
    public int[][] floyd() {
        int[][] floydMat = new int[vertexQuantity][vertexQuantity];
        int[][] P = new int[vertexQuantity][vertexQuantity];
        
        for (int i = 0; i < vertexQuantity; i++) {
            for (int j = 0; j < vertexQuantity; j++) {
                floydMat[i][j] = costs[i][j];
                if (i != j && costs[i][j] < INFINITO) {
                    P[i][j] = i;
                } else {
                    P[i][j] = -1;
                }
            }
        }
        for (int k = 0; k < vertexQuantity; k++){
            for (int i = 0; i < vertexQuantity; i++){
                for (int j = 0; j < vertexQuantity; j++){
                    if (floydMat[i][k] != INFINITO && floydMat[k][j] != INFINITO &&
                        floydMat[i][j] > floydMat[i][k] + floydMat[k][j]) {
                        
                        floydMat[i][j] = floydMat[i][k] + floydMat[k][j];
                        P[i][j] = P[k][j];
                    }
                }
            }
        }

        // matriz de costos y predecesores
        System.out.println("Matriz de costos de Floyd:");
        System.out.println("-  A\tB\tC\tD\tE\tF\tG");
        mostrarMatriz(floydMat);
        System.out.println("Matriz de predecesores P:");
        System.out.println("-  A\tB\tC\tD\tE\tF\tG");
        mostrarMatriz(P);

        return floydMat;
    }

    private void mostrarMatriz(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            System.out.print(vertices[i].getElement() + " ");
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print((matrix[i][j] == INFINITO ? "INF" : matrix[i][j]) + "\t");
            }
            System.out.println();
        }
    }
}