package ParcialGrafosTrieB.Tries;

class TrieNode {
    TrieNode[] chars;
    TrieNode(int end) {
        chars = new TrieNode[end + 1];
    }
    TrieNode getValueAt(int pos) {
        if(pos>=chars.length || pos<0){
            return null;
        } 
        return chars[pos];
    }
    void setValueAt(int pos, TrieNode newNode) {
        if(pos<chars.length && pos>=0){
            chars[pos] = newNode;
        } else{
            System.err.println("error: el valor no pudo ser establecido");
        }
    }
}
