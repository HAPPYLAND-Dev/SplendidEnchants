package world.icebear03.splendidenchants.enchant.mechanism.entry.`object`

import com.mcstarrysky.starrysky.function.deserializeItemStackFromBase64
import com.mcstarrysky.starrysky.function.serializeToBase64
import org.bukkit.inventory.ItemStack
import taboolib.library.reflex.Reflex.Companion.invokeMethod
import taboolib.module.nms.getKey
import world.icebear03.splendidenchants.api.calcToInt
import world.icebear03.splendidenchants.api.name
import world.icebear03.splendidenchants.enchant.mechanism.entry.internal.ObjectEntry
import world.icebear03.splendidenchants.enchant.mechanism.entry.internal.objLivingEntity
import world.icebear03.splendidenchants.enchant.mechanism.entry.internal.objString

object ObjectItem : ObjectEntry<ItemStack>() {

    override fun modify(
        obj: ItemStack,
        cmd: String,
        params: List<String>
    ): Boolean {
        when (cmd) {
            "修改名称" -> obj.name = params[0]
            "损耗耐久" -> obj.damage(params[0].calcToInt(), objLivingEntity.d(params[1])!!)  //考虑了耐久等附魔
        }
        return true
    }

    override fun get(from: ItemStack, objName: String): Pair<ObjectEntry<*>, Any?> {
        if (objName.startsWith("元数据:")) {
            return with(from.itemMeta) {
                when (objName.removePrefix("元数据:")) {
                    "模型数据" -> objString.h(customModelData)
                    "无法破坏" -> objString.h(isUnbreakable)
                    else -> runCatching {
                        objString.h(invokeMethod(objName))
                    }.getOrElse { objString.h(null) }
                }
            }
        }
       return when (objName) {
           "数量" -> objString.h(from.amount)
           "类型" -> objString.h(from.type)
           "最大堆叠数量" -> objString.h(from.maxStackSize)
           "翻译键" -> objString.h(from.getKey()) // 可以给发送信息用
           else -> runCatching {
               objString.h(from.invokeMethod(objName))
           }.getOrElse { objString.h(null) }
       }
    }

    override fun holderize(obj: ItemStack) = this to "物品=${obj.serializeToBase64()}"

    override fun disholderize(holder: String) = holder.replace("物品=", "").deserializeItemStackFromBase64()
}