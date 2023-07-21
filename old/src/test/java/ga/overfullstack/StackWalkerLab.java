package ga.overfullstack;

import static java.lang.StackWalker.Option.RETAIN_CLASS_REFERENCE;
import static org.assertj.core.api.Assertions.assertThat;

import java.lang.StackWalker.StackFrame;
import java.lang.annotation.Annotation;
import org.junit.jupiter.api.Test;

class StackWalkerLab {
  private static final StackWalker STACK_WALKER = StackWalker.getInstance(RETAIN_CLASS_REFERENCE);

  static Runnable runnable = () -> System.out.println(STACK_WALKER.getCallerClass());

  @Test
  void stackWalkerFromLambda() {
    runnable.run();
  }

  @Test
  void stackWalkForMethodWithAnnotation() {
    assertThat(findFirstMethodWithAnnotationFromStackTrace(Test.class))
        .isEqualTo("stackWalkForMethodWithAnnotation");
  }

  public static <T extends Annotation> String findFirstMethodWithAnnotationFromStackTrace(
      Class<T> type) {
    return STACK_WALKER
        .walk(
            stackFrames ->
                stackFrames
                    .filter(stackFrame -> doesMethodHasAnnotation(type, stackFrame))
                    .findFirst()
                    .map(StackFrame::getMethodName))
        .orElseThrow(() -> new IllegalCallerException("No method found with @Test annotation"));
  }

  private static <T extends Annotation> boolean doesMethodHasAnnotation(
      Class<T> annotationClass, StackFrame stackFrame) {
    try {
      return stackFrame
              .getDeclaringClass()
              .getDeclaredMethod(
                  stackFrame.getMethodName(), stackFrame.getMethodType().parameterArray())
              .getAnnotation(annotationClass)
          != null;
    } catch (NoSuchMethodException e) {
      return false;
    }
  }
}
