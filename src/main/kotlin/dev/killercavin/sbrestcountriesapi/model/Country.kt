package dev.killercavin.sbrestcountriesapi.model

data class Country(
    val name: Name? = null,
    val capital: List<String>? = emptyList(),
    val population: Long = 0,
    val region: String? = null,
    val subregion: String? = null,
    val flags: Flag? = null,
    val coatOfArms: CoatOfArms? = null,
    val currencies: Map<String, Currency>? = emptyMap(),
    val languages: Map<String, String>? = emptyMap(),
    val borders: List<String>? = emptyList(),
    val area: Double? = null,
    val timezones: List<String>? = emptyList()
) {
    fun getFirstCapital(): String {
        return capital?.firstOrNull() ?: "N/A"
    }

    fun getFormattedPopulation(): String {
        return "%,d".format(population)
    }
}

data class Name(
    val common: String? = null,
    val official: String? = null
)

data class Flag(
    val png: String? = null,
    val svg: String? = null,
    val alt: String? = null
)

data class CoatOfArms(
    val png: String? = null,
    val svg: String? = null
)

data class Currency(
    val name: String? = null,
    val symbol: String? = null
)
