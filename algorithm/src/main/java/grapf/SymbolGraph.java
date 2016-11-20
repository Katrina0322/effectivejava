package grapf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by spark on 11/20/16.
 */
public class SymbolGraph {
    private Map<String, Integer> map;
    private String[] keys;
    private Graph G;

    public SymbolGraph(InputStream stream, String sp) throws IOException {
        map = new HashMap<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(stream));
        String data = null;
        while ((data = br.readLine()) != null) {
            String[] a = data.split(sp);
            for (int i = 0; i < a.length; i++) {
                if (!map.containsKey(a[i])) map.put(a[i], map.size());
            }
        }

        keys = new String[map.size()];
        for (String name : map.keySet()) {
            keys[map.get(name)] = name;
        }

        G = new Graph(map.size());
        while ((data = br.readLine()) != null) {
            String[] a = data.split(sp);
            int v = map.get(a[0]);
            for (int i = 1; i < a.length; i++) {
                G.addEdge(v, map.get(a[i]));
            }
        }
    }

    public Graph getG() {
        return G;
    }

    public int index(String s){ return map.get(s); }

    public String name(int v){ return keys[v]; }
}
