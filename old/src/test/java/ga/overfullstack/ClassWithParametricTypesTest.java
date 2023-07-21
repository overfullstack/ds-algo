package ga.overfullstack;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ClassWithParametricTypesTest {
  @Test
  @DisplayName("parametric types")
  void parametricTypes() {
    final var example = new ClassWithParametricTypes<String, Integer>() {};
    System.out.println(example.type1);
    System.out.println(example.type2);
  }
}
