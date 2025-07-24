package gfg.practice.graphs.SnakesAndLadders;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

/** Created by Gopala Akshintala on 22/02/17. */
public class SnakesAndLadders {
	static void main() {
		var scn = new Scanner(System.in);
		var tests = scn.nextInt();
		while (tests-- > 0) {
			var snakesOrLadders = scn.nextInt();
			Map<Integer, Integer> snakesOrLaddersMap = new HashMap<>();
			while (snakesOrLadders-- > 0) {
				snakesOrLaddersMap.put(scn.nextInt(), scn.nextInt());
			}
			System.out.println(minDiceThrows(snakesOrLaddersMap));
		}
	}

	private static int minDiceThrows(Map<Integer, Integer> snakesOrLaddersMap) {
		var visited = new boolean[31];
		Queue<Node> boardQueue = new LinkedList<>();
		boardQueue.add(new Node(1, 0));
		visited[1] = true;
		while (!boardQueue.isEmpty()) {
			var square = boardQueue.poll();
			if (square.vertex == 30) {
				return square.diceThrows;
			}
			for (var v = 1; v <= 6 && square.vertex + v <= 30; v++) {
				var snakeOrLadder = snakesOrLaddersMap.get(square.vertex + v);
				var nextSquareVertex =
						(snakeOrLadder != null && snakeOrLadder <= 30) ? snakeOrLadder : square.vertex + v;
				if (!visited[nextSquareVertex]) {
					boardQueue.add(new Node(nextSquareVertex, square.diceThrows + 1));
					visited[nextSquareVertex] = true;
				}
			}
		}
		return -1;
	}
}

class Node {
	int vertex;
	int diceThrows;

	public Node(int vertex, int diceThrows) {
		this.vertex = vertex;
		this.diceThrows = diceThrows;
	}
}
