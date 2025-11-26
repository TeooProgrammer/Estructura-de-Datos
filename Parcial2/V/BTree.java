package Parcial2.V;

import java.util.*;

class BTree {
    BNode root;
    int arity;

    BTree(int arity) {
        this.arity = arity;
        root = null;
    }

    void addKey(Object key) {
        if(root==null){
            BNode newNode=new BNode(arity + 1);
            Keys keys=new Keys(arity);
            int aux=keys.size;
            keys.addKey(key);
            newNode.setData(keys);
            root=newNode;
            return;
            }
        //find a leaf to insert key. The leaf will be referenced by currentNode
        BNode currentNode=root;
        Keys currentKeys=root.getData();
        int index;
        Stack nodes=new Stack();
        while(currentNode.degree()>0){
            index=currentKeys.search(key);
            if(index==-1)
                return;
            nodes.push(currentNode);
            currentNode=currentNode.getChild(index);
            currentKeys=currentNode.getData();
        }
        index=currentKeys.search(key);
        if(index==-1)
        return;
        //insert key into currentNode
        int aux=currentKeys.size;
        currentKeys.addKey(key);
        //if a node contains arity number of keys, split the node to two nodes.
        //Repeat the step for its parent and other ancestors if necessary
        while (currentKeys.size==arity){
            int mid=(arity + 1) / 2 - 1;
            key=currentKeys.keyAt(mid);
            currentKeys.deleteKey(key);
            BNode newNode=new BNode(arity + 1);
            Keys newKeys=new Keys(arity);
            newNode.setData(newKeys);
            //move keys from currentNode to newNode
            for(int i=0; i<(arity + 1) / 2 - 1; i++){
                Object tempKey=currentKeys.keyAt(0);
                newKeys.addKey(tempKey);
                currentKeys.deleteKey(tempKey);
            }
            //move children
            if(currentNode.degree()>0)
                for(int i=0; i<=(arity + 1) / 2 - 1; i++){
                    BNode child=currentNode.getChild(0);
                    newNode.addChild(child,i);
                    currentNode.deleteChild(0);
                }
            if(currentNode==root){
                root=new BNode(arity + 1);
                Keys tempKeys=new Keys(arity);
                tempKeys.addKey(key);
                root.setData(tempKeys);
                root.addChild(newNode,0);
                root.addChild(currentNode,1);
                return;
            }
            //add key and newNode into parent
            currentNode=(BNode)nodes.peek();
            nodes.pop();
            currentKeys=currentNode.getData();
            index=currentKeys.addKey(key);
            currentNode.addChild(newNode,index);
        }
    }

    boolean searchKey(Object key) {
        if(root==null)
            return false;
        BNode currentNode=root;
        Keys currentKeys=root.getData();
        int index=currentKeys.search(key);
        while(currentNode.degree()>0){
            if(index==-1)
                return true;
            currentNode=currentNode.getChild(index);
            currentKeys=currentNode.getData();
            index=currentKeys.search(key);
        }
        if(index!=-1)
            return false;
        else
            return true;
    }

    public boolean deleteKey(Object key) {
        if (root == null) {
            System.out.println("Error: El árbol está vacío.");
            return false;
        }

        BNode currentNode = root;
        Keys currentKeys = root.getData();
        int index = currentKeys.search(key);
        Stack<BNode> nodes = new Stack<>();

        while (currentNode.degree() > 0) {
            if (index == -1) return true;
            nodes.push(currentNode);
            currentNode = currentNode.getChild(index);
            if (currentNode == null) {
                System.out.println("Error: Nodo hijo es nulo durante la búsqueda.");
                return false;
            }
            currentKeys = currentNode.getData();
            index = currentKeys.search(key);
        }

        if (index == -1 && currentNode.degree() > 0) {
            currentKeys.deleteKey(key);
            index = currentKeys.search(key);
            nodes.push(currentNode);
            Keys tempKeys;
            if (index < currentNode.degree() - 1) {
                currentNode = currentNode.getChild(index + 1);
                while (currentNode.degree() > 0) {
                    nodes.push(currentNode);
                    currentNode = currentNode.getChild(0);
                }
                tempKeys = currentNode.getData();
                key = tempKeys.keyAt(0);
            } else {
                currentNode = currentNode.getChild(index);
                while (currentNode.degree() > 0) {
                    nodes.push(currentNode);
                    currentNode = currentNode.getChild(currentNode.degree() - 1);
                }
                tempKeys = currentNode.getData();
                key = tempKeys.keyAt(tempKeys.size - 1);
            }
            currentKeys.addKey(key);
            tempKeys.deleteKey(key);
            currentKeys = tempKeys;
        } else {
            if (index == -1) currentKeys.deleteKey(key);
            else return false;
        }

        while (currentNode != root && currentKeys.size < (arity + 1) / 2 - 1) {
            BNode parent = nodes.isEmpty() ? null : nodes.pop();
            if (parent == null) {
                System.out.println("Error: No se encontró el nodo padre.");
                return false;
            }

            Keys parentKeys = parent.getData();
            for (int i = 0; i < parent.degree(); i++) {
                if (currentNode == parent.getChild(i)) {
                    index = i;
                    break;
                }
            }

            if (index > 0) {
                BNode leftSibling = parent.getChild(index - 1);
                if (leftSibling == null) {
                    System.out.println("Error: Hermano izquierdo es nulo.");
                    return false;
                }
                Keys leftKeys = leftSibling.getData();
                Object tempKey = parentKeys.keyAt(index - 1);
                parentKeys.deleteKey(tempKey);

                if (leftKeys.size >= (arity + 1) / 2) {
                    Object movedKey = leftKeys.keyAt(leftKeys.size - 1);
                    leftKeys.deleteKey(movedKey);
                    parentKeys.addKey(movedKey);
                    currentKeys.addKey(tempKey);
                    return true;
                }
            }
            else {
                BNode rightSibling = parent.getChild(index + 1);
                if (rightSibling == null) {
                    System.out.println("Error: Hermano derecho es nulo.");
                    return false;
                }
                Keys rightKeys = rightSibling.getData();
                Object tempKey = parentKeys.keyAt(index);
                parentKeys.deleteKey(tempKey);

                if (rightKeys.size >= (arity + 1) / 2) {
                    Object movedKey = rightKeys.keyAt(0);
                    rightKeys.deleteKey(movedKey);
                    parentKeys.addKey(movedKey);
                    currentKeys.addKey(tempKey);
                    return true;
                }
            }
        }

        if (currentNode == root) {
            if (root.getData().size == 0) {
                if (root.degree() > 0) root = root.getChild(0);
                else root = null;
            }
        }
        return true;
    }

    public void printTree(BNode node, String prefix) {
        if (node == null) {
            return;
        }
        System.out.println(prefix + node.getData());
        for (int i = 0; i < node.degree(); i++) {
            printTree(node.getChild(i), prefix + "   ");
        }
    }
}

