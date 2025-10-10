package tp6;

public class TrieL {
    private TrieNodeL root;
    TrieL(){
        this.root=null;
    }
    void insertWord(String word){
        if(word==null||word.isEmpty()){
            return;
        }
        word.toLowerCase();
        root=insertWord(root,word,0);
    }
    private TrieNodeL insertWord(TrieNodeL nodo, String palabra, int indice){
        if(indice==palabra.length()){
            return null;
        }
        char actual= palabra.charAt(indice);
        if(nodo==null){
            nodo=new TrieNodeL(actual);
        }
        if (nodo.getC()==actual) {
            nodo.setDown(insertWord(nodo.getDown(),palabra,indice+1));
        }else{
            nodo.setRight(insertWord(nodo.getDown(),palabra,indice+1));
        }
        return nodo;
    }
    public boolean searchWord(String palabra) {
        if (palabra == null || palabra.isEmpty()){
            return false;
        }
        return searchWord(root, palabra.toLowerCase(), 0);
    }

    private boolean searchWord(TrieNodeL nodo, String palabra, int indice) {
        if (nodo == null){
            return false;
        }
        if (indice == palabra.length()){
            return true;
        }
        char actual = palabra.charAt(indice);
        if (nodo.getC() == actual) {
            return searchWord(nodo.getDown(), palabra, indice+ 1);
        } else {
            return searchWord(nodo.getRight(), palabra, indice);
        }
    }
    void mostrarPalabras(){
        TrieNodeL aux=root;
        mostrarPalabras(aux,""); 
    } 
    void mostrarPalabras(TrieNodeL nodo,String palabra){
        if(nodo==null){
            return;
        }
        String actual=palabra+nodo.getC();
        if(nodo.getDown()==null){
            System.out.println(actual);
        }
        mostrarPalabras(nodo.getDown(),actual);
        mostrarPalabras(nodo.getRight(),actual);
    }
 
    int cantidadPalabras() {
        TrieNodeL aux =root;
        return cantidadPalabras(aux);
    }
    
    private int cantidadPalabras(TrieNodeL nodo) {
        if (nodo == null) {
            return 0;
        }
        int cantidad = (nodo.getDown() == null) ? 1 : 0;
        cantidad += cantidadPalabras(nodo.getDown());
        cantidad += cantidadPalabras(nodo.getRight());
        return cantidad;
    }
    void mostrarPrefijo(String prefijo) {
        TrieNodeL nodo = buscarNodo(root, prefijo, 0);
        if (nodo != null) {
            mostrarPalabras(nodo, prefijo);
        }
    }
    
    private TrieNodeL buscarNodo(TrieNodeL nodo, String prefijo, int indice) {
        if (nodo == null || indice == prefijo.length()) {
            return nodo;
        }
        char actual = prefijo.charAt(indice);
        if (nodo.getC() == actual) {
            return buscarNodo(nodo.getDown(), prefijo, indice + 1);
        } else {
            return buscarNodo(nodo.getRight(), prefijo, indice);
        }
    }    
    
}
