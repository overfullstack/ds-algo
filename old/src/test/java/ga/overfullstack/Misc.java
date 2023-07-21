package ga.overfullstack;

import static ga.overfullstack.Misc.Some.A;
import static ga.overfullstack.Misc.Some.B;
import static ga.overfullstack.Misc.Some.C;
import static ga.overfullstack.Misc.Some.D;
import static ga.overfullstack.Misc.Some.E;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Misc {
  @Test
  void exceptionInCatch() {
    assertThrows(IllegalArgumentException.class, this::someThrowingFun);
  }

  void someThrowingFun() {
    try {
      throw new IllegalArgumentException("test");
    } catch (Exception e) {
      System.out.println("something");
      throw e;
    } finally {
      System.out.println("finally - someFun");
    }
  }

  @Test
  void testIntCast() {
    Object obj = null;
    final var i = (int) Objects.requireNonNullElse(obj, 0);
    System.out.println(i);
    add(1, 2, System.out::println);
    add(1, 2, result -> assertEquals(3, result));
  }

  static void add(int a, int b) {
    System.out.println(a + b);
  }

  static void add(int a, int b, Consumer<Integer> consumer) {
    consumer.accept(a + b);
  }

  enum Some {
    A,
    B,
    C,
    D,
    E
  }

  @Test
  void setOfEnumsAsKeyForMap() {
    final var map =
        Map.of(
            Set.of(A, B, C), "abc",
            Set.of(D, E), "de");
    System.out.println(map);
  }

  @Test
  @DisplayName("Collections Disjoint")
  void collectionsDisjoint() {
    System.out.println(Collections.disjoint(List.of(), List.of()));
  }

  @Test
  @DisplayName("Assert test")
  void assertTest() {
    assertThat(
            List.of(
                "01txx0000006iyWAAQ",
                "01txx0000006iyXAAQ",
                "01txx0000006iwuAAA",
                "01txx0000006iwuAAA"))
        .containsAll(List.of("01txx0000006iwuAAA", "01txx0000006iyWAAQ", "01txx0000006iyXAAQ"));
  }
}
