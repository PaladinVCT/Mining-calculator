package by.lebedev.domain.entities

class Amd(
    override val id: Int,
    override val name: String,
    override val algos: HashMap<String, Double>,
    override val hashrate: Int,
    override var count: Int
) : Device