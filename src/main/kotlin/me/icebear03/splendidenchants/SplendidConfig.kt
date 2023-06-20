package me.icebear03.splendidenchants

import taboolib.module.configuration.Config
import taboolib.module.configuration.ConfigNode
import taboolib.module.configuration.Configuration

/**
 * 主配置文件, 也就是 config.yml, 提供一些最基本最重要的配置.
 */
object SplendidConfig {

    @Config
    lateinit var config: Configuration
        private set

    @ConfigNode("use_mini_message")
    var useMiniMessage: Boolean = false
}