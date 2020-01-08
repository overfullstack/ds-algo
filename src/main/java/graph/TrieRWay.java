package graph;

/**
 * Created by gakshintala on 6/28/16.
 */
public class TrieRWay {
    private static int R = 256;
    private TrieNode root = new TrieNode();

    public static void main(String[] args) {
        var trie = new TrieRWay();
        trie.put("gopal", 7);
        trie.put("gokul", 6);

        System.out.println(trie.get("gopal"));
        System.out.println(trie.get("gokul"));

        trie.delete("gokul");
        System.out.println(trie.get("gopal"));
        System.out.println(trie.get("gokul"));

    }

    private void put(String key, int val) {
        root = put(root, key, val, 0);
    }

    private TrieNode put(TrieNode root, String key, int weight, int depth) {
        if (root == null) {
            root = new TrieNode();
        }
        if (depth == key.length()) {
            root.weight = weight;
        } else {
            int c = key.charAt(depth);
            root.next[c] = put(root.next[c], key, weight, depth + 1);
        }
        return root;
    }

    private int get(String key) {
        return get(root, key, 0);
    }

    private int get(TrieNode root, String key, int depth) {
        if (root == null) {
            return -1;
        }
        if (depth == key.length()) {
            return root.weight;
        }
        var c = key.charAt(depth);
        return get(root.next[c], key, depth + 1);
    }

    private void delete(String key) {
        delete(root, key, 0);
    }

    private TrieNode delete(TrieNode root, String key, int depth) {
        if (root == null || (depth == key.length())) {
            return null;
        }
        var c = key.charAt(depth);
        root.next[c] = delete(root.next[c], key, depth + 1);
        if (isAllLinksNull(root.next)) {
            return null;
        }
        return root;
    }

    private boolean isAllLinksNull(TrieNode[] next) {
        for (var trieNode : next) {
            if (trieNode != null) {
                return false;
            }
        }
        return true;
    }

    private static class TrieNode {
        private int weight;
        private TrieNode[] next = new TrieNode[R];
    }
}
