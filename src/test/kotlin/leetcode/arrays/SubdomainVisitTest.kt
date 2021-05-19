package leetcode.arrays

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder

class SubdomainVisitTest : StringSpec({
    "Subdomain visit" {
        forAll(
            row(
                arrayOf("9001 discuss.leetcode.com"),
                listOf("9001 discuss.leetcode.com", "9001 leetcode.com", "9001 com")
            ),
            row(
                arrayOf("900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"),
                listOf(
                    "901 mail.com",
                    "50 yahoo.com",
                    "900 google.mail.com",
                    "5 wiki.org",
                    "5 org",
                    "1 intel.mail.com",
                    "951 com"
                )
            )
        ) { cpdomains, result ->
            subdomainVisits(cpdomains) shouldContainExactlyInAnyOrder result
        }
    }
})
