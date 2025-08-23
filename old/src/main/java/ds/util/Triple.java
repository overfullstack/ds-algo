package ds.util;

public record Triple<F, S, T>(F first, S second, T third) {
  public static <F, S, T> Triple<F, S, T> of(F first, S second, T third) {
    return new Triple<>(first, second, third);
  }

  public static Triple<Integer, Integer, Integer> of(int[] arr) {
    return new Triple<>(arr[0], arr[1], arr[2]);
  }
}
