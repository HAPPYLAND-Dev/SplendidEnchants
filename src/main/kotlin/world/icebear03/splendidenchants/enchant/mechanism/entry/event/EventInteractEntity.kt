package world.icebear03.splendidenchants.enchant.mechanism.entry.event

import org.bukkit.entity.LivingEntity
import org.bukkit.event.player.PlayerInteractEntityEvent
import world.icebear03.splendidenchants.enchant.mechanism.entry.internal.*

//ONLY PLAYER
object EventInteractEntity : EventEntry<PlayerInteractEntityEvent>() {

    override fun modify(event: PlayerInteractEntityEvent, entity: LivingEntity, cmd: String, params: List<String>): Boolean {
        when (cmd) {
            "取消交互" -> event.isCancelled = true
            else -> {}
        }
        return true
    }

    override fun get(event: PlayerInteractEntityEvent, objName: String): Pair<ObjectEntry<*>, Any?> {
        return when (objName) {
            "主副手" -> objString.holderize(event.hand)
            "交互生物" -> (event.rightClicked as? LivingEntity)?.let { objLivingEntity.holderize(it) } ?: (objLivingEntity to null)
            "玩家" -> objPlayer.holderize(event.player)
            else -> objString.holderize(null)
        }
    }
}