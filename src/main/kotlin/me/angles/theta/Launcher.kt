package me.angles.theta

import me.angles.theta.commands.init
import me.angles.theta.config.getFileReader
import me.angles.theta.config.loadConfig
import me.angles.theta.listeners.MessageListener
import me.angles.theta.listeners.Watcher
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.requests.GatewayIntent
import net.dv8tion.jda.api.utils.ChunkingFilter
import net.dv8tion.jda.api.utils.MemberCachePolicy
import java.io.File
import kotlin.system.exitProcess

private var token = "no token"
fun main(args: Array<String>) {
    if(args.isEmpty()) {
        val reader = getFileReader(true, "token.txt")
        if(reader == null) {
            println("Must provide the bot's token as the first argument or put the token in ${System.getProperty("user.home") + File.separator + "Theta" + File.separator + "token.txt"}")
            exitProcess(1)
        } else {
            reader.use {
                val line = it.readLine()
                if(line == null) {
                    println("Must provide the bot's token as the first argument or put the token in ${System.getProperty("user.home") + File.separator + "Theta" + File.separator + "token.txt"}")
                    exitProcess(1)
                } else token = line
            }
        }
    } else token = args[0]
    val jda = JDABuilder.createDefault(token)
        .addEventListeners(MessageListener(), Watcher())
        .enableIntents(GatewayIntent.GUILD_MEMBERS)
        .setChunkingFilter(ChunkingFilter.ALL)
        .setMemberCachePolicy(MemberCachePolicy.ONLINE)
        .build()
    init()
    loadConfig()
    Runtime.getRuntime().addShutdownHook(hook)
}