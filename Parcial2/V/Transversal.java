package Parcial2.V;

import java.util.*;

class Transversal implements Enumeration {
    private Vector nodes;

    public Transversal(BTree AB) {
        nodes = new Vector();
        if (AB.root != null) nodes.addElement(AB.root);
    }

    public boolean hasMoreElements() {
        return (nodes.size() != 0);
    }

    public Object nextElement() {
        BNode tempNode = (BNode) nodes.elementAt(0);
        nodes.removeElementAt(0);
        for (int i = 0; i < tempNode.degree(); i++) nodes.addElement(tempNode.getChild(i));
        return tempNode;
    }
}

