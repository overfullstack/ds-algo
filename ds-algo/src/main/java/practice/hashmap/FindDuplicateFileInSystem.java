package practice.hashmap;

import static java.lang.IO.println;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * [609. Find Duplicate File in
 * System](https://leetcode.com/problems/find-duplicate-file-in-system/)
 */
public class FindDuplicateFileInSystem {
	public List<List<String>> findDuplicate(String[] paths) {
		if (paths.length == 0) {
			return Collections.emptyList();
		}
		var contentToFilePath = new HashMap<String, List<String>>();
		for (var path : paths) {
			var filePathsWithContent = path.split("\\s+");
			var directoryPath = filePathsWithContent[0];
			for (var i = 1; i < filePathsWithContent.length; i++) {
				var filePathWithContent = filePathsWithContent[i];
				var idx = filePathWithContent.indexOf("(");
				var content = filePathWithContent.substring(idx);
				var filePath = directoryPath + "/" + filePathWithContent.substring(0, idx);
				contentToFilePath.computeIfAbsent(content, _ -> new ArrayList<>()).add(filePath);
			}
		}
		return contentToFilePath.values().stream().filter(l -> l.size() > 1).toList();
	}

	static void main() {
		var findDuplicateFileInSystem = new FindDuplicateFileInSystem();
		println(
				findDuplicateFileInSystem.findDuplicate(
						new String[] {
							"root/a 1.txt(abcd) 2.txt(efgh)",
							"root/c 3.txt(abcd)",
							"root/c/d 4.txt(efgh)",
							"root 4.txt(efgh)"
						})); // [["root/a/2.txt","root/c/d/4.txt","root/4.txt"],
		// ["root/a/1.txt","root/c/3.txt"]]
		println(
				findDuplicateFileInSystem.findDuplicate(
						new String[] {
							"root/a 1.txt(abcd) 2.txt(efgh)",
							"root/c 3.txt(abcd)",
							"root/c/d 4.txt(efgh)",
							"root 4.txt(efgh)"
						})); // [["root/a/2.txt","root/c/d/4.txt","root/4.txt"],
		// ["root/a/1.txt","root/c/3.txt"]]
	}
}
