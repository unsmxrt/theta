package me.angles.theta.commands.impl

import me.angles.theta.commands.Command
import me.angles.theta.config.Action
import me.angles.theta.config.getProfileByID
import me.angles.theta.reply
import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.entities.Message

class Test : Command("Test", "","Tests stuff.", Permission.ADMINISTRATOR)
{
    override fun execute(message: Message, args: List<String>, prefix: String) {
        val profile = getProfileByID(message.guild.idLong)
        if(args.isEmpty()) return
        when(args[0].lowercase()) {
            "warn" -> {
                profile.warn = false
                reply(message, "set to ${profile.warn}")
            }
            "action" -> {
                profile.onMassMentionAction = Action.KICK
            }
        }
    }

}