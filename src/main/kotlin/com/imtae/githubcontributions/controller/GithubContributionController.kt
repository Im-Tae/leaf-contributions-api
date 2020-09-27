package com.imtae.githubcontributions.controller

import com.imtae.githubcontributions.service.GithubContributionService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/"])
class GithubContributionController(private val githubContributionService: GithubContributionService) {

    @GetMapping(value = ["", "user"])
    fun getDefault() = "/user/{github name}"

    @GetMapping(value = ["user/{user}"])
    fun getContributions(@PathVariable("user") user: String) = githubContributionService.getContributions(user)

    @GetMapping(value = ["user/{user}/{year}"])
    fun getContribution(@PathVariable("user") user: String, @PathVariable("year") year: String) = githubContributionService.getContribution(user, year)

    @GetMapping(value = ["user/{user}/today"])
    fun getTodayContribution(@PathVariable("user") user: String) =githubContributionService.getTodayContribution(user)
}