package educative;

import java.util.*;

class RepeatedDNA {

  public static Set<String> findRepeatedSequences(String s, int k) {

    int n = s.length();

    if (n < k) {
      return new HashSet<>();
    }

    Map<Character, Integer> mapping = new HashMap<>();
    mapping.put('A', 1);
    mapping.put('C', 2);
    mapping.put('G', 3);
    mapping.put('T', 4);

    int a = 4;

    List<Integer> numbers = new ArrayList<>(Arrays.asList(new Integer[n]));
    Arrays.fill(numbers.toArray(), 0);
    for (int i = 0; i < n; i++) {
      numbers.set(i, mapping.get(s.charAt(i)));
    }

    int hashValue = 0;

    Set<Integer> hashSet = new HashSet<>();
    Set<String> output = new HashSet<>();

    for (int i = 0; i < n - k + 1; i++) {

      if (i == 0) {
        for (int j = 0; j < k; j++) {
          hashValue += numbers.get(j) * (int) Math.pow(a, k - j - 1);
        }
      } else {
        int previousHashValue = hashValue;
        hashValue =
            ((previousHashValue - numbers.get(i - 1) * (int) Math.pow(a, k - 1)) * a)
                + numbers.get(i + k - 1);
      }

      if (hashSet.contains(hashValue)) {
        output.add(s.substring(i, i + k));
      }

      hashSet.add(hashValue);
    }
    return output;
  }

  // Driver code
  public static void main(String[] args) {
    List<String> inputsString =
        Arrays.asList(
            "ACGT",
            "AGACCTAGAC",
            "AAAAACCCCCAAAAACCCCCC",
            "GGGGGGGGGGGGGGGGGGGGGGGGG",
            "TTTTTCCCCCCCTTTTTTCCCCCCCTTTTTTT",
            "TTTTTGGGTTTTCCA",
            "AAAAAACCCCCCCAAAAAAAACCCCCCCTG",
            "ATATATATATATATAT");
    List<Integer> inputsK = Arrays.asList(3, 3, 8, 12, 10, 14, 10, 6);

    for (int i = 0; i < inputsK.size(); i++) {
      System.out.println(
          (i + 1)
              + ".\tInput sequence: "
              + inputsString.get(i)
              + "\n\tk: "
              + inputsK.get(i)
              + "\n\n\tRepeated sequences: "
              + Print.printSetString(findRepeatedSequences(inputsString.get(i), inputsK.get(i))));
      System.out.println(Print.repeat("-", 100));
    }
  }
}

class Print {
  public static String repeat(String str, int pValue) {
    String out = "";
    for (int i = 0; i < pValue; i++) {
      out += str;
    }
    return out;
  }

  public static String printSetString(Set<String> set) {
    StringBuilder sb = new StringBuilder("{");
    Iterator<String> iterator = set.iterator();
    while (iterator.hasNext()) {
      sb.append(iterator.next());
      if (iterator.hasNext()) {
        sb.append(", ");
      }
    }
    sb.append("}");
    return sb.toString();
  }
}
