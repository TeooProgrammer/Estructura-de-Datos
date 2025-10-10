package tp7;
import java.util.*; 
class Transversal implements Enumeration { 
	private Vector nodes;
	public Transversal(ArbolB AB) { 
		nodes=new Vector();
		if(AB.raiz!=null){
			nodes.addElement(AB.raiz);
		}
		} 
	public boolean hasMoreElements() { 
		return (nodes.size()!=0); 
	} 
	public Object nextElement() {
		NodoB tempNode=(NodoB)nodes.elementAt(0);
		nodes.removeElementAt(0);
		for(int i=0; i<tempNode.grado(); i++){
			nodes.addElement(tempNode.obtenerHijo(i));
		} 
		return tempNode;
	} 
}