package by.lebedev.domain

import by.lebedev.domain.entities.Amd
import by.lebedev.domain.entities.Device
import by.lebedev.domain.transformators.RigDevicesCount
import by.lebedev.domain.usecase.GetCoinCapRatesUseCase
import org.junit.Assert
import org.junit.Test
import java.util.ArrayList

class RigDevicesCountTest {

    @Test
    fun testRealRequestComplete() {

        val list: ArrayList<Device> = arrayListOf(
            Amd(0, "RX 460", hashMapOf(
                Pair(" ", 0.0)),400,1),
            Amd(1, "RX 470",hashMapOf(
                Pair("", 0.0)), 700,2),
            Amd(2, "RX 480", hashMapOf(
                Pair("", 0.0)),830,3),
            Amd(3, "RX 550", hashMapOf(
                Pair("", 0.0)),400,0))

        val count = RigDevicesCount().execute(list)
        assert(count==6)

    }
}