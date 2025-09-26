package tp3.Hashing;

public class Hashing {
    
    public int[] table;

    public Hashing(int count) {
        table = new int[count];
    }

    public void addSequential(int value) {
        int place = getHash(value);
        if (table[place] != 0) {
            do {
                place = getNextPlace(place);
            } while (table[place] != 0);
        }
        table[place] = value;
    }

    public int searchSequential(int search) {
        int place = getHash(search);
        if (table[place] != search) {
            // para que no siga en forma circular infinitamente
            int init = place;
            do {
                place = getNextPlace(place);
            } while (table[place] != search & init != place);
            if (init == place) {
                return -1;
            }
        }
        return place;
    }

    private int getHash(int value) {
        return value % table.length;
    }

    private int getNextPlace(int place) {
        place++;
        if (place == table.length) {
            place = 0;
        }
        return place;
    }
}

