package practice.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/** [126. Word Ladder II](https://leetcode.com/problems/word-ladder-ii) */
public class WordLadder2 {
	// ! Efficient due to smaller search. Think of it like a tree. Going from root to leaf needs more
	// ! searching compared to leaf to root
	public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
		var wordSet = new HashSet<>(wordList);
		if (!wordSet.contains(endWord)) {
			return Collections.emptyList();
		}
		var queue = new ArrayDeque<String>();
		queue.add(beginWord);
		wordSet.remove(beginWord);
		var distanceFromBeginWord = new HashMap<String, Integer>();
		distanceFromBeginWord.put(beginWord, 0);
		var predecessors = new HashMap<String, Set<String>>();
		var endFound = false;
		var distance = 0;
		while (!queue.isEmpty() && !endFound) {
			var queueSize = queue.size();
			distance++;
			// ! Level order traversal
			while (queueSize-- > 0) {
				var curWord = queue.removeFirst();
				var curWordArr = curWord.toCharArray();
				// ! Try all possible words from curWord
				for (var i = 0; i < curWordArr.length; i++) {
					var curCh = curWordArr[i];
					for (var ch = 'a'; ch <= 'z'; ch++) {
						if (curCh != ch) {
							curWordArr[i] = ch;
							var nextWord = new String(curWordArr);
							// ! Already visited in the same level from curWord or different predecessor
							if (distanceFromBeginWord.getOrDefault(nextWord, 0) == distance) {
								// ! predecessors is a `Set`, so `curWord` can be added any number of times
								predecessors.get(nextWord).add(curWord);
							} else if (wordSet.contains(nextWord)) {
								queue.add(nextWord);
								wordSet.remove(nextWord); // ! Mark visited globally
								distanceFromBeginWord.put(nextWord, distance);
								predecessors.computeIfAbsent(nextWord, _ -> new HashSet<>()).add(curWord);
								// ! This can be hit multiple times in the same level, finish the current level
								if (nextWord.equals(endWord)) {
									endFound = true;
								}
							}
						}
					}
					curWordArr[i] = curCh;
				}
			}
		}
		if (!endFound) {
			return Collections.emptyList();
		}
		return dfsPredecessors(endWord, beginWord, new ArrayList<>(), predecessors);
	}

	private static List<List<String>> dfsPredecessors(
			String curWord, String beginWord, List<String> path, Map<String, Set<String>> graph) {
		path.addFirst(curWord); // ! `addFirst` to build in reverse direction
		if (curWord.equals(beginWord)) {
			return List.of(new ArrayList<>(path));
		}
		return graph.getOrDefault(curWord, Collections.emptySet()).stream()
				.flatMap(
						predecessor -> {
							var pathToBeginWord = dfsPredecessors(predecessor, beginWord, path, graph).stream();
							path.removeFirst(); // ! Mutating same `path` across recursions
							return pathToBeginWord;
						})
				.toList();
	}

	static void main() {
		var wordLadder2 = new WordLadder2();

		// Test case 1: Simple case
		var test1 = List.of("hot", "dot", "dog", "lot", "log", "cog");
		var result1a = wordLadder2.findLadders("hit", "cog", test1);
		System.out.println("Test 1 - findLadders: " + result1a);

		// Test case 2: No path case
		var test2 = List.of("hot", "dot", "dog", "lot", "log");
		var result2a = wordLadder2.findLadders("hit", "cog", test2);
		System.out.println("Test 2 - findLadders: " + result2a);

		// Test case 3: Multiple paths
		var test3 = List.of("ted", "tex", "red", "tax", "tad", "den", "rex", "pee");
		var result3a = wordLadder2.findLadders("red", "tax", test3);
		System.out.println("Test 3 - findLadders: " + result3a);

		// Test case 4: Large input (this was timing out before)
		var test4 =
				List.of(
						"aaaaa", "caaaa", "cbaaa", "daaaa", "dbaaa", "eaaaa", "ebaaa", "faaaa", "fbaaa",
						"gaaaa", "gbaaa", "haaaa", "hbaaa", "iaaaa", "ibaaa", "jaaaa", "jbaaa", "kaaaa",
						"kbaaa", "laaaa", "lbaaa", "maaaa", "mbaaa", "naaaa", "nbaaa", "oaaaa", "obaaa",
						"paaaa", "pbaaa", "bbaaa", "bbcaa", "bbcba", "bbdaa", "bbdba", "bbeaa", "bbeba",
						"bbfaa", "bbfba", "bbgaa", "bbgba", "bbhaa", "bbhba", "bbiaa", "bbiba", "bbjaa",
						"bbjba", "bbkaa", "bbkba", "bblaa", "bblba", "bbmaa", "bbmba", "bbnaa", "bbnba",
						"bboaa", "bboba", "bbpaa", "bbpba", "bbbba", "abbba", "acbba", "dbbba", "dcbba",
						"ebbba", "ecbba", "fbbba", "fcbba", "gbbba", "gcbba", "hbbba", "hcbba", "ibbba",
						"icbba", "jbbba", "jcbba", "kbbba", "kcbba", "lbbba", "lcbba", "mbbba", "mcbba",
						"nbbba", "ncbba", "obbba", "ocbba", "pbbba", "pcbba", "ccbba", "ccaba", "ccaca",
						"ccdba", "ccdca", "cceba", "cceca", "ccfba", "ccfca", "ccgba", "ccgca", "cchba",
						"cchca", "cciba", "ccica", "ccjba", "ccjca", "cckba", "cckca", "cclba", "cclca",
						"ccmba", "ccmca", "ccnba", "ccnca", "ccoba", "ccoca", "ccpba", "ccpca", "cccca",
						"accca", "adcca", "bccca", "bdcca", "eccca", "edcca", "fccca", "fdcca", "gccca",
						"gdcca", "hccca", "hdcca", "iccca", "idcca", "jccca", "jdcca", "kccca", "kdcca",
						"lccca", "ldcca", "mccca", "mdcca", "nccca", "ndcca", "occca", "odcca", "pccca",
						"pdcca", "ddcca", "ddaca", "ddada", "ddbca", "ddbda", "ddeca", "ddeda", "ddfca",
						"ddfda", "ddgca", "ddgda", "ddhca", "ddhda", "ddica", "ddida", "ddjca", "ddjda",
						"ddkca", "ddkda", "ddlca", "ddlda", "ddmca", "ddmda", "ddnca", "ddnda", "ddoca",
						"ddoda", "ddpca", "ddpda", "dddda", "addda", "aedda", "bddda", "bedda", "cddda",
						"cedda", "fddda", "fedda", "gddda", "gedda", "hddda", "hedda", "iddda", "iedda",
						"jddda", "jedda", "kddda", "kedda", "lddda", "ledda", "mddda", "medda", "nddda",
						"nedda", "oddda", "oedda", "pddda", "pedda", "eedda", "eeada", "eeaea", "eebda",
						"eebea", "eecda", "eecea", "eefda", "eefea", "eegda", "eegea", "eehda", "eehea",
						"eeida", "eeiea", "eejda", "eejea", "eekda", "eekea", "eelda", "eelea", "eemda",
						"eemea", "eenda", "eenea", "eeoda", "eeoea", "eepda", "eepea", "eeeea", "ggggg",
						"agggg", "ahggg", "bgggg", "bhggg", "cgggg", "chggg", "dgggg", "dhggg", "egggg",
						"ehggg", "fgggg", "fhggg", "igggg", "ihggg", "jgggg", "jhggg", "kgggg", "khggg",
						"lgggg", "lhggg", "mgggg", "mhggg", "ngggg", "nhggg", "ogggg", "ohggg", "pgggg",
						"phggg", "hhggg", "hhagg", "hhahg", "hhbgg", "hhbhg", "hhcgg", "hhchg", "hhdgg",
						"hhdhg", "hhegg", "hhehg", "hhfgg", "hhfhg", "hhigg", "hhihg", "hhjgg", "hhjhg",
						"hhkgg", "hhkhg", "hhlgg", "hhlhg", "hhmgg", "hhmhg", "hhngg", "hhnhg", "hhogg",
						"hhohg", "hhpgg", "hhphg", "hhhhg", "ahhhg", "aihhg", "bhhhg", "bihhg", "chhhg",
						"cihhg", "dhhhg", "dihhg", "ehhhg", "eihhg", "fhhhg", "fihhg", "ghhhg", "gihhg",
						"jhhhg", "jihhg", "khhhg", "kihhg", "lhhhg", "lihhg", "mhhhg", "mihhg", "nhhhg",
						"nihhg", "ohhhg", "oihhg", "phhhg", "pihhg", "iihhg", "iiahg", "iiaig", "iibhg",
						"iibig", "iichg", "iicig", "iidhg", "iidig", "iiehg", "iieig", "iifhg", "iifig",
						"iighg", "iigig", "iijhg", "iijig", "iikhg", "iikig", "iilhg", "iilig", "iimhg",
						"iimig", "iinhg", "iinig", "iiohg", "iioig", "iiphg", "iipig", "iiiig", "aiiig",
						"ajiig", "biiig", "bjiig", "ciiig", "cjiig", "diiig", "djiig", "eiiig", "ejiig",
						"fiiig", "fjiig", "giiig", "gjiig", "hiiig", "hjiig", "kiiig", "kjiig", "liiig",
						"ljiig", "miiig", "mjiig", "niiig", "njiig", "oiiig", "ojiig", "piiig", "pjiig",
						"jjiig", "jjaig", "jjajg", "jjbig", "jjbjg", "jjcig", "jjcjg", "jjdig", "jjdjg",
						"jjeig", "jjejg", "jjfig", "jjfjg", "jjgig", "jjgjg", "jjhig", "jjhjg", "jjkig",
						"jjkjg", "jjlig", "jjljg", "jjmig", "jjmjg", "jjnig", "jjnjg", "jjoig", "jjojg",
						"jjpig", "jjpjg", "jjjjg", "ajjjg", "akjjg", "bjjjg", "bkjjg", "cjjjg", "ckjjg",
						"djjjg", "dkjjg", "ejjjg", "ekjjg", "fjjjg", "fkjjg", "gjjjg", "gkjjg", "hjjjg",
						"hkjjg", "ijjjg", "ikjjg", "ljjjg", "lkjjg", "mjjjg", "mkjjg", "njjjg", "nkjjg",
						"ojjjg", "okjjg", "pjjjg", "pkjjg", "kkjjg", "kkajg", "kkakg", "kkbjg", "kkbkg",
						"kkcjg", "kkckg", "kkdjg", "kkdkg", "kkejg", "kkekg", "kkfjg", "kkfkg", "kkgjg",
						"kkgkg", "kkhjg", "kkhkg", "kkijg", "kkikg", "kkljg", "kklkg", "kkmjg", "kkmkg",
						"kknjg", "kknkg", "kkojg", "kkokg", "kkpjg", "kkpkg", "kkkkg", "ggggx", "gggxx",
						"ggxxx", "gxxxx", "xxxxx", "xxxxy", "xxxyy", "xxyyy", "xyyyy", "yyyyy", "yyyyw",
						"yyyww", "yywww", "ywwww", "wwwww", "wwvww", "wvvww", "vvvww", "vvvwz", "avvwz",
						"aavwz", "aaawz", "aaaaz");

		System.out.println("Test 4 - Large input test (this was timing out before):");
		long startTime = System.currentTimeMillis();
		var result4a = wordLadder2.findLadders("aaaaa", "ggggg", test4);
		long time1 = System.currentTimeMillis() - startTime;

		System.out.println("Test 4 - findLadders: " + result4a.size() + " paths, " + time1 + "ms");
	}
}
