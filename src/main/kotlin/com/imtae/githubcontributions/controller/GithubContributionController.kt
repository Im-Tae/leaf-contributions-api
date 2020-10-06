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

    @GetMapping(value = ["user/{user}/{date}"])
    fun getContribution(@PathVariable("user") user: String, @PathVariable("date") date: String) = githubContributionService.getContribution(user, date)

    @GetMapping(value = ["user/{user}/today"])
    fun getTodayContribution(@PathVariable("user") user: String) =githubContributionService.getTodayContribution(user)
}