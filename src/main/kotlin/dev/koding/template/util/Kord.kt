package dev.koding.template.util

import com.kotlindiscord.kord.extensions.DiscordRelayedException
import com.kotlindiscord.kord.extensions.commands.CommandContext
import dev.kord.common.Color
import dev.kord.rest.builder.message.EmbedBuilder
import dev.kord.rest.builder.message.create.MessageCreateBuilder
import dev.kord.rest.builder.message.create.embed

/**
 * List of common colors for embeds.
 *
 * @author Koding
 */
@Suppress("unused")
object Colors {
    val SUCCESS = Color(0x4caf50)
    val ERROR = Color(0xf44336)
    val INFO = Color(0x2196f3)
}

/**
 * Create a new embed builder with a default color for
 * the given message. Provides consistent colors for
 * embeds.
 *
 * @param message The message to create the embed for.
 * @param builder The builder to use for the embed.
 */
suspend fun MessageCreateBuilder.feedback(message: String? = null, builder: suspend EmbedBuilder.() -> Unit = {}) {
    embed {
        color = Colors.INFO
        description = message
        builder()
    }
}

@Suppress("UnusedReceiverParameter", "unused")
fun CommandContext.error(message: String): Nothing = throw DiscordRelayedException(message)
