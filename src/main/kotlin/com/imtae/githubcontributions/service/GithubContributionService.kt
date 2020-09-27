package com.imtae.githubcontributions.service

import com.imtae.githubcontributions.domain.Contribution
import com.imtae.githubcontributions.domain.Contributions

interface GithubContributionService  {

    fun getContributions(user: String) : Contribution

    fun getContribution(user: String, year: String) : Contributions

    fun getTodayContribution(user: String) : Contributions
}