package Parcial2.Grafos;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph(7);

        // insertar los vértices
        graph.insertVertex(1);
        graph.insertVertex(2);
        graph.insertVertex(3);
        graph.insertVertex(4);
        graph.insertVertex(5);
        graph.insertVertex(6); 
        graph.insertVertex(7); 


        graph.insertEdge(1, 6, 3);
        graph.insertEdge(1, 7, 1);
    

        System.out.println("Algoritmo de Dijkstra (desde el vértice 1):");
        graph.dijkstraAlgorithm(1);

    }
}

