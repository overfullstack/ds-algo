package hackerrank.DS;

/** Created by Gopala Akshintala on 11/5/16. */
public record DirectedEdge(int from, int to, int weight) {

	@Override
	public String toString() {
		return this.from + "->" + this.to;
	}
}
