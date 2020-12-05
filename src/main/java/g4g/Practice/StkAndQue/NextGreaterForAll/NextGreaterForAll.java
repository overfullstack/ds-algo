package g4g.Practice.StkAndQue.NextGreaterForAll;

import java.util.*;

/**
 * Created by gakshintala on 6/13/16.
 */
public class NextGreaterForAll {
    public static void main(String[] args) {
        var scn = new Scanner(System.in);
        var tests = scn.nextInt();
        while (tests-- > 0) {
            var len = scn.nextInt();
            var arr = fillArray(len, scn);
            findNextGreatest(arr);
        }
    }

    private static void findNextGreatest(int[] arr) {
        Deque<Integer> stk = new ArrayDeque<>();
        Map<Integer, Integer> eleToGreaterEleMap = new HashMap<>();
        stk.push(arr[0]);
        for (var i = 1; i < arr.length; i++) {
            // Pop until a lesser element is found.
            while (!stk.isEmpty() && arr[i] > stk.peek()) {
                eleToGreaterEleMap.put(stk.pop(), arr[i]);
            }
            stk.push(arr[i]);
        }
        // Pop the remaining in stack. Elements which don't have higher elements than themselves remain in it.
        while (!stk.isEmpty()) {
            eleToGreaterEleMap.put(stk.pop(), -1);
        }
        printNextGreatest(eleToGreaterEleMap, arr);
    }

    private static void printNextGreatest(Map<Integer, Integer> eleToGreaterEleMap, int[] arr) {
        for (var ele : arr) {
            System.out.print(eleToGreaterEleMap.get(ele) + " ");
        }
        System.out.println();
    }

    private static int[] fillArray(int len, Scanner scn) {
        var arr = new int[len];
        for (var i = 0; i < len; i++) {
            arr[i] = scn.nextInt();
        }
        return arr;
    }
}
