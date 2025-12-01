package Parcial1.p3;

public class P3{
    private int[] table;
    private int size;

    public P3(int size) {
        this.size = 12;
        table = new int[size];
        for (int i = 0; i < size; i++) {
            table[i] = -1;  // -1 indica posición vacía
        }
    }

    private int hash1(int key) {
        return key % size;
    }

    private int hash2(int key) {
        return 7 - (key % 7);  // Segunda función hash
    }

    public void insert(int key) {
        int hash = hash1(key);
        int stepSize = hash2(key);
        int attempts = 0;  // Contador para evitar bucles infinitos
        System.out.println("Se inserta valor " + key + " en la posición " + hash);

        // Continuar probando hasta encontrar un lugar vacío o alcanzar el número máximo de intentos
        while (table[hash] != -1 && attempts < size) {  // Colisión detectada
            System.out.println("Colisión en posición " + hash + " para valor " + key);
            hash = (hash + stepSize) % size;  // Doble hash
            attempts++;
        }

        if (attempts == size) {
            System.out.println("No se pudo insertar el valor " + key + " (tabla llena o bucle)");
        } else {
            table[hash] = key;
            System.out.println("Valor " + key + " finalmente insertado en la posición " + hash);
        }
    }

    public void display() {
        for (int i = 0; i < size; i++) {
            System.out.println("Index " + i + ": " + table[i]);
        }
    }

    private int hash3(int key) {
        return (2*key+3)% 10;  // Tercera función hash
    }

    public static void main(String[] args) {
        P3 hashTable = new P3(12);

        int[] keys = {  4371, 1323, 6173, 4199, 4344, 9679, 1989 };
// 
        
        for (int key : keys) {
            hashTable.insert(key);
            hashTable.hash3(key);

        }
        
        hashTable.display();
    }
}
