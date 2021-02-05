package leetcode.arrays

/**
 * https://leetcode.com/problems/subdomain-visit-count/
 */
fun subdomainVisits(cpdomains: Array<String>): List<String> {
    val map = mutableMapOf<String, Int>()
    for(cpdomain in cpdomains) {
        val (freqStr, domain) = cpdomain.split(' ')
        val freq = freqStr.toInt()
        map.merge(domain, freq, Int::plus)
        for ((index, char) in domain.withIndex()) {
            if (char == '.') {
                val subdomain = domain.substring(index + 1)
                map.merge(subdomain, freq, Int::plus)
            }
        }
    }
    return map.entries.map { "${it.value} ${it.key}" }
}