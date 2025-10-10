package ejercicio4;

public class TrieNode {
	private char value;
	private TrieNode next;
	private LinkedList down;
	
	public TrieNode() {
		this.value = '/';
		this.next = null;
		this.down = new LinkedList();
	}
	
	public TrieNode(char value) {
		this.value = value;
		this.next = null;
		this.down = new LinkedList();
	}
	
	public void setNext(TrieNode n) {
		this.next = n;
	}
	
	public void setChar(char c) {
		this.down.add(c);
	}
	
	public char getValue() {
		return this.value;
	}
	
	public TrieNode getNext() {
		return this.next;
	}
	
	public TrieNode getChar(char c) {
		return this.down.getTrieNode(c);
	}
	
	public void remove(char c) {
		this.down.remove(c);
	}
	
	public boolean isEmpty() {
		return this.down.isEmpty();
	}
}
