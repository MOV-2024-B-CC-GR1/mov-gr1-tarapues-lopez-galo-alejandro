package org.example.repository

import org.example.model.Recinto
import org.example.storage.FileManager

/**
 * Clase encargada de gestionar la lógica de almacenamiento y operaciones con los hábitats (Recintos).
 * Utiliza un administrador de archivos (FileManager) para persistencia.
 */
class RecintoRepository(private val fileManager: FileManager) {

    // Lista en memoria para almacenar los recintos (hábitats).
    private val recintos = mutableListOf<Recinto>()

    /**
     * Agrega un nuevo hábitat a la lista de recintos y persiste los cambios en el archivo.
     *
     * @param recinto Objeto Recinto que se agregará a la lista.
     */
    fun addRecinto(recinto: Recinto) {
        recintos.add(recinto) // Agrega el hábitat a la lista en memoria.
        saveToFile() // Guarda la lista actualizada en el archivo.
    }

    /**
     * Devuelve una lista completa de los recintos almacenados en memoria.
     *
     * @return Lista de recintos actuales.
     */
    fun listRecintos(): List<Recinto> = recintos

    /**
     * Elimina un hábitat de la lista por su ID y persiste los cambios si fue exitoso.
     *
     * @param id ID del hábitat a eliminar.
     * @return Devuelve `true` si el hábitat fue eliminado, de lo contrario `false`.
     */
    fun removeRecinto(id: Int): Boolean {
        val removed = recintos.removeIf { it.id == id } // Intenta eliminar el hábitat con el ID especificado.
        if (removed) saveToFile() // Guarda los cambios si la eliminación fue exitosa.
        return removed
    }

    /**
     * Comprueba si un hábitat con el ID proporcionado ya existe en la lista.
     *
     * @param id ID del hábitat a buscar.
     * @return Devuelve `true` si el ID existe en la lista, de lo contrario `false`.
     */
    fun existsRecinto(id: Int): Boolean {
        return recintos.any { it.id == id } // Busca si el ID ya existe en la lista de recintos.
    }

    /**
     * Guarda la lista actual de recintos en un archivo utilizando el FileManager.
     * Se persiste en el archivo `recintos.txt`.
     */
    private fun saveToFile() {
        fileManager.writeToFile("recintos.txt", recintos) // Llama al método del administrador de archivos para guardar.
    }
}
