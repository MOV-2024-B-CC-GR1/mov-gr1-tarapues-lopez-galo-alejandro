package org.example.storage

import java.io.File

class FileManager {

    fun <T> writeToFile(fileName: String, data: List<T>) {
        File(fileName).writeText(data.joinToString("\n") { it.toString() })
    }
}
