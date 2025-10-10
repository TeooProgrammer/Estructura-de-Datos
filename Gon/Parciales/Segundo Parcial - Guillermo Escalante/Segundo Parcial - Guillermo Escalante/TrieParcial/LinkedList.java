package ejercicio4;

public class LinkedList {
	private TrieNode list;
	
	public LinkedList() {
		list = null;
	}
	
	public void add(char c) {
		TrieNode n = new TrieNode(c);
		TrieNode p = list;
		if(list == null) {
			list = n;
		}
		else {
			if((c=='@'&& list.getValue()!='@')||(c<list.getValue())) {
				n.setNext(list);
				list = n;
			}
			else {
				while(p.getNext()!=null && c>=p.getNext().getValue()) {
					p = p.getNext();
				}
				if(c!=p.getValue()) {
					n.setNext(p.getNext());
					p.setNext(n);
				}
			}
		}
	}
	
	public void remove(char c) {
		TrieNode p = list;
		if(p!=null) {
			if(p.getValue()==c) {
				list = p.getNext();
			}
			else {
				while(p.getNext()!=null && p.getNext().getValue()!=c) {
					p = p.getNext();
				}
				if(p.getNext()!=null) {
					p.setNext(p.getNext().getNext());
				}
			}
		}
	}
	
	public boolean isEmpty() {
		return list == null;
	}
	
	public TrieNode getTrieNode(char c) {
		TrieNode p = list;
		while(p!=null&&p.getValue()!=c) {
			p = p.getNext();
		}
		return p;
	}
	
	public String toString() {
		String s = "";
		TrieNode p = list;
		while(p!=null) {
			s+=p.getValue()+" ";
			p = p.getNext();
		}
		return s;
	}
}
