package me.angles.theta.commands.impl

import me.angles.theta.commands.Command
import me.angles.theta.config.getProfileByID
import me.angles.theta.reply
import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.entities.Message

class Prefix : Command("Prefix", "#botsrights", "\\prefix <prefix no space support!!!?!>", Permission.MANAGE_SERVER) {
    override fun execute(message: Message, args: List<String>, prefix: String) {
        if(args.isEmpty()) {
            reply(message, "provide the prefix dum bass")
            return
        }
        getProfileByID(message.guild.idLong).let {
            it.prefix = args[0]
            if(!it.prefix.contains("`"))
            reply(message, "set prefix to ${it.prefix}")
            else reply(message, "changed prefix")
        }
    }
}