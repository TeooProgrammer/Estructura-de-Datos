package Parcial2.V;

public class Keys {
    int arity, size;
    Object[] keys;

    Keys(int arity) {
        this.arity = arity;
        size = 0;
        keys = new Object[arity];
    }

    Object keyAt(int index) {
        if (index < 0 || index >= size) return null;
        return keys[index];
    }

    int search(Object key) {
        for (int i = 0; i < size; i++) {
            int c = ((String) key).compareTo((String) keys[i]);
            if (c < 0) return i;
            if (c == 0) return -1;
        }
        return size;
    }

    int addKey(Object key) {
        int index = search(key);
        if (index == -1) return -1;
        for (int k = size; k > index; k--) keys[k] = keys[k - 1];
        keys[index] = key;
        size++;
        return index;
    }

    boolean deleteKey(Object key) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            int c = ((String) key).compareTo((String) keys[i]);
            if (c < 0) return false;
            if (c == 0) {
                index = i;
                break;
            }
        }
        if (index == -1) return false;
        for (int j = index + 1; j < size; j++) keys[j - 1] = keys[j];
        size--;
        return true;
    }
}
