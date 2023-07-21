package ga.overfullstack.generics;

import java.util.List;
import org.junit.jupiter.api.Test;

class GenericsLab {
  @Test
  void genericsLab1() {
    List<? super String> strList = List.of(new Object());
    final var obj = strList.get(0);
  }
}
