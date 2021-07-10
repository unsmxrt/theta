package me.angles.theta.config

//basically config class
data class GuildProfile(
    var prefix: String = ">",
    var checkOnJoin: Boolean = true,
    var joinCheckFailedAction: Action = Action.KICK,
    var checkMassMentions: Boolean = true,
    var warn: Boolean = true,
    var onMassMentionAction: Action = Action.BAN,
    var accountAgeLimit: Int = 3,
    var maxMentions: Int = 1,
)

enum class Action { KICK, BAN }
