package com.imtae.githubcontributions.controller

import com.imtae.githubcontributions.service.GithubContributionService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/"])
class GithubContributionController(private val githubContributionService: GithubContributionService) {

    @GetMapping(value = ["", "user"])
    fun getDefault() = "/user/{github name}"

    @GetMapping(value = ["user/{user}"])
    fun getContributions(@PathVariable("user") user: String) = githubContributionService.getContributions(user)
}