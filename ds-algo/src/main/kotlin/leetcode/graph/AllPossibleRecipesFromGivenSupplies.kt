package leetcode.graph

/* 02 Aug 2025 19:03 */

/**
 * [2115. Find All Possible Recipes from Given
 * Supplies](https://leetcode.com/problems/find-all-possible-recipes-from-given-supplies/)
 */
fun findAllRecipes(
  recipes: Array<String>,
  ingredients: List<List<String>>,
  supplies: Array<String>,
): List<String> {
  val recipeToIngredients = recipes.zip(ingredients).toMap()
  val visited = mutableSetOf<String>()
  // ! A recipe can be visited, but not preparable, so we need to maintain a separate list
  val preparable = mutableSetOf<String>()
  val suppliesSet = supplies.toSet()
  return recipeToIngredients.keys
    .asSequence()
    .filter { recipe ->
      when (recipe) {
        !in visited ->
          when {
            recipe.canBePrepared(recipeToIngredients, visited, preparable, suppliesSet) -> {
              preparable += recipe
              true
            }
            else -> false
          }
        else -> recipe in preparable
      }
    }
    .toList()
}

fun String.canBePrepared(
  recipeToIngredients: Map<String, List<String>>,
  visited: MutableSet<String>,
  preparable: MutableSet<String>,
  supplies: Set<String>,
  visitedInGroup: Set<String> = setOf(this),
): Boolean {
  visited += this
  return recipeToIngredients[this]?.all { ingredient ->
    when (ingredient) {
      in visitedInGroup -> false // Cycle detected
      in preparable -> true // Already confirmed as preparable
      in visited -> false
      else -> {
        when {
          ingredient.canBePrepared(
            recipeToIngredients,
            visited,
            preparable,
            supplies,
            visitedInGroup + ingredient,
          ) -> {
            preparable += ingredient
            true
          }
          else -> false
        }
      }
    }
  } ?: (this in supplies)
}

fun main() {
  println(
    findAllRecipes(
      arrayOf("ju", "fzjnm", "x", "e", "zpmcz", "h", "q"),
      listOf(
        listOf("d"),
        listOf("hveml", "f", "cpivl"),
        listOf("cpivl", "zpmcz", "h", "e", "fzjnm", "ju"),
        listOf("cpivl", "hveml", "zpmcz", "ju", "h"),
        listOf("h", "fzjnm", "e", "q", "x"),
        listOf("d", "hveml", "cpivl", "q", "zpmcz", "ju", "e", "x"),
        listOf("f", "hveml", "cpivl"),
      ),
      arrayOf("f", "hveml", "cpivl", "d"),
    )
  ) // Expected output: ["ju","fzjnm","q"]
}
