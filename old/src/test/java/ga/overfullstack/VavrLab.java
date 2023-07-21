package ga.overfullstack;

import io.vavr.control.Either;
import io.vavr.control.Try;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class VavrLab {

  @Test
  void either() {
    final var right = Optional.of(Either.<Integer, Integer>left(1));
    System.out.println(right.map(etr -> etr.map(v -> v == 1).getOrElse(false)).orElse(false));
  }

  @Test
  @DisplayName("Thread sleep with Try")
  void threadSleepWithTry() {
    System.out.println("start");
    Try.run(() -> Thread.sleep(10000));
    System.out.println("done");
  }
}
