package by.lebedev.domain.entities

data class Coin (
    val id: Int,
    val name: String,
    val ticker: String,
    val logo: String,
    val algo_id: Int,
    val cryptunit_color: String
)