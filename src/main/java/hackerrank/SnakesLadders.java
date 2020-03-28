package hackerrank;

import java.util.*;

/**
 * Created by gakshintala on 12/3/15.
 */
public class SnakesLadders {
    public static void main(String[] args) {
        var scn = new Scanner(System.in);
        var testCases = scn.nextInt();
        for (var i = 0; i < testCases; i++) {
            Map<Integer, Integer> snakeOrLad = new HashMap<>();
            var visited = new boolean[101];
            var pathFound = false;

            var ladders = scn.nextInt();
            for (var l = 0; l < ladders; l++)
                snakeOrLad.put(scn.nextInt(), scn.nextInt());
            var snakes = scn.nextInt();
            for (var s = 0; s < snakes; s++)
                snakeOrLad.put(scn.nextInt(), scn.nextInt());

            Queue<Node> boardQueue = new LinkedList<>();
            boardQueue.add(new Node(1, 0));
            visited[1] = true;

            while (!boardQueue.isEmpty()) {
                var square = boardQueue.poll();
                if (square.vertex == 100) {
                    pathFound = true;
                    System.out.println(square.stepsFromSource);
                    break;
                }

                for (var v = 1; v <= 6 && square.vertex + v <= 100; v++) {
                    // Always check the map, if the current vertex holds a snake, or a ladder
                    var referencedVertex = snakeOrLad.get(square.vertex + v);
                    var connectedVertex = (referencedVertex != null && referencedVertex <= 100) ? referencedVertex : square.vertex + v;
                    if (!visited[connectedVertex]) {
                        boardQueue.add(new Node(connectedVertex, square.stepsFromSource + 1));
                        visited[connectedVertex] = true;
                    }
                }
            }
            if (!pathFound) System.out.println("-1");
        }
    }
}

class Node {
    int vertex;
    int stepsFromSource;

    Node(int vertex, int stepsFromSource) {
        this.vertex = vertex;
        this.stepsFromSource = stepsFromSource;
    }
}
