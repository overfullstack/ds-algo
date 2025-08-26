package practice;

/* 26 Aug 2025 16:23 */

public class CompareVersionNumbers {
	public int compareVersion(String version1, String version2) {
		var version1Split = version1.split("\\.");
		var version2Split = version2.split("\\.");
		var len = Math.max(version1Split.length, version2Split.length);
		for (var i = 0; i < len; i++) {
			Integer v1 = (i < version1Split.length) ? Integer.parseInt(version1Split[i]) : 0;
			Integer v2 = (i < version2Split.length) ? Integer.parseInt(version2Split[i]) : 0;
			var compare = v1.compareTo(v2);
			if (compare != 0) {
				return compare;
			}
		}
		return 0;
	}
}
