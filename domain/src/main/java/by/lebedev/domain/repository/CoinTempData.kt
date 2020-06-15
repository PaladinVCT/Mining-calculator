package by.lebedev.domain.repository

import by.lebedev.domain.entities.CoinGeckoCoin


class CoinTempData private constructor() {
    var coinTicker: String = ""
    var coinTimeFrame = "m1"
    var coinChartName = ""
    var allGeckoCoinList = ArrayList<CoinGeckoCoin>()

    companion object {
        val instance: CoinTempData by lazy { CoinTempData() }
    }
}