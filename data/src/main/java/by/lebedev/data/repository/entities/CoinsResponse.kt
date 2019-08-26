package by.lebedev.data.repository.entities

data class CoinsResponse(

    val id: Int,
    val name: String,
    val ticker: String,
    val logo: String,
    val algo_id: Int,
    val cryptunit_color: String
)