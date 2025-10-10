
import java.util.Vector;
public class Main {
    public static void main(String[] args) {
        
    
    Graph grafo = new Graph(8);
    grafo.insertVertex('A');
    grafo.insertVertex('B');
    grafo.insertVertex('C');
    grafo.insertVertex('D');
    grafo.insertVertex('E');
    grafo.insertVertex('F');
    grafo.insertVertex('G');
    grafo.insertVertex('H');
  
    grafo.insertEdge2('A', 'B', 1);
    grafo.insertEdge2('A', 'D', 7);
    grafo.insertEdge2('A', 'E', 9);
    grafo.insertEdge2('A', 'H', 5);
    grafo.insertEdge2('B', 'D', 2);
    grafo.insertEdge2('B', 'E', 4);
    grafo.insertEdge2('B', 'C', 1);
    grafo.insertEdge2('B', 'D', 2);
    grafo.insertEdge2('C', 'D', 3);
    grafo.insertEdge2('D', 'E', 3);
    grafo.insertEdge2('E', 'F', 6);
    grafo.insertEdge2('E', 'G', 2);
    grafo.insertEdge2('E', 'H', 1);
    grafo.insertEdge2('F', 'H', 3);
    grafo.insertEdge2('H', 'G', 2);
 //En ambos sentidos ya que es un multigrafo
    grafo.insertEdge2('B', 'A', 1);
    grafo.insertEdge2('D', 'A', 7);
    grafo.insertEdge2('E', 'A', 9);
    grafo.insertEdge2('H', 'A', 5);
    grafo.insertEdge2('D', 'B', 2);
    grafo.insertEdge2('E', 'B', 4);
    grafo.insertEdge2('C', 'B', 1);
    grafo.insertEdge2('D', 'B', 2);
    grafo.insertEdge2('D', 'C', 3);
    grafo.insertEdge2('E', 'D', 3);
    grafo.insertEdge2('F', 'E', 6);
    grafo.insertEdge2('G', 'E', 2);
    grafo.insertEdge2('H', 'E', 1);
    grafo.insertEdge2('H', 'F', 3);
    grafo.insertEdge2('G', 'H', 2);

    
    

    Vector <Integer> distanciaAll = grafo.dijkstraAlgorithm('A');
    for(int i = 0; i<distanciaAll.size();i++)
        System.out.println("Distancia de A hasta "+grafo.obtenerVertice(i)+" = "+ distanciaAll.elementAt(i) );/* */
    
    Vector <Integer> distancia = grafo.rutaMinima('A', 'G');
    System.out.println("Ruta Minima");
    for(int i = 0; i<distancia.size();i++)
        System.out.print(grafo.obtenerVertice(distancia.elementAt(i))+" -> " );/* */

    System.out.println("Distancia entre A y G");
    System.out.println(grafo.minimaEntrePares('A', 'G'));

    }
     
}
