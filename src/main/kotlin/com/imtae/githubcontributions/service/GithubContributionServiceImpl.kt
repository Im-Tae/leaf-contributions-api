package com.imtae.githubcontributions.service

import com.imtae.githubcontributions.domain.Contribution
import com.imtae.githubcontributions.repository.ContributionParsingRepository
import net.sf.ehcache.CacheManager
import net.sf.ehcache.Element
import org.springframework.stereotype.Component

@Component
class GithubContributionServiceImpl(private val contributionParsingRepository: ContributionParsingRepository) : GithubContributionService {

    private val cacheManager = CacheManager(javaClass.getResource("/ehcache.xml"))

    override fun getContributions(user: String): Contribution {

        val cache = cacheManager.getCache("userCache")

        return if (cache.get(user) != null)
            cache.get(user).objectValue as Contribution
        else {
            cacheManager.clearAll()

            val contribution = contributionParsingRepository.getContributions(user)

            cache.put(Element(user, contribution))

            contribution
        }
    }

    override fun getContribution(user: String, date: String): Any = contributionParsingRepository.getContribution(user, date)

    override fun getTodayContribution(user: String): Any = contributionParsingRepository.getTodayContribution(user)
}