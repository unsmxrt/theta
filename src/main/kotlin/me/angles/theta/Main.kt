package me.angles.theta

import me.angles.theta.config.saveConfig
import net.dv8tion.jda.api.entities.Message
import java.util.concurrent.Executors

val executorService = Executors.newCachedThreadPool()

inline fun reply(message: Message, content: String, mention: Boolean = false, crossinline onSuccess: (Message) -> Unit = {}) {
    message.reply(content).mentionRepliedUser(mention).queue { onSuccess(it) }
}

object hook : Thread() {
    override fun run() {
        saveConfig()
    }
}