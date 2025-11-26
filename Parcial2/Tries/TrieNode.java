package Parcial2.Tries;

class TrieNode {
    private TrieNode[] chars;

    public TrieNode(int size) {
        chars = new TrieNode[size + 1]; // +1 para marcar el fin de palabra
    }

    public TrieNode getValueAt(int pos) {
        return chars[pos];
    }

    public void setValueAt(int pos, TrieNode node) {
        chars[pos] = node;
    }
}

