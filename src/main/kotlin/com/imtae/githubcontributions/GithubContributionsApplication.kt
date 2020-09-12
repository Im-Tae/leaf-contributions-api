package com.imtae.githubcontributions

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@EnableCaching
@SpringBootApplication
class GithubContributionsApplication

fun main(args: Array<String>) {
	runApplication<GithubContributionsApplication>(*args)
}
