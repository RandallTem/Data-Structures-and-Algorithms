package HashTables;

public class HashFunction {

    public static <T> int hash(T value, int size) {
        String val = value.toString();
        int hash_value = 0;
        for (int i = 0; i < val.length(); i++) {
            hash_value = (hash_value * 255 + val.charAt(i)) % size;
        }
        return hash_value % size;
    }

    public static <T> int doublehash(T value, int size) {
        String val = value.toString();
        int hash_value = 0;
        for (int i = val.length()-1; i >= 0; i--) {
            hash_value = (hash_value * 255 + val.charAt(i)) % size;
        }
        int step = hash_value % size;
        return step == 0 ? 1 : step;
    }

}
