public class AVLTree {
    AVLNode root; //raíz del árbol
    boolean grown, shrunk, found; //variables artificiales para los métodos

    AVLTree() {//constructor para un árbol vacío
        root = null;
        grown = false;
        shrunk = false;
        found = false;
    }

    boolean search(int c) {//busca el contenido c en el árbol
        AVLNode n = root;
        while (n != null) {
            if (c == n.content) {
                return true;
            }
            if (c < n.content) {
                n = n.left; 
            }else {
                n = n.right;
            }
        }
        return false;
    }

    void balanceError(AVLNode n) {
        System.out.println("Error en el valor de equilibrio: " + n.balance + " y contenido: " + n.content);
    }

    boolean insert(int c) {//inserta el contenido c en el árbol
        found = false;
        grown = true; //por defecto el TB más bajo crecerá
        root = insert(root, c);
        return !found;
    }

    AVLNode insert(AVLNode n, int c) {
        if (n == null) {
            return new AVLNode(c);
        }
        if (c == n.content) {
            found = true;
            grown = false;
        } else {
            if (c < n.content) {
                n.left = insert(n.left, c);
                if (grown) {
                    n.balance--; //el balance se inclina hacia la izquierda

                            }} else {
                n.right = insert(n.right, c);
                if (grown) {
                    n.balance++; //el balance se inclina hacia la derecha

                            }}
            switch (n.balance) {
                case -2: {
                    if (n.left.balance == +1) {
                        rotateLeft(n.left);
                    }
                    rotateRight(n);
                    grown = false;
                    break;
                }
                case -1:
                    break;
                case 0: {
                    grown = false;
                    break;
                }
                case +1:
                    break;
                case +2: {
                    if (n.right.balance == -1) {
                        rotateRight(n.right);
                    }
                    rotateLeft(n);
                    grown = false;
                    break;
                }
                default: {
                    balanceError(n);
                    break;
                }
            }
        }
        return n;
    }

    void rotateRight(AVLNode n) {//rotación simple a la derecha
        AVLNode m = n.left;
        int cc = n.content;
        n.content = m.content;
        m.content = cc;
        n.left = m.left;
        m.left = m.right;
        m.right = n.right;
        n.right = m;
        int bm = 1 + Math.max(-m.balance, 0) + n.balance;
        int bn = 1 + m.balance + Math.max(0, bm);
        n.balance = (byte) bn;
        m.balance = (byte) bm;
    }

    void rotateLeft(AVLNode n) {//rotación simple a la izquierda
        AVLNode m = n.right;
        int cc = n.content;
        n.content = m.content;
        m.content = cc;
        n.right = m.right;
        m.right = m.left;
        m.left = n.left;
        n.left = m;
        int bm = -(1 + Math.max(+m.balance, 0) - n.balance);
        int bn = -(1 - m.balance + Math.max(0, -bm));
        n.balance = (byte) bn;
        m.balance = (byte) bm;
    }

    int height() {//devuelve la altura
        int h = 0;
        AVLNode n = root;
        while (n != null) {
            h++;
            if (n.balance > 0) {
                h += n.balance;
                n = n.left;
            } else {
                h -= n.balance;
                n = n.right;
            }
        }
        return h;
    }

    int internal() {//trayectoria interna que alarga
        return internal(root, 0);
    }

    int internal(AVLNode n, int h) {
        if (n == null) {
            return 0; 
        }else {
            h++;
            return h + internal(n.left, h) + internal(n.right, h);
        }
    }

    boolean delete(int c) {//borra o elimina el contenido c del árbol
        found = true;
        shrunk = true;
        root = delete(root, c);
        return found;
    }
    AVLNode delete(AVLNode n, int c) {
        if (n == null) {
            found = false;
            shrunk = false;
            return n;
        }
        if (c == n.content) {
            if (n.left == null) {
                return n.right;
            }
            if (n.right == null) {
                return n.left;
            }
            n.content = c = minValue(n.right);
        }
        if (c < n.content) {
            n.left = delete(n.left, c);
            if (shrunk) {
                n.balance++;
            }
        } else {
            n.right = delete(n.right, c);
            if (shrunk) {
                n.balance--;
            }
        }
        switch (n.balance) {
            case -2: {
                switch (n.left.balance) {
                    case +1: {
                        rotateLeft(n.left);
                        break;
                    }
                    case 0: {
                        shrunk = false;
                        break;
                    }
                    case -1:
                        break;
                    default: {
                        balanceError(n.left);
                        break;
                    }
                }
                rotateRight(n);
                break;
            }
            case -1: {
                shrunk = false;
                break;
            }
            case 0:
                break;
            case +1: {
                shrunk = false;
                break;
            }
            case +2: {
                switch (n.right.balance) {
                    case -1: {
                        rotateRight(n.right);
                        break;
                    }
                    case 0: {
                        shrunk = false;
                        break;
                    }
                    case +1:
                        break;
                    default: {
                        balanceError(n.right);
                        break;
                    }
                }
                rotateLeft(n);
                break;
            }
            default: {
                balanceError(n);
                break;
            }
        }
        return n;
    }

    int minValue(AVLNode n) {//determina el valor más pequeño en el subárbol n
        while (n.left != null) {
            n = n.left;
        }
        return n.content;
    }
}
