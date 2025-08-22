package dev.killercavin.sbrestcountriesapi.service

import dev.killercavin.sbrestcountriesapi.model.Country
import org.springframework.http.HttpStatus
import org.springframework.stereotype.*
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.*

@Service
class CountryService(private val webClient: WebClient) {

    private val fields = listOf(
        "name", "capital", "population", "region", "subregion", "flags"
    ).joinToString(",")

    fun getAllCountries(): Flux<Country> {
        return webClient.get()
            .uri("/all?fields=${fields}")
            .retrieve()
            .onStatus({ it.isError }, { response ->
                Mono.error(RuntimeException("Failed to fetch countries: ${response.statusCode()}"))
            })
            .bodyToFlux(Country::class.java)
    }

    fun getCountryByName(name: String): Flux<Country> {
        return webClient.get()
            .uri("/name/{name}?fields=${fields}", name)
            .retrieve()
            .onStatus({ it == HttpStatus.NOT_FOUND }, {
                Mono.empty() // Return empty flux for 404
            })
            .onStatus({ it.isError }, { response ->
                Mono.error(RuntimeException("Failed to fetch country: ${response.statusCode()}"))
            })
            .bodyToFlux(Country::class.java)
    }

    fun getCountriesByRegion(region: String): Flux<Country> {
        return webClient.get()
            .uri("/region/{region}?fields=${fields}", region)
            .retrieve()
            .onStatus({ it.isError }, { response ->
                Mono.error(RuntimeException("Failed to fetch countries by region: ${response.statusCode()}"))
            })
            .bodyToFlux(Country::class.java)
    }
}