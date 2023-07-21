package ga.overfullstack;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.junit.jupiter.api.Test;

class GuavaLab {
  @Test
  void givenTable_whenGet_returnsSuccessfully() {
    Table<String, String, Integer> universityCourseSeatTable = HashBasedTable.create();
    universityCourseSeatTable.put("Mumbai", "Chemical", 120);
    universityCourseSeatTable.put("Mumbai", "IT", 60);
    universityCourseSeatTable.put("Harvard", "Electrical", 60);
    universityCourseSeatTable.put("Harvard", "IT", 120);

    System.out.println(universityCourseSeatTable.rowMap());
  }
}
