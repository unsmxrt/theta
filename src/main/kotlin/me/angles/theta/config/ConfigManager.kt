package me.angles.theta.config

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

private val gson = GsonBuilder().setLenient().setPrettyPrinting().enableComplexMapKeySerialization().create()

fun saveConfig() {
    getFileWriter(true, true, "config.json")?.use { writer ->
        writer.write(gson.toJson(profiles))
    }
}

fun loadConfig() {
    getFileReader(true, "config.json")?.use { reader ->
       val typeToken = object : TypeToken<HashMap<Long, GuildProfile>>(){}.type
       val newMap: HashMap<Long, GuildProfile> = gson.fromJson(reader.readText(), typeToken)
       newMap.forEach { entry ->
           profiles[entry.key] = entry.value
       }
    }
}