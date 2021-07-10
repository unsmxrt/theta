package me.angles.theta.tasks

import me.angles.theta.config.Action
import me.angles.theta.config.getProfileByID
import net.dv8tion.jda.api.entities.User
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent

private val warned = ArrayList<User>() //list of users that have received warnings. gets emptied on bot restart.

class MentionCheckTask (private val event: GuildMessageReceivedEvent) : Runnable {
    override fun run() {
        val profile = getProfileByID(event.guild.idLong)
        if (event.message.mentionedUsers.size >= profile.maxMentions) {
            if (warned.contains(event.author) || !profile.warn) {
                when (profile.onMassMentionAction) {
                    Action.BAN -> event.member?.ban(0, "Mass Mentioning")?.queue()
                    Action.KICK -> event.member?.kick("Mass Mentioning")?.queue()
                }
            } else event.author.warn(event.guild.name, "Mass Mentioning")
        }
    }
}

fun User.warn(guildName: String, reason: String) {
    this.openPrivateChannel().queue({
        it.sendMessage("You've received a warning in ``$guildName`` for: ``${reason}``\n\n**Next violation will result in a kick/ban from the server!**\n\nIf this is a false positive, contact angles#2018.").queue()
    }) { println("Couldn't DM user ${this.asTag}")}
    warned.add(this)
}