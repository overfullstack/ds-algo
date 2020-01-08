package g4g.Regular.LinkedLists.ReverseLL;

import g4g.DsAndUtils.SLLNode;

import static g4g.DsAndUtils.Utils.printSLL;

/**
 * Created by gakshintala on 6/10/16.
 */
public class ReverseLL {
    public static void main(String[] args) {
        SLLNode l1, l2, l3, l4;
        l1 = new SLLNode(1);
        l2 = new SLLNode(2);
        l3 = new SLLNode(3);
        l4 = new SLLNode(4);

        l1.next = l2;
        l2.next = l3;
        l3.next = l4;

        reverseLinkedList(l1);
        printSLL(l4);
    }

    public static SLLNode reverseLinkedList(SLLNode head) {
        SLLNode prev = null, cur = head, next = head;

        while (next != null) {
            // next will be leading and cur and prev shall be reversing pointers
            cur = next;
            next = next.next;

            cur.next = prev;
            prev = cur;
        }
        return cur;
    }
}
