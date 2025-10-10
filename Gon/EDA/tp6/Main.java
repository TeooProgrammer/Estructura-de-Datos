package tp6;
public class Main {
    public static void main(String[] args) {
        String[] words = {"algorithm","animation","ant","apache","append","apple","application","approach"};
        //--------MATRIZ--------
        System.out.println("==============MATRIZ==============");
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
        // mostrar palabras con un prefijo
        String pref = "ap";
        System.out.println("\nPalabras con '" + pref + "':");
        trie.buscarPrefijos(pref);
        // cantidad de prefijos
        System.out.println("\nCantidad de prefijos: "+ trie.contarPrefijos());

        System.out.println("\nCantidad de nodos: " + trie.contarNodos());
        
        System.out.println("\n==============fin MATRIZ==============");
    //     //--------ARREGLO ENLAZADO--------
    //     System.out.println("\n==============ARREGLOS==============");
    //     TrieArr trieArr = new TrieArr();
    //     // insertar palabras
    //     for (String word : words) {
    //         trieArr.insertWord(word);
    //     }
    //     // buscar palabras
    //     for (String word : words) {
    //         System.out.println("esta '" + word + "'? " + trieArr.searchWord(word));
    //     }
    //     // mostrar todas las palabras
    //     System.out.println("\nTodas las palabras del arreglo : ");
    //     trie.mostrarPalabras();

    //     // mostrar palabras con un prefijo
    //     String pref2 = "ph";
    //     System.out.println("\nPalabras con '" + pref2 + "':");
    //     trie.buscarPrefijos(pref2);

    //     System.out.println("\n==============FIN ARREGLOS==============");
        
    //     //--------LISTAS ENLAZADAS--------
    //     System.out.println("\n==============LISTAS==============");
    //     TrieL trieL = new TrieL();
    //     // insertar palabras
    //     for (String word : words) {
    //         trieArr.insertWord(word);
    //     }
    //     // buscar palabras
    //     for (String word : words) {
    //         System.out.println("esta '" + word + "'? " + trieL.searchWord(word));
    //     }
    //     // mostrar todas las palabras
    //     System.out.println("\nTodas las palabras del arreglo : ");
    //     trie.mostrarPalabras();

    //     // mostrar palabras con un prefijo
    //     String pref3 = "xm";
    //     System.out.println("\nPalabras con '" + pref3 + "':");
    //     trie.buscarPrefijos(pref3);
    //     System.out.println("\n==============FIN LISTAS==============");
    }
}
