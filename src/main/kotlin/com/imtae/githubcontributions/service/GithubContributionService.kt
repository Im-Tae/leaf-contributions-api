package com.imtae.githubcontributions.service

import com.imtae.githubcontributions.domain.Contribution

interface GithubContributionService  {

    fun getContributions(user: String) : Contribution

    fun getContribution(user: String, date: String) : Any

    fun getTodayContribution(user: String) : Any
}