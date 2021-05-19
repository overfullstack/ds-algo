package hackerrank.DS;

/** Created by Gopala Akshintala on 11/5/16. */
public class Vertex implements Comparable<Vertex> {
  public int index;
  public int distFromSource = -1;

  Vertex(int index) {
    this.index = index;
  }

  @Override
  public int compareTo(Vertex v) {
    return Integer.compare(this.distFromSource, v.distFromSource);
  }
}
