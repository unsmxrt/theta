package me.angles.theta.commands

import me.angles.theta.executorService
import me.angles.theta.tasks.CommandExecutionTask
import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.entities.Message

open class Command(val name: String, val description: String, var usage: String = "", val requiredPermission: Permission, val hidden: Boolean = false, vararg val aliases: String,
    inline var function: (message: Message, args: List<String>, prefix: String) -> Unit = {_, _, _ ->})
{
    fun run(message: Message, args: List<String>, prefix: String) {
        executorService.execute(CommandExecutionTask(function, message, args, prefix))
    }


}