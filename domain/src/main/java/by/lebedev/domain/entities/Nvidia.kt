package by.lebedev.domain.entities

class Nvidia(
    override val id: Int,
    override val name: String,
    val algos: HashMap<String, Double>,
    override var count: Int
) : Device {

}
