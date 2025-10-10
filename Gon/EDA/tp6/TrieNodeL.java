package tp6;

public class TrieNodeL {
    private char c;
    private TrieNodeL right;
    private TrieNodeL down;
    TrieNodeL(char car){
        this.c=car;
        this.right=null;
        this.down=null;
    } 
    public char getC() {
        return this.c;
    }
    public void setC(char c) {
        this.c = c;
    }
    public TrieNodeL getRight() {
        return this.right;
    }
    public void setRight(TrieNodeL right) {
        this.right = right;
    }
    public TrieNodeL getDown() {
        return this.down;
    }
    public void setDown(TrieNodeL down) {
        this.down = down;
    }
}
