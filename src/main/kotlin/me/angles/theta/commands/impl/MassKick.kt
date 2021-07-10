package me.angles.theta.commands.impl

import me.angles.theta.commands.Command
import me.angles.theta.reply
import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.entities.Message

class MassKick : Command("MassKick", "Uniquie command", "masskick", Permission.ADMINISTRATOR) {
    init {
        function = { message, args, prefix ->
            var msg: Message? = null
            var counter = 0
            val toKick = message.guild.members.filter { it.roles.isEmpty() }
            reply(message, "Found ${toKick.size} members with no roles.\n${counter}/${toKick.size}") {
                msg = it
            }
            toKick.forEach {
                it.kick().reason("Mass kick requested by ${message.author.asTag}").queue {
                    counter++
                    if(counter % 10 == 0) {
                        msg?.editMessage("Found ${toKick.size} members with no roles.\n" +
                                "${counter}/${toKick.size}")?.queue()
                    } else if (counter == toKick.size) {
                        msg?.editMessage("Found ${toKick.size} members with no roles.\n" +
                                "Finished!")?.queue()
                    }
                }
            }
        }
    }
}