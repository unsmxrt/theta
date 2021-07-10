package me.angles.theta.tasks

import me.angles.theta.commands.getCommands
import me.angles.theta.listeners.extractArguments
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent

class CommandDispatchTask(private val event: GuildMessageReceivedEvent, private var message: String, private var prefix: String) : Runnable {
    override fun run() {
        message = message.substring(prefix.length)
        for(command in getCommands()) {
            //if the message starts with a command *name* execute it without checking other aliases
            if(message.startsWith(command.name.lowercase()) && event.member?.hasPermission(command.requiredPermission) == true) {
                command.execute(event.message, message.extractArguments(prefix, command.name), prefix); return
            }
            //else loop through all command's aliases
            for(alias in command.aliases) {
                //if message starts with the alias, execute the command
                if(message.startsWith(alias.lowercase()) && event.member?.hasPermission(command.requiredPermission) == true) {
                    command.execute(event.message, message.extractArguments(prefix, command.name), prefix)
                    return
                }
            }
        }
    }
}