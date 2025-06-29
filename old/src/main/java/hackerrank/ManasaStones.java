package hackerrank;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/** Created by gakshintala on 12/8/15. */
public class ManasaStones {
	public static void main(String[] args) {
		var scn = new Scanner(System.in);
		var c = scn.nextInt();
		while (c-- != 0) {
			var n = scn.nextInt();
			var diff1 = scn.nextInt();
			var diff2 = scn.nextInt();
			Set<Integer> possibles = new TreeSet<>();
			possibles.add(diff1);
			possibles.add(diff2);
			n -= 2;
			while (n-- != 0) {
				Set<Integer> temp = new TreeSet<>();
				for (int v : possibles) {
					temp.add(v + diff1);
					temp.add(v + diff2);
				}
				possibles = temp;
			}
			var out = new StringBuilder();
			for (int possible : possibles) out.append(possible).append(" ");
			System.out.println(out);
		}
	}
}
