package hackerrank;

import java.util.Arrays;
import java.util.Scanner;

public class Flowers {
	public static void main(String args[]) {

		var in = new Scanner(System.in);

		int N, K;
		N = in.nextInt();
		// K = in.nextInt();

		long C[] = new long[N];
		for (var i = 0; i < N; i++) {
			C[i] = in.nextInt();
		}
		long result = 0;

		descendingQuickSort(C, 0, N - 1);
		// quickSort(C,0,N-1);
		System.out.print(Arrays.toString(C));
		/*for (int i = 0; i < N; i++) {
				result += (C[i] * ((i / K) + 1));
		}
		System.out.println(result);*/
	}

	public static void descendingQuickSort(long[] c, int lowIndex, int highIndex) {
		int low = lowIndex, high = highIndex;
		var pivot = c[(low + high) / 2];
		while (low < high) {
			while (low < highIndex && c[low] > pivot) low++;
			while (high > lowIndex && c[high] < pivot) high--;
			if (low < high) {
				swap(c, high, low);
				low++;
				high--;
			}
		}
		if (low + 1 < highIndex) descendingQuickSort(c, low + 1, highIndex);
		if (high - 1 > lowIndex) descendingQuickSort(c, lowIndex, high - 1);
	}

	public static void swap(long[] c, int high, int low) {
		var temp = c[high];
		c[high] = c[low];
		c[low] = temp;
	}
}
