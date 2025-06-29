package graph;

/** Created by gakshintala on 12/16/15. */
public class UnionFind {
	private final int[] id;

	UnionFind(int n) {
		id = new int[n];
		for (var i = 0; i <= n; i++) id[i] = i;
	}

	private int root(int i) {
		while (i != id[i]) i = id[i];
		return i;
	}

	public boolean connected(int p, int q) {
		return root(p) == root(q);
	}

	public void union(int p, int q) {
		id[root(p)] = root(q);
	}
}
