package me.angles.theta.commands.impl

import me.angles.theta.commands.Command
import me.angles.theta.reply
import net.dv8tion.jda.api.Permission

class Ping : Command("Ping", "Pingery", "\\ping", Permission.VIEW_CHANNEL) {
    init {
        function = { message, args, prefix ->
            val time = System.currentTimeMillis()
            reply(message, "Hi") {
                it.editMessage("Pong! ``${System.currentTimeMillis() - time}ms``").queue()
            }
        }
    }
}