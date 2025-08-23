package ds;

/** Created by gakshintala on 4/22/16. */
public class ListNode {
	public int val;
	public ListNode next;
	public ListNode down;
	public ListNode random;

	public ListNode(int val) {
		this.val = val;
	}

	public ListNode(int val, ListNode next) {
		this.val = val;
		this.next = next;
	}

	public ListNode(int val, ListNode next, ListNode down) {
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
		head.next = new ListNode(val);
	}
}
