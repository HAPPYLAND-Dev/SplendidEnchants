package world.icebear03.splendidenchants.enchant.mechanism.entry.`object`

import world.icebear03.splendidenchants.enchant.mechanism.entry.internal.*

object ObjectList : ObjectEntry<Pair<ObjectEntry<*>, List<String>>>() {

    override fun holderize(obj: Pair<ObjectEntry<*>, List<String>>) = this to when (obj.first) {
        objBlock -> "block"
        objEntity -> "entity"
        objItem -> "item"
        objLivingEntity -> "living_entity"
        objPlayer -> "player"
        objVector -> "vector"
        else -> "string"
    } + ":[" + obj.second.joinToString(",") + "]"

    override fun disholderize(holder: String): Pair<ObjectEntry<*>, List<String>> {
        val parts = holder.split(":")
        val objEntry = when (parts[0]) {
            "block" -> objBlock
            "entity" -> objEntity
            "item" -> objItem
            "living_entity" -> objLivingEntity
            "player" -> objPlayer
            "vector" -> objVector
            else -> objString
        }
        return objEntry to parts[1].replace("[", "").replace("]", "").split(",")
    }
}