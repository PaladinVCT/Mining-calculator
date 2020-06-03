package by.lebedev.domain.transformators

import by.lebedev.domain.entities.PoolsItem
import java.util.*
import kotlin.collections.ArrayList

class AllPoolsToCoinSpecificTransformator {

    fun execute(coinTicker: String, poolList: ArrayList<PoolsItem>): ArrayList<PoolsItem> {

        val coinSpecList = ArrayList<PoolsItem>()

        for (i in 0 until poolList.size) {

            if (poolList[i].coins.contains(coinTicker.toUpperCase(Locale.ROOT))) {
                coinSpecList.add(poolList[i])
            }
        }
        return coinSpecList
    }
}