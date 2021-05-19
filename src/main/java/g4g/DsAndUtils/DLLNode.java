package g4g.DsAndUtils;

/** Created by gakshintala on 4/22/16. */
public class DLLNode extends SLLNode {
  public DLLNode next;
  public DLLNode prev;

  public DLLNode(int val) {
    super(val);
  }

  public DLLNode(int val, DLLNode next, DLLNode prev) {
    super(val, next);
    this.prev = prev;
  }

  @Override
  public String toString() {
    return String.valueOf(this.val);
  }
}
