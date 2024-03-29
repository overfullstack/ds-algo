/* gakshintala created on 2/1/20 */
package challenges

fun noOfTopicsLikesAndDislikes(aLikes: BooleanArray, bLikes: BooleanArray) =
  aLikes.zip(bLikes) { a, b -> !a.xor(b) }.filter { it }.count()

fun main() {
  val aLikes = readln().map { it == '1' }.toBooleanArray()
  val bLikes = readln().map { it == '1' }.toBooleanArray()
  println(noOfTopicsLikesAndDislikes(aLikes, bLikes))
}
