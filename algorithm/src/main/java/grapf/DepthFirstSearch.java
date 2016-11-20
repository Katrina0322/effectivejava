package grapf;

import java.util.Stack;

/**
 * Created by spark on 11/20/16.
 */
public class DepthFirstSearch {
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;
    private int count;

    public DepthFirstSearch(Graph G, int s) {
        marked = new boolean[G.getV()];
        edgeTo = new int[G.getV()];
        this.s = s;
        dfs(G, s);
    }

    public boolean marked(int w) {
        return marked[w];
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<>();
        for (int x = v; x != s; x = edgeTo[x]) path.push(x);
        path.push(s);
        return path;
    }

    public int count() {
        return count;
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        count++;
        for (int w : G.adj(v)) {
            if (!marked[w]) dfs(G, w);
        }
    }
}
