package world.icebear03.splendidenchants.api

import org.bukkit.entity.Player
import taboolib.common5.format
import world.icebear03.splendidenchants.player.cooldown

fun Player.addCd(key: String) {
    cooldown[key] = System.currentTimeMillis()
}

fun Player.removeCd(key: String) {
    cooldown.remove(key)
}

fun Player.clearCd() {
    cooldown.clear()
}

//pair#first 冷却是否结束，冷却中为false
//pair#second 冷却若未结束，离结束还剩下的时间（秒）
fun Player.checkCd(key: String, cd: Double): Pair<Boolean, Double> {
    if (!cooldown.containsKey(key))
        return true to 0.0
    val tmp = (cd - (System.currentTimeMillis() - cooldown[key]!!) / 1000.0).format(1)
    return if (tmp <= 0.0) true to -1.0 else false to maxOf(tmp, 0.0)
}