package dev.killercavin.sbrestcountriesapi.controller

import dev.killercavin.sbrestcountriesapi.model.Country
import dev.killercavin.sbrestcountriesapi.service.CountryService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import kotlin.collections.emptyList

@Controller
class CountryController(private val countryService: CountryService) {

    @GetMapping("/")
    fun home(
        @RequestParam(value = "search", required = false) search: String?,
        @RequestParam(value = "region", required = false) region: String?,
        model: Model
    ): String {

        try {
            val countries = when {
                !search.isNullOrEmpty() -> countryService.getCountryByName(search)
                !region.isNullOrEmpty() -> countryService.getCountriesByRegion(region)
                else -> countryService.getAllCountries()
            }

            model.addAttribute("countries", countries.collectList().block() ?: emptyList<Country>())

        } catch (e: Exception) {
            model.addAttribute("error", "Failed to fetch countries: ${e.message}")
            model.addAttribute("countries", emptyList<Country>())
        }

        return "index"
    }
}