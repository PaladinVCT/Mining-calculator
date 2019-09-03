package by.lebedev.domain.entities

class Amd(override val id: Int, override val name: String, val hashrate: Int, override var count: Int) : Device {

}
