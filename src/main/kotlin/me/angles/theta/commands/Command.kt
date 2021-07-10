package me.angles.theta.commands

import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.entities.Message

abstract class Command(val name: String, val description: String, var usage: String = "", val requiredPermission: Permission, val hidden: Boolean = false, vararg val aliases: String) {
    abstract fun execute(message: Message, args: List<String>, prefix: String)
}