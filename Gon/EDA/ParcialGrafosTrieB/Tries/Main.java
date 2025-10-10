package ParcialGrafosTrieB.Tries;
public class Main {
    public static void main(String[] args) {
        String[] words = {"algorithm","animation","ant","apache","append","apple","application","approach"};
        //--------MATRIZ--------
        Trie trie = new Trie();
        // insertar palabras
        for (String word : words) {
            trie.insertWord(word);
        } 
        // buscar palabras
        for (String word : words) {
            System.out.println("esta '" + word + "'? " + trie.searchWord(word));
        }
        // cantidad de palabras
        System.out.println("\nCantidad de palabras : " + trie.contarPalabras());
        // mostrar todas las palabras
        System.out.println("\nTodas las palabras : ");
        trie.mostrarPalabras();
        //Buscar palabras parecidas
        System.out.println();
        String pal = "anime";
        System.out.println("\nExiste "+pal+":\n"+trie.buscarPalabrasParecidas(pal));
        // mostrar palabras con un prefijo
        String pref = "ap";
        System.out.println("\nPalabras con '" + pref + "':");
        trie.buscarPrefijos(pref);
        // cantidad de prefijos
        System.out.println("\nCantidad de prefijos: "+ trie.contarPrefijos());
        //Cantidad de nodos
        System.out.println("\nCantidad de nodos: " + trie.contarNodos());
    }
}
