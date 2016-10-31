package GuavaCollection;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * guava use
 * Created by tianjin on 3/10/16.
 */
public class CollectionUse {
    public static void main(String[] args) {

        Set<String> collection = Sets.newHashSet();
        collection.add("nullable");
        System.out.println(collection.hashCode());
    }
}
