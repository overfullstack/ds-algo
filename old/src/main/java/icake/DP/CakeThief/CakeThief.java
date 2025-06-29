package icake.DP.CakeThief;

/** Created by gakshintala on 3/22/16. */
public class CakeThief {
	public static void main(String[] args) {
		var cakes =
				new Cake[] {
					new Cake(7, 160), new Cake(3, 90), new Cake(2, 15),
				};

		var capacity = 20;

		System.out.println(maxDuffelBagValue(cakes, capacity));
	}

	private static int maxDuffelBagValue(Cake[] cakes, int capacity) {
		var maxVal = new int[capacity + 1];
		maxVal[0] = 0;

		for (var cake : cakes) {
			for (var cap = cake.weight; cap <= capacity; cap++) {
				// Math.max(Excluding Cake, Including Cake)
				// if excluded, that slot is filled by others
				maxVal[cap] = Math.max(maxVal[cap], cake.value + maxVal[cap - cake.weight]);
			}
		}
		return maxVal[capacity];
	}
}

class Cake {
	int weight;
	int value;

	public Cake(int weight, int value) {
		this.weight = weight;
		this.value = value;
	}
}
