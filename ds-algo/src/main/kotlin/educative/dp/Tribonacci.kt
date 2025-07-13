package educative.dp

fun tribonacci(n: Int): Int {
  if (n <= 3) {
    if (n == 0) 0 else 1
  }
  var first = 0
  var second = 1
  var third = 1
  var next = 0
  for (i in 3..n) {
    next = first + second + third
    first = second
    second = third
    third = next
  }
  return third
}
