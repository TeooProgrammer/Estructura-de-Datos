package Parcial2.B;

public class BNode {
    Keys data;
    BNode[] children;
    int arity, size;

    BNode(int arity) {
        this.arity = arity;
        size = 0;
        children = new BNode[arity];
    }

    BNode(int arity, Keys data) {
        this.arity = arity;
        size = 0;
        children = new BNode[arity];
        this.data = data;
    }

    void setData(Keys data) {
        this.data = data;
    }

    Keys getData() {
        return data;
    }

    BNode getChild(int index) {
        if (index >= size || index < 0) return null;
        return children[index];
    }

    BNode addChild(BNode data) {
        if (size == arity) return null;
        Keys temp = data.getData();
        BNode tempNode = new BNode(arity, temp);
        children[size++] = tempNode;
        return tempNode;
    }

    BNode addChild(BNode data, int index) {
        Keys temp = data.getData();
        if (index < 0 || index >= size) return addChild(data);
        for (int i = size; i > index; i--) children[i] = children[i - 1];
        BNode tempNode = new BNode(arity, temp);
        children[index] = tempNode;
        size++;
        return tempNode;
    }

    BNode deleteChild(int index) {
        if (index < 0 || index >= size) return null;
        BNode tempNode = children[index];
        for (int i = index; i < size - 1; i++) children[i] = children[i + 1];
        size--;
        return tempNode;
    }

    void removeChild(BNode data) {
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                if (children[i] == data) deleteChild(i);
            }
        }
    }

    int degree() {
        return size;
    }
}

