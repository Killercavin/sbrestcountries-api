package dev.killercavin.sbrestcountriesapi.config

import org.springframework.context.annotation.*
import org.springframework.web.reactive.function.client.*

@Configuration
class WebClientConfig {
    @Bean
    fun webClient(): WebClient {
        return WebClient.builder()
            .baseUrl("https://restcountries.com/v3.1")
            .build()
    }
}