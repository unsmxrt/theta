package me.angles.theta.listeners

import me.angles.theta.config.getProfileByID
import me.angles.theta.executorService
import me.angles.theta.tasks.MemberCheckTask
import me.angles.theta.tasks.MentionCheckTask
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class Watcher : ListenerAdapter() {

    override fun onGuildMessageReceived(event: GuildMessageReceivedEvent)  {
        if(event.author.isBot || !getProfileByID(event.guild.idLong).checkMassMentions) return
        executorService.execute(MentionCheckTask(event))
    }

    override fun onGuildMemberJoin(event: GuildMemberJoinEvent) {
        if(getProfileByID(event.guild.idLong).checkOnJoin) {
            executorService.execute(MemberCheckTask(event))
        }
    }

}