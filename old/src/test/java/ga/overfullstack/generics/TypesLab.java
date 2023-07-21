package ga.overfullstack.generics;

import java.util.List;
import org.junit.jupiter.api.Test;

class TypesLab {
  @Test
  void parametricTest() {
    System.out.println(List.class.getGenericSuperclass());
    // assertTrue(List.class.getGenericSuperclass() instanceof ParameterizedType);
  }
}
