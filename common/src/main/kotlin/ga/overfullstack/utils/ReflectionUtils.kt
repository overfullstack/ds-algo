package ga.overfullstack.utils

inline fun <reified T> instanceWithJavaReflection(): T =
  T::class.java.getConstructor().newInstance()

inline fun <reified T> instanceWithJavaReflectionFn(): () -> T = {
  T::class.java.getConstructor().newInstance()
}
