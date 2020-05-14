package by.lebedev.domain.repo


class CoinTempData private constructor() {
    var coin: String = ""
    var coinTimeFrame = "m1"
    var coinChartName = ""

    companion object {
        val instance: CoinTempData by lazy { CoinTempData() }
    }
}