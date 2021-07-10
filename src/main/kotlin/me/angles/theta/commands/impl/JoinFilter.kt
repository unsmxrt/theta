package me.angles.theta.commands.impl

import me.angles.theta.commands.Command
import me.angles.theta.config.Action
import me.angles.theta.config.getProfileByID
import me.angles.theta.config.saveConfig
import me.angles.theta.reply
import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.entities.Message
import java.util.concurrent.TimeUnit

class JoinFilter : Command("JoinFilter", "manage the thing that checks members when they join", "", Permission.MANAGE_SERVER, false, "jfilter", "jf")
{
    override fun execute(message: Message, args: List<String>, prefix: String) {
        if(args.isEmpty()) {
            reply(message, "No arguments provided. \n\nUsage:\n${this.usage.replace("\\", prefix)}") { it.delete().queueAfter(5000, TimeUnit.MILLISECONDS) }
            return
        }
        val profile = getProfileByID(message.guild.idLong)
        when (args[0].lowercase()) {
            "enable", "true" -> {
                profile.checkOnJoin = true
                reply(message, "Success! Now the bot will check all members that join the server.")
            }
            "disable", "false" -> {
                profile.checkOnJoin = false
                reply(message, "Success! Now the bot won't check members that join the server.")
            }
            "action" -> {
                if(args.size > 1) {
                    when(args[1].lowercase()) {
                        "ban" -> {
                            profile.joinCheckFailedAction = Action.BAN
                            reply(message, "Set action to ban")
                        }
                        "kick" -> {
                            profile.joinCheckFailedAction = Action.KICK
                            reply(message, "Set action to kick")
                        }
                    }
                }else {
                    reply(message, "Not enough arguments, see ${prefix}help for more information.")
                }
            }
            "age", "limit", "minage", "setlimit" -> {
                if(args.size > 1) {
                    if(args[1].contains(Regex("[^0-9]"))) {
                        reply(message, "invalid amount provided")
                        return
                    }
                    profile.accountAgeLimit = args[1].toInt()
                    reply(message, "Set min account age limit to ${profile.accountAgeLimit}")
                } else {
                    reply(message, "Not enough arguments, see ${prefix}help for more information.")
                }
            }
        }
    }

    init {
        this.usage = "\\jfilter <enable/disable>\n" +
                "\\jfilter minage <amount in days>\n" +
                "\\jfilter action <kick/ban>\n" +
                "\\jfilter age <min days>"
    }
}
