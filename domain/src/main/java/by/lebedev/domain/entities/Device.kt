package by.lebedev.domain.entities

interface Device {
    val id: Int
    val name: String
    val hashrate: Int
    var count:Int

}