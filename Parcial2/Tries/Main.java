package Parcial2.Tries;

public class Main {
    public static void main(String[] args) {
        TrieArreglo trie = new TrieArreglo();
        String[] palabras = {"arbol", "arabe", "armario", "abecedario", "motocicleta", "motor", "motoneta", "mobiliario", "cartera", "cuaderno", "libro", "bolso"};

        for (String palabra : palabras) {
            trie.insertWord(palabra);
        }

        // Contar palabras en el Trie
        System.out.println("Cantidad de palabras almacenadas: " + trie.contarPalabras());

        // Listar todas las palabras
        System.out.println("\nTodas las palabras en el Trie:");
        for (String palabra : trie.listarPalabras()) {
            System.out.println("- " + palabra);
        }

        // Buscar palabras con un prefijo
        String prefijo = "moto";
        System.out.println("\nPalabras que comienzan con el prefijo '" + prefijo + "':");
        for (String palabra : trie.listarPalabrasConPrefijo(prefijo)) {
            System.out.println("- " + palabra);
        }

        // Buscar palabras parecidas
        String buscar = "pywds";
        System.out.println("\nPalabras parecidas a '" + buscar + "':");
        for (String palabra : trie.buscarParecidas(buscar)) {
            System.out.println("- " + palabra);
        }

        // Contar prefijos distintos
        System.out.println("\nCantidad de prefijos distintos: " + trie.contarPrefijos());
    }
}

