package world.icebear03.splendidenchants.enchant.mechanism.entry.event

import org.bukkit.entity.LivingEntity
import org.bukkit.event.player.PlayerChatEvent
import world.icebear03.splendidenchants.enchant.mechanism.entry.internal.EventEntry
import world.icebear03.splendidenchants.enchant.mechanism.entry.internal.ObjectEntry
import world.icebear03.splendidenchants.enchant.mechanism.entry.internal.objPlayer
import world.icebear03.splendidenchants.enchant.mechanism.entry.internal.objString

object EventChat : EventEntry<PlayerChatEvent>() {

    override fun modify(event: PlayerChatEvent, entity: LivingEntity, cmd: String, params: List<String>): Boolean {
        when (cmd) {
            "取消发送" -> event.isCancelled = true
            "设置信息" -> event.message = params.firstOrNull() ?: ""
            else -> {}
        }
        return true
    }

    override fun get(event: PlayerChatEvent, objName: String): Pair<ObjectEntry<*>, Any?> {
        return when (objName) {
            "消息" -> objString.h(event.message)
            "发送者" -> objPlayer.h(event.player)
            else -> objString.h(null)
        }
    }
}
