package gfg.ds;

/** Created by gakshintala on 4/22/16. */
public class SLLNode {
	public int val;
	public SLLNode next;
	public SLLNode down;
	public SLLNode random;

	public SLLNode(int val) {
		this.val = val;
	}

	public SLLNode(int val, SLLNode next) {
		this.val = val;
		this.next = next;
	}

	public SLLNode(int val, SLLNode next, SLLNode down) {
		this.val = val;
		this.next = next;
		this.down = down;
	}

	@Override
	public String toString() {
		return String.valueOf(this.val);
	}

	public void add(int val) {
		var head = this;
		while (head.next != null) {
			head = head.next;
		}
		head.next = new SLLNode(val);
	}
}
