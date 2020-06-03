package by.lebedev.domain.repository

import by.lebedev.domain.entities.CoinRate


class CoinTempData private constructor() {
    var coinTicker: String = ""
    var coinTimeFrame = "m1"
    var coinChartName = ""
    var allCoinList = ArrayList<CoinRate>()

    companion object {
        val instance: CoinTempData by lazy { CoinTempData() }
    }
}