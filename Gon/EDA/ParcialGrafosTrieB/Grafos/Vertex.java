package ParcialGrafosTrieB.Grafos;
public class Vertex {
    private Object element;
    private Edge edge;
    Vertex() {
        element = null;
        edge = null;
    }
    public Object getElement() {
        return element;
    }
    public Edge getEdge() {
        return edge;
    }
    public void setElement(Object element) {
        this.element = element;
    }
    public void setEdge(Edge edge) {
        this.edge = edge;
    }
}
