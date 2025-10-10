package ejercicio4;
public class Trie {
	TrieNode root;
	
	public Trie() {
		root = null;
	}
	
	public void insert(String s) {
		TrieNode p;
		char c;
		s += '@';
		if(root == null) {
			root = new TrieNode();
		}
		p = root;
		for(int i = 0; i<s.length();i++) {
			c = s.charAt(i);
			if(p.getChar(c)==null) {
				p.setChar(c);
			}
			p=p.getChar(c);
		}
	}
	
	public void remove(String s) {
		removeAux(root,s+'@',0);
	}
	
	private void removeAux(TrieNode p, String s, int i) {
		char c = s.charAt(i);
		if(p!=null) {
			if(c=='@') {
				p.remove('@');
			}
			else {
				removeAux(p.getChar(c),s,i+1);
				if(p.getChar(c)!=null && p.getChar(c).isEmpty()) {
					p.remove(c);
				}
			}
		}
	}
	
	public boolean search(String s) {
		boolean found = true;
		char c;
		int i = 0;
		TrieNode p = this.root;
		while(found && i<s.length()) {
			c = s.charAt(i);
			if(p==null)
				found = false;
			else
				p = p.getChar(c);
			i++;
		}
		if(p!=null && p.getChar('@')==null) {
			found = false;
		}
		return found;
	}
	
	
	//---------------------------------------------------------------------------------------------------------
	public int cantPalabras() {
		int cont = 0;
		cont = Palabras(root);
		return cont;
	} 
	
	private int Palabras(TrieNode x) {
		int cant = 0;
		if(x!=null) {
			if(x.getChar('@')!=null) {
				cant++;
			}
			for(char i = 'a'; i<='z';i++) {
				cant += Palabras(x.getChar(i));
			}
		}
		return cant;
	}
	
	//----------------------------------------------------------------------------------------------------------------
	
	
	//--------------------------------------------------------------------------------------------------------
	public void listarPalabras() {
		listarPalabras(root,"");
	}
	
	private void listarPalabras(TrieNode x, String s) {
		if(x!=null) {
			if(x.getChar('@')!=null) {
				System.out.println(s);
			}
			for(char i = 'a'; i<='z';i++) {
				listarPalabras(x.getChar(i),s+i);
			}
		}
	}
	//----------------------------------------------------------------------------------------------------------------
	
	
	//---------------------------------------------------------------------------------------------------------
	public void listarPrefijo(String prefijo) {
		int i = 0;
		char c;
		TrieNode p = root;
		while(i<prefijo.length() && p!=null) {
			c = prefijo.charAt(i);
			p = p.getChar(c);
			i++;
		}
		listarPalabras(p,prefijo);
	}
	//----------------------------------------------------------------------------------------------------------------
	
	
	
	
	
	//----------------------------------------------------------------------------------------------------------
	public int cantPrefijos() {
		return cantPrefijos(root);
	}
	
	private int cantPrefijos(TrieNode t) {
		int cant = 0;
		int aux = 0;
		TrieNode p;
		if(t!=null) {
		for(char i = 'a'; i<='z';i++) {
				p = t.getChar(i);
				if(p!=null) {
					aux++;
					cant += cantPrefijos(p);
				}
			}
			if(aux>1 && t!=root) {
				cant++;
			}
		}
		return cant;
	}
	//----------------------------------------------------------------------------------------------------------------
	
	
	
	
	
}
