package com.imtae.githubcontributions.service

import com.imtae.githubcontributions.domain.Contribution
import com.imtae.githubcontributions.repository.ContributionParsingRepository
import org.springframework.stereotype.Component

@Component
class GithubContributionServiceImpl(private val contributionParsingRepository: ContributionParsingRepository) : GithubContributionService {

    override fun getContributions(user: String): Contribution = contributionParsingRepository.getContribution(user)
}