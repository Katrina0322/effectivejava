package grapf;

import java.util.ArrayList;

/**
 * Created by spark on 11/20/16.
 */
public class Graph {
    private int V;  //顶点数
    private int E;  //边数
    private ArrayList<Integer>[] adj;

    public Graph(int v) {
        V = v;
        E = 0;
        adj = new ArrayList[v];
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    public void addEdge(int v, int w) {
        if (v < 0 || v > V) {
            throw new IndexOutOfBoundsException();
        }
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    public int getE() {
        return E;
    }

    public int getV() {
        return V;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }
}
