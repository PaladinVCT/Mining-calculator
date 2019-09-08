package by.lebedev.data.repository.entities.coincap

data class CoinCapInfoResponse (
    val status : Status,
    val data : ArrayList<CoinCap>
)