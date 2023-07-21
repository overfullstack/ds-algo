package ga.overfullstack;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StringLab {
  @Test
  @DisplayName("String Join")
  void stringJoin() {
    System.out.println(String.join("', '", List.of("a", "b", "c")));
  }
}
