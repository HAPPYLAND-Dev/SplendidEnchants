package world.icebear03.splendidenchants.enchant.data

import taboolib.common.platform.function.console
import taboolib.module.configuration.Configuration
import world.icebear03.splendidenchants.Config
import world.icebear03.splendidenchants.enchant.EnchantLoader
import world.icebear03.splendidenchants.util.loadAndUpdate
import java.util.concurrent.ConcurrentHashMap

data class Rarity(
    val id: String,
    val name: String,
    val color: String,
    val weight: Int,
    val skull: String
) {
    init {
        EnchantLoader.enchantsByRarity[this] = mutableSetOf()
    }

    override fun toString(): String {
        return name
    }

    companion object {

        val rarities = ConcurrentHashMap<String, Rarity>()

        lateinit var defaultRarity: Rarity

        fun initialize() {
            rarities.clear()

            val rarityConfig = Configuration.loadAndUpdate("enchants/rarity.yml")
            rarityConfig.getKeys(false).forEach {
                rarities[it] = Rarity(
                    it,
                    rarityConfig.getString("$it.name")!!,
                    rarityConfig.getString("$it.color")!!,
                    rarityConfig.getInt("$it.weight"),
                    rarityConfig.getString("$it.skull", "")!!
                )
            }
            defaultRarity = rarities[Config.config.getString("default_rarity", "common")]!!

            console().sendMessage("    Successfully load §6${rarities.size} rarities")
        }

        fun fromIdOrName(idOrName: String): Rarity {
            return rarities[idOrName] ?: rarities.values.firstOrNull { it.name == idOrName } ?: defaultRarity
        }
    }
}