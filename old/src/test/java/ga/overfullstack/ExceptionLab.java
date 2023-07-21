package ga.overfullstack;

import org.junit.jupiter.api.Test;

class ExceptionLab {
  @Test
  void testCatches() {
    try {
      someFunThrows();
    } catch (Throwable e) {
      System.out.printf("caught " + e.getMessage());
    }
  }

  @Test
  void testWrapper() {
    final var rte = new RuntimeException(new MyException("my-exception"));
    System.out.printf(String.valueOf(rte.getCause()));
  }

  static void someFunThrows() throws Throwable {
    throw new MyException("my-exception");
  }

  private static class MyException extends Exception {
    public MyException(String msg) {
      super(msg);
    }
  }
}
