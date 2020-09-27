package com.imtae.githubcontributions.repository

import com.imtae.githubcontributions.domain.Contribution
import com.imtae.githubcontributions.domain.Contributions

interface ContributionParsingRepository {

    fun getContributions(user: String) : Contribution

    fun getContribution(user: String, year: String) : Contributions

    fun getTodayContribution(user: String) : Contributions
}