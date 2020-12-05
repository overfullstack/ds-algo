package hackerrank.DS;

/**
 * Created by Gopala Akshintala on 11/5/16.
 */
public class DirectedEdge {
    private final int from, to;
    private final int weight;

    public DirectedEdge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public int from() {
        return from;
    }

    public int to() {
        return to;
    }

    public int weight() {
        return weight;
    }

    @Override
    public String toString() {
        return this.from + "->" + this.to;
    }
}
