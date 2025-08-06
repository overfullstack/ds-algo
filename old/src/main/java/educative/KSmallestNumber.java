package educative;

import java.util.*;
 
class KSmallestNumber {
    public static int kSmallestNumber(List<List<Integer>> lists, int k) {
        // storing the length of lists to use it in a loop later
        int listLength = lists.size();
        // declaring a min-heap to keep track of smallest elements
        PriorityQueue<int[]> kthSmallest = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        for (int index = 0; index < listLength; index++) {
            // if there are no elements in the input lists, continue
            if (lists.get(index).size() == 0) {
                continue;
            } else {
                // placing the first element of each list in the min-heap
                kthSmallest.offer(new int[] {lists.get(index).get(0), index, 0});
            }
        }

        // set a counter to match if our kth element
        // equals to that counter, return that number
        int numbersChecked = 0, smallestNumber = 0;
        while (!kthSmallest.isEmpty()) {  // iterating over the elements pushed in our min-heap
            // get the smallest number from top of heap and its corresponding list and index
            int[] smallest = kthSmallest.poll();
            smallestNumber = smallest[0];
            int listIndex = smallest[1];
            int numIndex = smallest[2];
            numbersChecked++;

            if (numbersChecked == k) {
                break;
            }

            // if there are more elements in list of the top element,
            // add the next element of that list to the min-heap
            if (numIndex + 1 < lists.get(smallest[1]).size()) {
                kthSmallest.offer(new int[] {lists.get(listIndex).get(numIndex + 1), listIndex, numIndex + 1});
            }
        }

        // return the Kth number found in input lists
        return smallestNumber;
    }
    // Driver code
    public static void main(String[] args) {
        
        List<List<List<Integer>>> lists = Arrays.asList(
            Arrays.asList(
                Arrays.asList(2, 6, 8),
                Arrays.asList(3, 6, 10),
                Arrays.asList(5, 8, 11)
            ),
            Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5),
                Arrays.asList(6, 7, 8, 15),
                Arrays.asList(10, 11, 12, 13),
                Arrays.asList(5, 10)
            ),
            Arrays.asList(
                Arrays.asList(),
                Arrays.asList(),
                Arrays.asList()
            ),
            Arrays.asList(
                Arrays.asList(1, 1, 3, 8),
                Arrays.asList(5, 5, 7, 9),
                Arrays.asList(3, 5, 8, 12)
            ),
            Arrays.asList(
                Arrays.asList(5, 8, 9, 17),
                Arrays.asList(),
                Arrays.asList(8, 17, 23, 24)
            )
        );

        int[] k = {5, 50, 7, 4, 8};

        // loop to execute till the length of list k
        for (int i = 0; i < k.length; i++) {
            System.out.println(i + 1 + ".\t Input lists: " + lists.get(i) +
                "\n\t K = " + k[i] +
                "\n\t " + k[i] + "th smallest number from the given lists is: " +
                kSmallestNumber(lists.get(i), k[i]));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    
    }
}
