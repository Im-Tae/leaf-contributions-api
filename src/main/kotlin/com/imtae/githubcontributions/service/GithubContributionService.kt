package com.imtae.githubcontributions.service

import com.imtae.githubcontributions.domain.Contribution

interface GithubContributionService  {

    fun getContributions(user: String) : Contribution
}