package by.lebedev.domain.entities

interface Device {
    val id: Int
    val name: String
    val algos: HashMap<String, Double>
    val hashrate:Int
    var count:Int
}