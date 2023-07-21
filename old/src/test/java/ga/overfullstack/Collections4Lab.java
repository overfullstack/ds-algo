package ga.overfullstack;

import io.vavr.Tuple2;
import java.util.List;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Collections4Lab {
  @Test
  void mapOfMaps() {
    MultiValuedMap<String, Tuple2<String, String>> mapOfMaps = new HashSetValuedHashMap<>();
  }

  @Test
  @DisplayName("variance")
  void variance() {
    final List<? extends Bean> producer = List.of(new Bean() {}, new ChildBean() {});
    final List<? super Bean> consumer = List.of("a", 1, new Bean() {});
    final Bean s1 = producer.get(0);
    final Object s = consumer.get(0);
  }

  interface Bean {}

  class ChildBean implements Bean {}
}
