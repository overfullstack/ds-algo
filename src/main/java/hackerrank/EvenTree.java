package hackerrank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// This has a limitation that input needs to be in specific order.
public class EvenTree {
    private int numVertices = 0;
    private int edges = 0;
    private Map<Integer, Integer> edgeListMap = new HashMap<>();
    private Map<Integer, ArrayList<Integer>> adjListMap = new HashMap<>();
    private Map<Integer, Integer> numChildren = new HashMap<>();

    public static void main(String[] args) {
        var ob = new Scanner(System.in);
        var eventree = new EvenTree();
        eventree.numVertices = ob.nextInt();
        if (eventree.numVertices % 2 != 0) {
            System.out.println("Number of vertices are not even. Exiting...");
            return;
        }
        eventree.edges = ob.nextInt();
        int vertex1, vertex2;
        for (var i = 0; i < eventree.edges; i++) {
            vertex1 = ob.nextInt();
            vertex2 = ob.nextInt();
            eventree.edgeListMap.put(vertex1, vertex2);
            if (eventree.adjListMap.containsKey(vertex2)) {
                var adjListMap = eventree.adjListMap.get(vertex2);
                adjListMap.add(vertex1);
            } else {
                var adjList = new ArrayList<Integer>();
                adjList.add(vertex1);
                eventree.adjListMap.put(vertex2, adjList);
            }
        }
        //Populate the children map  
        eventree.populateChildren();
        //Decide the edges to be removed  
        eventree.eliminateEdges();
    }

    //Method to decide which edges to remove  
    private void eliminateEdges() {
        var edgesRemoved = 0;
        for (var entry : edgeListMap.entrySet()) {
            if (((this.numChildren.get(entry.getValue()) - this.numChildren
                    .get(entry.getKey())) % 2 != 0 && this.numChildren
                    .get(entry.getKey()) % 2 != 0)
                    || (this.numChildren.get(entry.getValue()) % 2 != 0 && this.numChildren
                    .get(entry.getKey()) % 2 != 0)) {
                ++edgesRemoved;
            }
        }
        System.out.println(edgesRemoved);
    }

    //Method to populate the number of children below each node  
    private void populateChildren() {
        for (var i = numVertices; i > 0; i--) {
            var numChildren = 0;
            var adjList = this.adjListMap.get(i);
            if (adjList != null) {
                for (var node : adjList) {
                    if (this.numChildren.containsKey(node)) {
                        numChildren = numChildren + 1
                                + this.numChildren.get(node);
                    } else
                        ++numChildren;
                }
            }
            this.numChildren.put(i, numChildren);
        }
    }
}  