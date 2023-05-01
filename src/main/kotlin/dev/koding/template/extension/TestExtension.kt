package dev.koding.template.extension

import com.kotlindiscord.kord.extensions.extensions.Extension
import com.kotlindiscord.kord.extensions.extensions.publicSlashCommand
import com.kotlindiscord.kord.extensions.types.respond

class TestExtension : Extension() {
    override val name = "test"

    override suspend fun setup() {
        publicSlashCommand {
            name = "test"
            description = "This is a test command!"

            action {
                respond {
                    content = "This is a test message, hello ${user.mention}!"
                }
            }
        }
    }
}
