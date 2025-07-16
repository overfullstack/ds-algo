package educative;

import java.util.*;

public class Node {
	Node[] child = new Node[26];
	LinkedList<String> searchWords = new LinkedList<>();
}

class PrintHyphens {
	public static String repeat(String str, int pValue) {
		String out = "";
		for (int i = 0; i < pValue; i++) {
			out += str;
		}
		return out;
	}
}

class SearchSuggestion {

	private Node root = new Node();

	public void insert(String word) {
		Node node = root;
		for (char ch : word.toCharArray()) {
			int index = ch - 'a';
			// Create a new node if char does not exist in children dictionary
			if (node.child[index] == null) {
				node.child[index] = new Node();
			}
			node = node.child[index];
			node.searchWords.offer(word);
			if (node.searchWords.size() > 3) {
				node.searchWords.pollLast();
			}
		}
	}

	public List<List<String>> search(String searchWord) {
		List<List<String>> result = new ArrayList<>();
		Node node = root;
		for (char ch : searchWord.toCharArray()) {
			int index = ch - 'a';
			if (node != null) {
				node = node.child[index];
			}
			result.add(node == null ? Arrays.asList() : node.searchWords);
		}
		return result;
	}

	public List<List<String>> suggestedProducts(String[] products, String searchWord) {
		Arrays.sort(products);
		for (String product : products) {
			insert(product);
		}
		return search(searchWord);
	}

	public static void main(String[] args) {
		String[] products = {"bat", "bag", "bassinet", "bread", "cable", "table", "tree", "tarp"};
		String[] searchWordList = {"ba", "in", "ca", "t"};

		for (int i = 0; i < searchWordList.length; i++) {
			SearchSuggestion obj = new SearchSuggestion();
			System.out.println((i + 1) + ".\tProducts:" + Arrays.toString(products));
			System.out.println("\tSearch keyword: " + searchWordList[i]);
			System.out.println(
					"\tSuggested Products: " + obj.suggestedProducts(products, searchWordList[i]));
			System.out.println(PrintHyphens.repeat("-", 100));
		}
	}
}
