package hackerrank;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by gakshintala on 3/5/16.
 */
public class Permutations {
    private final String in;
    private boolean[] used;
    private StringBuilder out = new StringBuilder();
    private Set<Character> inSet;

    public Permutations(final String str) {
        in = str;
        inSet = new HashSet<>();
        for (var c : in.toCharArray()) {
            inSet.add(c);
        }
        used = new boolean[inSet.size()];
    }

    public static void main(String[] args) {
        var permutations = new Permutations("122");
        permutations.permute();
    }

    public void permute() {
        if (out.length() == in.length()) {
            System.out.println(out);
            return;
        }
        var i = 0;
        var stringLength = in.length();
        Iterator iterator = inSet.iterator();
        while (stringLength-- > 0) {
            if (!used[i]) {
                out.append(iterator.next());
                used[i] = true;
                permute();
                used[i] = false;
                out.setLength(out.length() - 1);
            } else iterator.next();
            i++;
        }
    }
}