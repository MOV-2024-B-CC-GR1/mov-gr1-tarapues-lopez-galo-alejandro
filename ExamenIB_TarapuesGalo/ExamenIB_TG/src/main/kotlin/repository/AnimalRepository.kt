package org.example.repository

import org.example.model.Animal
import org.example.storage.FileManager

/**
 * Clase encargada de gestionar la lógica de almacenamiento y operaciones con los animales.
 * Utiliza un administrador de archivos (FileManager) para persistencia.
 */
class AnimalRepository(private val fileManager: FileManager) {

    // Lista en memoria para almacenar los animales.
    private val animals = mutableListOf<Animal>()

    /**
     * Agrega un nuevo animal a la lista de animales y persiste los cambios en el archivo.
     *
     * @param animal Objeto Animal que se agregará a la lista.
     */
    fun addAnimal(animal: Animal) {
        animals.add(animal) // Agrega el animal a la lista en memoria.
        saveToFile() // Guarda la lista actualizada en el archivo.
    }

    /**
     * Devuelve una lista completa de los animales almacenados en memoria.
     *
     * @return Lista de animales actuales.
     */
    fun listAnimals(): List<Animal> = animals

    /**
     * Elimina un animal de la lista por su ID y persiste los cambios si fue exitoso.
     *
     * @param id ID del animal a eliminar.
     * @return Devuelve `true` si el animal fue eliminado, de lo contrario `false`.
     */
    fun removeAnimal(id: Int): Boolean {
        val removed = animals.removeIf { it.id == id } // Intenta eliminar el animal con el ID especificado.
        if (removed) saveToFile() // Guarda los cambios si la eliminación fue exitosa.
        return removed
    }

    /**
     * Comprueba si un animal con el ID proporcionado ya existe en la lista.
     *
     * @param id ID del animal a buscar.
     * @return Devuelve `true` si el ID existe en la lista, de lo contrario `false`.
     */
    fun existsAnimal(id: Int): Boolean {
        return animals.any { it.id == id } // Busca si el ID ya existe en la lista de animales.
    }

    /**
     * Guarda la lista actual de animales en un archivo utilizando el FileManager.
     * Se persiste en el archivo `animals.txt`.
     */
    private fun saveToFile() {
        fileManager.writeToFile("animals.txt", animals) // Llama al método del administrador de archivos para guardar.
    }
}
