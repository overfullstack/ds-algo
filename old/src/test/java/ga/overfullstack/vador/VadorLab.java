package ga.overfullstack.vador;

import com.salesforce.vador.config.ValidationConfig;
import com.salesforce.vador.execution.Vador;
import com.salesforce.vador.types.Validator;
import java.util.Collections;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class VadorLab {
  @Test
  void failFastRecursively() {
    final Validator<RecursiveBean, String> validator =
        recursiveBean -> recursiveBean.id == -1 ? "UNKNOWN_EXCEPTION" : "NONE";
    final var recursiveBean =
        new RecursiveBean(
            1,
            List.of(
                new RecursiveBean(11, Collections.emptyList()),
                new RecursiveBean(-1, Collections.emptyList()),
                new RecursiveBean(13, Collections.emptyList())));
    final var validationConfig =
        ValidationConfig.<RecursiveBean, String>toValidate()
            .withValidator(validator, "NONE")
            .withRecursiveMapper(RecursiveBean::recursiveBeans)
            .prepare();
    final var result = Vador.validateAndFailFast(recursiveBean, validationConfig);
    Assertions.assertThat(result).contains("UNKNOWN_EXCEPTION");
  }

  private record RecursiveBean(int id, List<RecursiveBean> recursiveBeans) {}
}
