package g4g.Regular.LinkedLists.OddEvenRearrange;

import g4g.DsAndUtils.SLLNode;

import static g4g.DsAndUtils.Utils.printSLL;

/**
 * Created by gakshintala on 4/22/16.
 */
public class OddEvenRearrange {
    public static void main(String[] args) {
        var head = new SLLNode(1);
        head.next = new SLLNode(2);
        head.next.next = new SLLNode(3);
        head.next.next.next = new SLLNode(4);
        head.next.next.next.next = new SLLNode(5);

        rearrangeEvenOdd(head);
        printSLL(head);
    }

    private static void rearrangeEvenOdd(SLLNode head) {
        var odd = head;
        var even = head.next;

        var evenFirst = even; // Store it to link even and odd at the end
        while (true) {
            // Check even.nextRight before setting to odd
            // If list ended, link both even and odd lists
            if (even == null || even.next == null) {
                odd.next = evenFirst;
                break;
            }

            odd.next = even.next;
            // This kind of makes 2 jumps
            odd = odd.next;

            // Check odd.nextRight before setting to even
            if (odd.next == null) { // No need to check odd == null, coz now odd = even.next and it is already verified above.
                odd.next = evenFirst; // Link odd and even lists
                even.next = null;
                break;
            }

            even.next = odd.next;
            even = even.next;
        }
    }
}
