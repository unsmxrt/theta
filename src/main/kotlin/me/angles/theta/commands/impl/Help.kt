package me.angles.theta.commands.impl

import me.angles.theta.commands.Command
import me.angles.theta.commands.getCommands
import me.angles.theta.reply
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.MessageEmbed
import java.awt.Color
import java.lang.StringBuilder
import java.util.concurrent.TimeUnit


val color = Color(59, 229, 245).rgb
class Help : Command("Help", "Supid dumbass?!?!?!?!?", "figure it out bich", Permission.VIEW_CHANNEL)
{
    init {
        function = { message, args, prefix ->
            reply(message, "i need help too\nno help for you baii") { _ ->
                message.reply("ok here is help").mentionRepliedUser(false).queueAfter(2, TimeUnit.SECONDS) {
                    it.editMessage(buildEmbed(message, prefix)).queue()
                }
            }
        }
    }

    private fun buildEmbed(message: Message, prefix: String): MessageEmbed {
        val builder = EmbedBuilder()
        builder.setTitle("all commands")
        builder.setDescription(getEmbedString(prefix))
        builder.setFooter("novoline is my favorite client!", message.author.effectiveAvatarUrl)
        builder.setColor(color)
        return builder.build()
    }

    private fun getEmbedString(prefix: String): String {
        val builder = StringBuilder()
        getCommands().forEach {
           builder.append("**${it.name}**" + "\n" +
                   "*${it.description}*\n" +
                   "*Usage*: ${it.usage.replace("\\", prefix)} \n" +
                   "***Required permission: ${it.requiredPermission}***\n\n"
           )
        }
        return builder.toString()
    }

}