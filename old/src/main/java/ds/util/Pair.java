package ds.util;

public record Pair<F, S>(F first, S second) {
  public static <F, S> Pair<F, S> of(F first, S second) {
    return new Pair<>(first, second);
  }

  public static Pair<Integer, Integer> of(int[] arr) {
    return new Pair<>(arr[0], arr[1]);
  }
}
