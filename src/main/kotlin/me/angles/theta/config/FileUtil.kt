package me.angles.theta.config

import java.io.*

private val separator = File.separator
private val path = System.getProperty("user.home") + separator + "Theta"

fun getFileWriter(deleteOld: Boolean, configFolder: Boolean, filePath: String): BufferedWriter? {
    try {
        if (configFolder) createConfigFolder()
        val file = if (configFolder) File(path + separator + filePath) else File(filePath)
        if(deleteOld && file.exists()) file.delete()
        file.createNewFile()
        return BufferedWriter(FileWriter(file))
    } catch(ex: Exception) {
        println("Failed to create BufferedWriter")
    }
    return null
}

fun getFileReader(configFolder: Boolean, filePath: String): BufferedReader? {
    try {
        val file = if(configFolder) File(path + separator + filePath) else File(filePath)
        if(!file.exists()) file.createNewFile()
        return BufferedReader(FileReader(file))
    } catch(ex: Exception) {
        println("Failed to create BufferedReader")
    }
    return null
}

fun createConfigFolder() {
    val folder = File(path)
    folder.parentFile.mkdirs()
    if (!folder.exists()) {
        folder.mkdirs()
    }
}