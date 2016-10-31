package hashing;

import java.util.List;

/**
 * used to
 * Created by tianjin on 6/22/16.
 */
public class SeparateChainingHashTable<T> {
    private static final int DEFAULT_TABLE_SIZE = 101;

    private int currentSize;

    private List<T>[] theLists;

    private int myHash(T t) {
        int hashVal = t.hashCode();

        hashVal %= theLists.length;

        if (hashVal < 0) {
            hashVal = +theLists.length;
        }
        return hashVal;
    }
}
