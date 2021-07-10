package me.angles.theta.tasks

import net.dv8tion.jda.api.entities.Message

class CommandExecutionTask(private inline val function: (Message, List<String>, String) -> Unit, private val arg1: Message, private val arg2: List<String>, private val arg3: String) : Runnable
{
    override fun run() {
        function(arg1, arg2, arg3)
    }
}