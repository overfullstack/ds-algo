package g4g.Regular.LinkedLists.FlattenAndSortLL;

import g4g.DsAndUtils.SLLNode;

/**
 * Created by gakshintala on 6/21/16.
 */
public class FlattenAndSortLL {

    private static SLLNode flattenLinkedList(SLLNode head) {
        if (head.next == null) return head;
        // Recurring to reach the end of top LL and then do bottom-up
        head.next = flattenLinkedList(head.next);
        return mergeLL(head, head.next);
    }

    private static SLLNode mergeLL(SLLNode ll1, SLLNode ll2) {
        if (ll1 == null) return ll2;
        if (ll2 == null) return ll1;

        // Top-down way to attach values to resultNode in ascending order
        SLLNode resultNode;
        if (ll1.val < ll2.val) {
            resultNode = ll1;
            resultNode.down = mergeLL(ll1.down, ll2);
        } else {
            resultNode = ll2;
            resultNode.down = mergeLL(ll1, ll2.down);
        }
        return resultNode;
    }

    private static void printSLL(SLLNode head) {
        while (head != null) {
            System.out.print(head + " -> ");
            head = head.down;
        }
    }
    
    public static void main(String[] args) {
        var head = new SLLNode(5);
        head.down = new SLLNode(7);
        head.down.down = new SLLNode(8);
        head.down.down.down = new SLLNode(30);

        var node = new SLLNode(10);
        head.next = node;
        node.down = new SLLNode(20);

        node = new SLLNode(19);
        head.next.next = node;
        node.down = new SLLNode(22);
        node.down.down = new SLLNode(50);

        node = new SLLNode(19);
        head.next.next.next = node;
        node.down = new SLLNode(28);
        node.down.down = new SLLNode(35);
        node.down.down.down = new SLLNode(40);
        node.down.down.down.down = new SLLNode(45);

        printSLL(flattenLinkedList(head));
    }
}
