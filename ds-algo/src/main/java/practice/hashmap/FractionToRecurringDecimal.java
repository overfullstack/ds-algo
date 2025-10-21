package practice.hashmap;

import java.util.HashMap;

/* 21 Oct 2025 12:05 */

public class FractionToRecurringDecimal {
	public String fractionToDecimal(int numerator, int denominator) {
		if (numerator == 0) {
			return "0";
		}
		var result = new StringBuilder();
		var sign = ((numerator > 0) ^ (denominator > 0)) ? "-" : "";
		result.append(sign);
		var num = Math.abs((long) numerator);
		var den = Math.abs((long) denominator);
		result.append(num / den);
		num %= den;
		if (num == 0) {
			return result.toString();
		}
		result.append(".");
		var map = new HashMap<Long, Integer>();
		map.put(num, result.length()); // ! No `-1` as this remainder is for next digit
		while (num != 0) {
			num *= 10;
			result.append(num / den);
			num %= den;
			if (map.containsKey(num)) {
				var lastIdx = map.get(num);
				result.insert(lastIdx, "(");
				result.append(")");
				break;
			} else {
				map.put(num, result.length());
			}
		}
		return result.toString();
	}

	static void main() {
		var obj = new FractionToRecurringDecimal();
		System.out.println(obj.fractionToDecimal(1, 2)); // 0.5
		System.out.println(obj.fractionToDecimal(2, 1)); // 2
		System.out.println(obj.fractionToDecimal(2, 3)); // 0.(6)
		System.out.println(obj.fractionToDecimal(4, 333)); // 0.(012)
		System.out.println(obj.fractionToDecimal(1, 5)); // 0.2
		System.out.println(obj.fractionToDecimal(1, 6)); // 0.1(6)
		System.out.println(obj.fractionToDecimal(2, 7)); // 0.(285714)
		System.out.println(obj.fractionToDecimal(1, 3)); // 0.(3)
		System.out.println(obj.fractionToDecimal(1, 1)); // 1
	}
}
