public class AVLNode {
    int content; //el contenido aquí es entero
    byte balance; //para los valores -2, -1, 0, 1, +2
    AVLNode left; //sucesor izquierdo
    AVLNode right; //sucesor derecho

    AVLNode(int c) {//constructor para los nuevos nodos
        content = c;
        balance = 0;
        left = null;
        right = null;
    }

}
