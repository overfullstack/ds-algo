package ga.overfullstack;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StreamLab {
  @Test
  @DisplayName("allMatch on emptyList")
  void allMatchOnEmptyList() {
    System.out.println(List.of().stream().allMatch(x -> true));
  }
}
