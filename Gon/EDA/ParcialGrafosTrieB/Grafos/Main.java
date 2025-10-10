package ParcialGrafosTrieB.Grafos;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph(7);

        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");
        graph.insertVertex("E");
        graph.insertVertex("F");
        graph.insertVertex("G");

        graph.insertEdge("A", "B", 7);
        graph.insertEdge("A", "C", 6);
        graph.insertEdge("A", "D", 8);
        graph.insertEdge("B", "E", 2);
        graph.insertEdge("C", "E", 7);
        graph.insertEdge("D", "E", 3);
        graph.insertEdge("D", "F", 4);
        graph.insertEdge("E", "F", 3);
        graph.insertEdge("E", "G", 1);
        graph.insertEdge("F", "E", 3);
        graph.insertEdge("F", "G", 1);

        System.out.println("Dijkstra desde A:");
        System.out.println(graph.dijkstraAlgorithm("A"));

        System.out.println();
        System.out.println("Floyd:");
        graph.floyd();
    }
}