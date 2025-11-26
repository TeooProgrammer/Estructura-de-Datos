package Parcial2.Tries;

import java.util.ArrayList;
import java.util.List;

class TrieArreglo {
    private TrieNode root;
    private final int ALPHABET_SIZE = 26;
    private final char START_CHAR = 'a';

    public TrieArreglo() {
        root = new TrieNode(ALPHABET_SIZE);
    }

    public void insertWord(String word) {
        TrieNode current = root;
        for (char c : word.toLowerCase().toCharArray()) {
            int index = c - START_CHAR;
            if (current.getValueAt(index) == null) {
                current.setValueAt(index, new TrieNode(ALPHABET_SIZE));
            }
            current = current.getValueAt(index);
        }
        current.setValueAt(ALPHABET_SIZE, new TrieNode(0));
    }

    // a) Devolver la cantidad de palabras almacenadas en el Arbol Trie
    public int contarPalabras() {
        return contarPalabrasRecursivo(root);
    }

    private int contarPalabrasRecursivo(TrieNode node) {
        if (node == null) return 0;
        int count = 0;
        if (node.getValueAt(ALPHABET_SIZE) != null) count++; // Es fin de palabra
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            count += contarPalabrasRecursivo(node.getValueAt(i));
        }
        return count;
    }

    // b) Listar todas las palabras almacenadas en el Arbol Trie
    public List<String> listarPalabras() {
        List<String> words = new ArrayList<>();
        listarPalabrasRecursivo(root, "", words);
        return words;
    }

    private void listarPalabrasRecursivo(TrieNode node, String prefijo, List<String> words) {
        if (node == null) return;
        if (node.getValueAt(ALPHABET_SIZE) != null) {
            words.add(prefijo); // agrega la palabra si es fin
        }
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            TrieNode child = node.getValueAt(i);
            if (child != null) {
                char nextChar = (char) (START_CHAR + i);
                listarPalabrasRecursivo(child, prefijo + nextChar, words);
            }
        }
    }

    // c) Mostrar todas las palabras almacenadas en un Arbol Trie, que comienzan con un determinado prefijo
    public List<String> listarPalabrasConPrefijo(String prefijo) {
        List<String> words = new ArrayList<>();
        TrieNode actual = root;
        for (char c : prefijo.toLowerCase().toCharArray()) {
            int index = c - START_CHAR;
            if (actual.getValueAt(index) == null) return words; // Prefijo no encontrado
            actual = actual.getValueAt(index);
        }
        listarPalabrasRecursivo(actual, prefijo, words);
        return words;
    }

    // d) Buscar palabras parecidas
    public List<String> buscarParecidas(String palabra) {
        if (searchWord(palabra)) {
            System.out.println("La palabra '" + palabra + "' fue encontrada en el Trie.");
            List<String> resultado = new ArrayList<>();
            resultado.add(palabra); // Agregar la palabra encontrada a la lista
            return resultado;
        }

        // Palabras que comparten al menos n/2 letras iniciales
        String prefijo = palabra.substring(0, palabra.length() / 2);
        System.out.println("La palabra '" + palabra + "' no fue encontrada. Mostrando palabras parecidas:");
        return listarPalabrasConPrefijo(prefijo);
    }


    // e) Contar la cantidad de prefijos distintos
    public int contarPrefijos() {
        return contarPrefijosRecursivo(root);
    }

    private int contarPrefijosRecursivo(TrieNode node) {
        if (node == null) return 0;

        int count = 0;
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            if (node.getValueAt(i) != null) {
                count++; // Cada hijo representa un prefijo distinto
                count += contarPrefijosRecursivo(node.getValueAt(i));
            }
        }
        return count;
    }

    // Método auxiliar para buscar palabras exactas
    public boolean searchWord(String word) {
        TrieNode current = root;
        for (char c : word.toLowerCase().toCharArray()) {
            int index = c - START_CHAR;
            if (current.getValueAt(index) == null) return false;
            current = current.getValueAt(index);
        }
        return current.getValueAt(ALPHABET_SIZE) != null;
    }
}

