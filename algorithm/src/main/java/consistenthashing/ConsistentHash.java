package consistenthashing;

import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * filename: ConsistentHash
 * Description:
 * Author: ubuntu
 * Date: 10/11/17 3:58 PM
 */
public class ConsistentHash<T> {
    private HashFunction hashFunction;
    private int replicas;
    private SortedMap<Long, T> circle = new TreeMap<>();

    public ConsistentHash(HashFunction hashFunction, int replicas, Collection<T> nodes) {
        this.hashFunction = hashFunction;
        this.replicas = replicas;
        for(T node: nodes){
            add(node);
        }
    }

    public void add(T node) {
        for(int i = 0; i < replicas; i++){
            circle.put(hashFunction.hash(node.toString() + i), node);
        }
    }

    public void remove(T node) {
        for (int i = 0; i < replicas; i++) {
            circle.remove(hashFunction.hash(node.toString() + i));
        }
    }

    public T get(String key){
        if(circle.isEmpty()) return null;
        long hash = hashFunction.hash(key);
        if(!circle.containsKey(hash)){
            SortedMap<Long, T> tailMap = circle.tailMap(hash);
            hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
        }
        return circle.get(hash);
    }

    public long getSize() {
        return circle.size();
    }
}
