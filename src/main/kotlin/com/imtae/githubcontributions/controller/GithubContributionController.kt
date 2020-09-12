package com.imtae.githubcontributions.controller

import com.imtae.githubcontributions.service.GithubContributionService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/user"])
class GithubContributionController(private val githubContributionService: GithubContributionService) {

    @GetMapping(value = ["/{user}"])
    fun getContributions(@PathVariable("user") user: String) = githubContributionService.getContributions(user)
}