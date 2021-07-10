package me.angles.theta.commands.impl

import me.angles.theta.commands.Command
import me.angles.theta.config.Action
import me.angles.theta.config.getProfileByID
import me.angles.theta.reply
import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.entities.Message
import java.util.concurrent.TimeUnit

class MaxMentions : Command("MassMentionFilter", "Manage mass mention filter (?)", "", Permission.MANAGE_SERVER, false, "mmf", "mentionfilter", "mfilter")
{
    override fun execute(message: Message, args: List<String>, prefix: String) {
        if(args.isEmpty()) {
            reply(message, "No arguments provided. \n\nUsage:\n${this.usage.replace("\\", prefix)}") { it.delete().queueAfter(5000, TimeUnit.MILLISECONDS) }
            return
        }
        when(args[0].lowercase()) {
            "enable" -> {
                getProfileByID(message.guild.idLong).checkMassMentions = true
                reply(message, "Enabled mass mention filter")
                return
            }
            "disable" -> {
                getProfileByID(message.guild.idLong).checkMassMentions = false
                reply(message, "Disabled mass mention filter")
                return
            }
            "max" -> {
                if(args.size > 1) {
                    if(!args[1].contains(Regex("[^0-9]"))) {
                        getProfileByID(message.guild.idLong).maxMentions = args[1].toInt()
                        reply(message, "Set max mentions amount to ${args[1]}")
                    } else reply(message, "Invalid amount provided.")
                }
            }
            "action" -> {
                if(args.size > 1) {
                    when(args[1].lowercase()) {
                        "kick" -> {
                            getProfileByID(message.guild.idLong).onMassMentionAction = Action.KICK
                            reply(message, "Success! Now the bot will kick for mass mentions.")
                        }
                        "ban" -> {
                            getProfileByID(message.guild.idLong).onMassMentionAction = Action.BAN
                            reply(message, "Success! Now the bot will ban for mass mentions.")
                        }
                    }
                }
            }
            "warn", "warnings" -> {
                if(args.size > 1) {
                    when(args[1].lowercase()) {
                        "true", "enable" -> {
                            getProfileByID(message.guild.idLong).warn = true
                            reply(message, "Success! Now the bot will warn members **once** before punishing for mass mentions.")
                        }
                        "false", "disable" -> {
                            getProfileByID(message.guild.idLong).warn = false
                            reply(message, "Success! Now the bot will instantly punish for mass mentioning.")
                        }
                    }
                }
            }
        }
    }

    init {
        this.usage = "\\mfilter enable/disable\n" +
                "\\mfilter max <amount of mentions allowed>\n" +
                "\\mfilter action <ban/kick>"
    }
}