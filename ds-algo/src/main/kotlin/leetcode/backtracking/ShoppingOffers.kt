package leetcode.backtracking

/* 09 Aug 2025 12:49 */
/** [638. Shopping Offers](https://leetcode.com/problems/shopping-offers/) */
fun shoppingOffers(
  price: List<Int>,
  offers: List<List<Int>>,
  needs: List<Int>,
  memo: MutableMap<List<Int>, Int> = mutableMapOf(),
): Int {
  memo[needs]?.let {
    return it
  }
  if (needs.all { it == 0 }) {
    return 0
  }
  val totalPriceWithoutOffers = price.zip(needs).sumOf { it.first * it.second }
  val priceWithOffers =
    offers
      .asSequence()
      // ! last element is the price of the offer
      .map { offer -> offer.last() to needs.zip(offer.dropLast(1)).map { it.first - it.second } }
      .filter { (_, remainingNeeds) -> remainingNeeds.all { it >= 0 } }
      .minOfOrNull { (priceOfOffer, remainingNeeds) -> // ! Backtrack to remainingNeeds
        // ! offers can be reused multiple times
        priceOfOffer + shoppingOffers(price, offers, remainingNeeds, memo)
      } ?: totalPriceWithoutOffers
  return minOf(totalPriceWithoutOffers, priceWithOffers).also { memo[needs] = it }
}

fun main() {
  println(shoppingOffers(listOf(2, 5), listOf(listOf(3, 0, 5), listOf(1, 2, 10)), listOf(3, 2)))
  println(
    shoppingOffers(listOf(2, 3, 4), listOf(listOf(1, 1, 0, 4), listOf(2, 2, 1, 9)), listOf(1, 2, 1))
  )
  println(
    shoppingOffers(
      listOf(2, 2),
      listOf(
        listOf(1, 1, 1),
        listOf(1, 1, 2),
        listOf(1, 1, 3),
        listOf(1, 1, 4),
        listOf(1, 1, 5),
        listOf(1, 1, 6),
        listOf(1, 1, 7),
        listOf(1, 1, 8),
        listOf(1, 1, 9),
        listOf(1, 1, 10),
        listOf(1, 1, 11),
        listOf(1, 1, 12),
        listOf(1, 1, 13),
        listOf(1, 1, 14),
        listOf(1, 1, 15),
      ),
      listOf(10, 10),
    )
  )
}
