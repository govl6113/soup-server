package com.github.soup

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class SoupServerApplication

fun main(args: Array<String>) {
	runApplication<SoupServerApplication>(*args)
}
