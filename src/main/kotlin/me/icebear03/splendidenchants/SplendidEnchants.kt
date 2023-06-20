package me.icebear03.splendidenchants

import taboolib.common.platform.Plugin
import taboolib.common.platform.function.info
import taboolib.common.platform.function.releaseResourceFile
import taboolib.module.kether.compileToJexl

object SplendidEnchants : Plugin() {

    override fun onEnable() {
        info("Loading SplendidEnchants...")
        releaseResourceFile("test.yml")
        println("3+7-a*9".compileToJexl().eval(mapOf("a" to 6))) // 注意需提前缓存好，建议在加载附魔的时候就缓存好编译好的公式，因为编译有损耗
    }
}