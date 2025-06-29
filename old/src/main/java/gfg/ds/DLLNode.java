package gfg.ds;

/** Created by gakshintala on 4/22/16. */
public class DLLNode {
	public int val;
	public DLLNode next;
	public DLLNode prev;

	public DLLNode(int val) {
		this.val = val;
	}

	public DLLNode(int val, DLLNode next, DLLNode prev) {
		this.val = val;
		this.next = next;
		this.prev = prev;
	}

	@Override
	public String toString() {
		return String.valueOf(this.val);
	}
}
