package g4g.regular.LinkedLists.CloneLL;

import g4g.ds.SLLNode;

/** Created by gakshintala on 6/21/16. */
public class CloneLL {
  public static void main(String[] args) {
    var head = new SLLNode(1);
    head.add(2);
    head.add(3);
    head.add(4);
    head.add(5);

    head.random = head.next.next;
    head.next.random = head.next.next.next;
    head.next.next.random = head.next.next.next.next;
    head.next.next.next.random = head.next.next.next.next.next;
    head.next.next.next.next.random = head.next;

    System.out.println("**Original: ");
    print(head);

    var clonedHead = cloneLL(head);
    System.out.println("**Cloned: ");
    print(clonedHead);
  }

  private static SLLNode cloneLL(SLLNode head) {
    insertClonesAlternatively(head);
    assignRandomNodesToClones(head);

    var cloneHead = head.next;
    separateOriginalAndClone(head, cloneHead);

    return cloneHead;
  }

  private static void separateOriginalAndClone(SLLNode head, SLLNode cloneHead) {
    while (true) {
      head.next = head.next.next;
      head = head.next;

      if (head == null) {
        break;
      }

      cloneHead.next = cloneHead.next.next;
      cloneHead = cloneHead.next;
    }
  }

  private static void assignRandomNodesToClones(SLLNode head) {
    while (head != null) {
      // head.nextRight points to current clone and head.random.nextRight will point to clone of
      // original head.random
      head.next.random = (head.random == null) ? null : head.random.next;
      head = head.next.next;
    }
  }

  private static void insertClonesAlternatively(SLLNode head) {
    while (head != null) {
      var cloneNode = new SLLNode(head.val);
      cloneNode.next = head.next;
      head.next = cloneNode;
      head = cloneNode.next;
    }
  }

  public static void print(SLLNode node) {
    while (node != null) {
      System.out.println("(" + node.val + "," + node.random + ")");
      node = node.next;
    }
  }
}
