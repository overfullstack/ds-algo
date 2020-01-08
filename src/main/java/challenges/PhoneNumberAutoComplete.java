package challenges;

import java.util.Scanner;
import java.util.Set;

/**
 * gakshintala created on 11/16/19.
 */

public class PhoneNumberAutoComplete {
    private static final int TRIE_CAPACITY = 26;
    private static final String[] NUM_PAD = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    private static final Trie DICTIONARY = new Trie();

    private static void getAllPossibleWords(int[] numbers, char[] word, int digitIndex) {
        if (digitIndex == numbers.length) {
            final var wordStr = new String(word).trim();
            if (!wordStr.isEmpty()) {
                DICTIONARY.printAutoComplete(wordStr);
            }
            return;
        }
        final var numPadDigit = numbers[digitIndex];
        if (numPadDigit == 0 || numPadDigit == 1) {
            getAllPossibleWords(numbers, word, digitIndex + 1);
        }
        for (var subIndex = 0; subIndex < NUM_PAD[numPadDigit].length(); subIndex++) {
            word[digitIndex] = NUM_PAD[numPadDigit].charAt(subIndex);
            getAllPossibleWords(numbers, word, digitIndex + 1);
        }
    }

    private static void initDictionary() {
        Set.of("tree", "trees", "used", "use", "apple", "ball", "cat").forEach(DICTIONARY::insert);
    }

    public static void main(String[] args) {
        initDictionary();
        var scn = new Scanner(System.in);
        var inputNum = scn.nextLine();
        var numbers = new int[inputNum.length()];
        for (var i = 0; i < inputNum.length(); i++) {
            numbers[i] = Integer.parseInt(String.valueOf(inputNum.charAt(i)));
        }

        getAllPossibleWords(numbers, new char[numbers.length], 0);
    }

    private static class Trie {
        private char value;
        private boolean isEnd;
        private Trie[] children = new Trie[TRIE_CAPACITY];

        Trie(char value) {
            this.value = value;
        }

        public Trie() {
        }

        public void insert(String key) {
            var crawl = this;
            for (var c : key.toCharArray()) {
                crawl.children[c - 'a'] = crawl.children[c - 'a'] == null ? new Trie(c) : crawl.children[c - 'a'];
                crawl = crawl.children[c - 'a'];
            }
            crawl.isEnd = true;
        }

        public void printAutoComplete(String key) {
            var crawl = this;
            for (var c : key.toCharArray()) {
                final var child = crawl.children[c - 'a'];
                if (child == null) {
                    return;
                }
                crawl = child;
            }
            printAutoComplete(crawl, key);
        }

        private void printAutoComplete(Trie node, String curPrefix) {
            if (node.isEnd) {
                System.out.println(curPrefix);
            }
            for (var child : node.children) {
                if (child != null) {
                    printAutoComplete(child, curPrefix + child.value);
                }
            }

        }
    }
}

