package com.imtae.githubcontributions.repository

import com.imtae.githubcontributions.domain.Contribution

interface ContributionParsingRepository {

    fun getContribution(user: String) : Contribution
}