package world.icebear03.splendidenchants.enchant.mechanism.chain

enum class ChainType(val display: String) {
    COOLDOWN("冷却"),
    CONDITION("条件"),
    ASSIGNMENT("赋值"),
    EVENT("事件"),
    OPERATION("操作");

    companion object {
        fun getType(identifier: String?): ChainType? = ChainType.entries.find { it.display == identifier || it.name == identifier }
    }
}