package me.angles.theta.listeners

import me.angles.theta.config.getProfileByID
import me.angles.theta.executorService
import me.angles.theta.tasks.EventDispatchTask
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class MessageListener : ListenerAdapter() {

    override fun onGuildMessageReceived(event: GuildMessageReceivedEvent) {
        val prefix = getProfileByID(event.guild.idLong).prefix
        val message = event.message.contentRaw.lowercase()
        if(message.startsWith(prefix))
            executorService.execute(EventDispatchTask(event, message, prefix))
    }
}

fun String.extractArguments(prefix: String, alias: String): List<String> {
    val sbs = this.replaceFirst(prefix + alias, "")
    if(sbs != "" && sbs != " ") {
        val split = sbs.split(" ")
        return split.minus(split[0])
    }
    return emptyList()
}