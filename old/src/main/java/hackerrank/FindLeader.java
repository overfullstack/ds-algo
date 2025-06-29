package hackerrank;

/* WRONG*/
public class FindLeader {

	public static void main(String[] args) {
		int[] A = {2, 2, 2, 2, 2, 3, 4, 5, 6};
		System.out.println(solution(A));
	}

	static int solution(int[] A) {
		var n = A.length;
		var L = new int[n + 1];
		L[0] = -1;
		System.arraycopy(A, 0, L, 1, n);
		var count = 0;
		var pos = (n + 1) / 2;
		var candidate = L[pos];
		for (var i = 1; i <= n; i++) {
			if (L[i] == candidate) count = count + 1;
		}
		if (count > pos) return candidate;
		return (-1);
	}
}
