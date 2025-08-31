package practice;

/* 31 Aug 2025 12:11 */

/** [838. Push Dominoes](https://leetcode.com/problems/push-dominoes) */
public class PushDominoes {
	public String pushDominoes(String dominoes) {
		dominoes = 'L' + dominoes + 'R'; // ! Add sentinels
		var result = new StringBuilder();
		var start = 0;
		var end = 1;
		while (end < dominoes.length()) {
			if (dominoes.charAt(end) != '.') {
				if (start > 0) {
					result.append(dominoes.charAt(start));
				}
				var windowLen = end - start - 1;
				if (windowLen >= 1) {
					if (dominoes.charAt(start) == dominoes.charAt(end)) {
						result.append(String.valueOf(dominoes.charAt(start)).repeat(windowLen));
					} else if (dominoes.charAt(start) == 'L' && dominoes.charAt(end) == 'R') {
						result.append(".".repeat(windowLen));
					} else {
						result.append("R".repeat(windowLen / 2));
						result.append(".".repeat(windowLen % 2));
						result.append("L".repeat(windowLen / 2));
					}
				}
				start = end;
			}
			end++;
		}
		return result.toString();
	}

	static void main() {
		var pushDominoes = new PushDominoes();
		System.out.println(pushDominoes.pushDominoes(".L.R...LR..L..")); // LL.RR.LLRRLL..
	}
}
