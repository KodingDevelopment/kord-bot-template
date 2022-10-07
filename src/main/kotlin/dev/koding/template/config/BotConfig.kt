package dev.koding.template.config

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

/**
 * Structure of the config file
 *
 * @author Koding
 */
@Serializable
data class BotConfig(
    val bot: Bot = Bot()
) {
    @Serializable
    data class Bot(
        val token: String = "INSERT_TOKEN_HERE",
        val guildId: String = "INSERT_GUILD_ID_HERE"
    )
}

/**
 * Global config object
 */
val config: BotConfig by lazy {
    val json = Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
        prettyPrint = true
    }

    val config = File("config.json")
        .also {
            if (!it.exists()) {
                it.createNewFile()
                it.writeText(json.encodeToString(BotConfig()))
            }
        }
        .readText()

    json.decodeFromString(config)
}
