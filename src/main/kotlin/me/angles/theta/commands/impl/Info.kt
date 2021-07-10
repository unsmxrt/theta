package me.angles.theta.commands.impl

import me.angles.theta.commands.Command
import me.angles.theta.config.getProfileByID
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.entities.Message
import java.lang.StringBuilder

class Info : Command("Info", "sends things about da bot", "\\info", Permission.ADMINISTRATOR)
{
    init {
        function = { message, args, prefix ->
            sendInfo(message)
        }
    }

}

fun sendInfo(message: Message) {
    val builder = StringBuilder()
    val embed = EmbedBuilder()
    val profile = getProfileByID(message.guild.idLong)
    embed.setTitle("Info for ${message.guild.name}")
    builder.append("**Prefix:** ${profile.prefix}\n")
    if(profile.checkMassMentions) {
        builder.append("*Checks mentions*\n")
        builder.append("**Max mentions allowed**: ${profile.maxMentions}\n")
        builder.append("**On too many mentions in a message**: ${if(profile.warn) "warn, then " else ""}${profile.onMassMentionAction.toString().lowercase()}\n")
    }
    if(profile.checkOnJoin) {
        builder.append("*Checks new members*\n**Account age limit:** ${profile.accountAgeLimit} days\n")
        builder.append("**On join check fail**: ${profile.joinCheckFailedAction.toString().lowercase()}")
    }
    embed.setDescription(builder.toString())
    embed.setFooter("do not execute this command in a public channel", message.author.effectiveAvatarUrl)
    embed.setColor(color)
    message.reply(embed.build()).queue()
}