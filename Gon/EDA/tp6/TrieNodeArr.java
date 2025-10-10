package tp6;

class TrieNodeArr {

    private TrieNodeArr[] children;
    private boolean end;

    TrieNodeArr(int size) {
        children = new TrieNodeArr[size];
        end=false;
    } 
    TrieNodeArr getValueAt(int pos) {
        if(pos>=children.length && pos<0){
            return null;
        }
        return children[pos];
    }
    void setValueAt(int pos, TrieNodeArr newNode) {
        if(pos<children.length && pos>=0){
            children[pos] = newNode;
        } else{
            System.err.println("error: el valor no pudo ser establecido");
        }
    }
    public boolean isEnd() {
        return this.end;
    }

    public boolean getEnd() {
        return this.end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }
}
