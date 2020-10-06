package com.imtae.githubcontributions.repository

import com.imtae.githubcontributions.domain.Contribution

interface ContributionParsingRepository {

    fun getContributions(user: String) : Contribution

    fun getContribution(user: String, date: String) : Any

    fun getTodayContribution(user: String) : Any
}