package tp6;
class TrieArr {
    private TrieNodeArr root;
    private int end = 26;

    TrieArr() {
        root = null;
    }

    void insertWord(String word) {
        TrieNodeArr aux;
        int pos, i=0;
        word = word.toLowerCase();
        if (root == null) {
            root = new TrieNodeArr(end+1);
        }
        aux = root;
        while (i < word.length()) {
            pos = getPosition(word.charAt(i));
            if(pos==-1||pos>end){
                break;
            } else{
                TrieNodeArr next=aux.getValueAt(pos);
                if (next == null) {
                    next=new TrieNodeArr(end+1);
                    aux.setValueAt(pos, next);
                }
                aux=next;
                i++;
            }
        }
        aux.setEnd(true);
    }

    boolean searchWord(String word) {
        word = word.toLowerCase();
        TrieNodeArr aux = root;
        int i = 0;
        int pos;
        if (aux == null) {
            return false;
        }
        while (i < word.length()) {
            pos = getPosition(word.charAt(i));
            if(pos==-1 || pos>end){
                break;
            } else{
                TrieNodeArr next= aux.getValueAt(pos);
                if (next!=null){
                    aux=next;
                    i++;
                }else{
                    return false;
                }
            }
        }
        return (aux != null && aux.isEnd());
    }

    static int getPosition(char c) {
        return c-'a';
    }
    static char getLetra(int c) {
        return (char) ('a'+c);
    }

    int cantidadPalabras(){
        TrieNodeArr aux = root;
        return cantidadPalabras(aux, 1);
    }
    private int cantidadPalabras(TrieNodeArr n, int indice){
        if(n==null || indice>= 28){
            return 0;
        }
        int cantidad = (n.getValueAt(0)!=null)?1:0;
        cantidad+=cantidadPalabras(n.getValueAt(indice), indice+1);
        return cantidad;
    } 
    void mostrarPalabra(){
        TrieNodeArr aux=root;
        mostrarPalabra(aux,"");
    }
    void mostrarPalabra(TrieNodeArr nodo, String palabra){
        if(nodo==null){
            return;
        }
        for (int i = 0; i < end; i++) {
            mostrarPalabra(nodo.getValueAt(i), palabra+recuperarCaracter(i));
        }
        if(nodo.getValueAt(0)!=null){
            System.out.println(palabra);
        }
    }
    void mostrarPrefijo(String pref){
        TrieNodeArr aux=root;
        int i=0,pos=-1;
        while (i<pref.length()){
            pos=getPosition(pref.charAt(i));
            aux=aux.getValueAt(pos);
            i++;
        }
        mostrarPalabra(aux, pref);
    }
    char recuperarCaracter(int val){
		char c='a';
		
		for (int i = 1; i < val; i++) {
			c++;
		}
		
		return c;
	}
}
