package g4g.Regular.LinkedLists.MergeAlternatively;

import g4g.DsAndUtils.SLLNode;
import g4g.DsAndUtils.Utils;

/** Created by gakshintala on 6/12/16. */
public class MergeAlternatively {
  public static void main(String[] args) {
    SLLNode k1, k2, k3;
    k3 = new SLLNode(5);
    k2 = new SLLNode(3, k3);
    k1 = new SLLNode(1, k2);

    SLLNode l1, l2, l3, l4, l5;
    l5 = new SLLNode(8);
    l4 = new SLLNode(7, l5);
    l3 = new SLLNode(6, l4);
    l2 = new SLLNode(4, l3);
    l1 = new SLLNode(2, l2);

    mergeAlternatively(k1, l1);
    Utils.printSLL(k1);
  }

  private static void mergeAlternatively(SLLNode k, SLLNode l) {
    SLLNode kNext, lNext;
    while (l != null && k != null) {
      // Save nextRight pointers
      kNext = k.next;
      lNext = l.next;

      // link alternatively
      k.next = l;
      l.next = kNext;

      // Move to corresponding nextRight nodes in list
      k = kNext;
      l = lNext;
    }
  }
}
