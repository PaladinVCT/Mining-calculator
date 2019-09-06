package by.lebedev.domain.entities

data class Nvidia(
    override val id: Int,
    override val name: String,
    override val algos: HashMap<String, Double>,
    override var count: Int,
    override val hashrate: Int
) : Device