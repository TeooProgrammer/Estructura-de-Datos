public class Main {
    public static void main(String[] args) {
        AVLTreeString avlTree = new AVLTreeString();

        // Inserciones
        System.out.println("Insertando valores en el AVL Tree:");
        avlTree.insert("perro");
        avlTree.insert("gato");
        avlTree.insert("elefante");
        avlTree.insert("lobo");
        avlTree.insert("tigre");
        avlTree.insert("leon");
        avlTree.insert("jirafa");

        // Búsquedas
        System.out.println("\nBuscando valores en el AVL Tree:");
        System.out.println("¿Existe 'gato'? " + avlTree.search("gato"));
        System.out.println("¿Existe 'tigre'? " + avlTree.search("tigre"));
        System.out.println("¿Existe 'zorro'? " + avlTree.search("zorro")); // No existe

        // Eliminaciones
        System.out.println("\nEliminando valores del AVL Tree:");
        System.out.println("¿Se eliminó 'gato'? " + avlTree.delete("gato"));
        System.out.println("¿Se eliminó 'tigre'? " + avlTree.delete("tigre"));
        System.out.println("¿Se eliminó 'zorro'? " + avlTree.delete("zorro")); // No existe

        // Verificar si todavía existen después de eliminarlos
        System.out.println("\nBuscando valores después de eliminación:");
        System.out.println("¿Existe 'gato'? " + avlTree.search("gato"));
        System.out.println("¿Existe 'tigre'? " + avlTree.search("tigre"));

        // Altura del árbol
        System.out.println("\nAltura del AVL Tree después de las operaciones: " + avlTree.height());

        // Suma de profundidades internas
        System.out.println("Suma de las profundidades internas del AVL Tree: " + avlTree.internal());
    }
}
