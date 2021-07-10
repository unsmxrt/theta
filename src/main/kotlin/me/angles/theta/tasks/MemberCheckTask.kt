package me.angles.theta.tasks

import me.angles.theta.config.Action
import me.angles.theta.config.GuildProfile
import me.angles.theta.config.getProfileByID
import net.dv8tion.jda.api.entities.User
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent
import java.time.Instant
import java.time.temporal.ChronoUnit

class MemberCheckTask(private val event: GuildMemberJoinEvent) : Runnable {
    override fun run() {
        val profile = getProfileByID(event.guild.idLong)
            if(checkAccountDate(profile, event.user)) {
                when (profile.joinCheckFailedAction) {
                    Action.KICK -> event.member.kick("Sus acc").queue()
                    Action.BAN -> event.member.ban(0, "Sus acc").queue()
                }
            }
    }
}

private fun checkAccountDate(profile: GuildProfile, user: User): Boolean {
    val daysSinceCreated = ChronoUnit.DAYS.between(user.timeCreated.toInstant(), Instant.now())
    if (daysSinceCreated < profile.accountAgeLimit) return true
    return false
}