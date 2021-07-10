package me.angles.theta.config

//profiles map (each entry contains a guild id and a guild profile bound to it)
val profiles = HashMap<Long, GuildProfile>()

//checks if the profile for specified guild is available, if so, returns it, and if not, creates a new one and saves it.
fun getProfileByID(guildID: Long): GuildProfile {
    if(profiles.containsKey(guildID)) {
        return profiles[guildID]!!
    }
    val profile = GuildProfile()
    profiles[guildID] = profile
    return profile
}