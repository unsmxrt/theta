package me.angles.theta.commands

import me.angles.theta.commands.impl.*
import java.util.*

private val commands = LinkedList<Command>()

fun getCommands(): List<Command> = commands

fun init() = commands.addAll(
    listOf(
        MaxMentions(), JoinFilter(), Prefix(), Help(), Info(), MassKick(), Ping()
    )
)


