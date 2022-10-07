package dev.koding.template

import com.kotlindiscord.kord.extensions.ExtensibleBot
import dev.koding.template.config.config
import dev.koding.template.util.Colors
import dev.koding.template.util.feedback
import mu.KotlinLogging

private val logger = KotlinLogging.logger { }

lateinit var bot: ExtensibleBot

suspend fun main() {
    // Warn for missing config
    if (config.bot.token == "INSERT_TOKEN_HERE") {
        return logger.error { "Please configure the bot before running it. A default config has been created." }
    }

    // Create the bot
    bot = ExtensibleBot(config.bot.token) {
        applicationCommands {
            enabled = true
            defaultGuild(config.bot.guildId)
        }

        errorResponse { message, type ->
            feedback(type.error.message ?: message) {
                color = Colors.ERROR
            }
        }
    }

    bot.start()
}
