package org.example

import org.example.model.Animal
import org.example.model.Recinto
import org.example.repository.AnimalRepository
import org.example.repository.RecintoRepository
import org.example.storage.FileManager
import java.util.Scanner

/**
 * Función principal que da inicio a la aplicación.
 * Esta es la puerta de entrada para el programa principal de la consola.
 */
fun main() {
    // Instancia de FileManager para la gestión de archivos.
    val fileManager = FileManager()

    // Repositorios para animales y hábitats utilizando el administrador de archivos.
    val animalRepo = AnimalRepository(fileManager)
    val recintoRepo = RecintoRepository(fileManager)

    // Instancia del escáner para capturar la entrada del usuario.
    val scanner = Scanner(System.`in`)

    // Bucle principal de la aplicación para mantener el menú activo hasta que el usuario decida salir.
    while (true) {
        // Menú principal de la aplicación para seleccionar el área de trabajo.
        println("Bienvenido al sistema de gestión del Zoológico de Galo Tarapues")
        println("Ingresa el área en la que quieres trabajar:")
        println("1. Gestión de Hábitats")
        println("2. Gestión de Animales")
        println("3. Salir")
        print("Selecciona una opción: ")

        // Captura la opción ingresada por el usuario y redirige a la función correspondiente.
        when (scanner.nextLine().toIntOrNull()) {
            1 -> gestionarHabitats(scanner, recintoRepo) // Gestionar los hábitats
            2 -> gestionarAnimales(scanner, animalRepo) // Gestionar los animales
            3 -> {
                println("Saliendo del sistema. ¡Hasta luego!")
                break // Salir de la aplicación
            }
            else -> println("Opción no válida. Intenta de nuevo.") // Mensaje para opciones inválidas
        }
    }
}

/**
 * Función para gestionar el menú de operaciones relacionadas con los hábitats.
 */
fun gestionarHabitats(scanner: Scanner, recintoRepo: RecintoRepository) {
    while (true) {
        // Menú de operaciones para la gestión de hábitats.
        println("\nGestión de Hábitats:")
        println("1. Agregar Hábitat")
        println("2. Listar Hábitats")
        println("3. Eliminar Hábitat")
        println("4. Volver al menú principal")
        print("Selecciona una opción: ")

        when (scanner.nextLine().toIntOrNull()) {
            1 -> {
                // Lógica para agregar un hábitat nuevo.
                println("**Agregar Hábitat**")
                print("Ingresa el ID del hábitat: ")
                val id = scanner.nextLine().toIntOrNull() ?: continue
                if (recintoRepo.existsRecinto(id)) {
                    println("El ID ya existe. Ingresa un ID único.")
                    continue
                }

                print("Ingresa el nombre del hábitat: ")
                val nombre = scanner.nextLine()

                println("Selecciona el tipo del hábitat:")
                println("1. Selva")
                println("2. Desierto")
                println("3. Sabana")
                println("4. Acuático")
                print("Selecciona una opción: ")
                val tipo = when (scanner.nextLine().toIntOrNull()) {
                    1 -> "Selva"
                    2 -> "Desierto"
                    3 -> "Sabana"
                    4 -> "Acuático"
                    else -> {
                        println("Opción no válida. Regresando al menú.")
                        continue
                    }
                }

                print("Ingresa la capacidad del hábitat: ")
                val capacidad = scanner.nextLine().toIntOrNull() ?: continue

                val recinto = Recinto(id, nombre, tipo, capacidad)
                recintoRepo.addRecinto(recinto)
                println("Hábitat agregado exitosamente.")
            }
            2 -> {
                // Mostrar la lista de hábitats.
                println("Lista de Hábitats:")
                recintoRepo.listRecintos().forEach { println(it) }
            }
            3 -> {
                // Eliminar un hábitat.
                print("Ingresa el ID del hábitat que deseas eliminar: ")
                val id = scanner.nextLine().toIntOrNull() ?: continue
                if (recintoRepo.removeRecinto(id)) {
                    println("Hábitat eliminado exitosamente.")
                } else {
                    println("No se encontró un hábitat con ese ID.")
                }
            }
            4 -> return
            else -> println("Opción no válida. Intenta de nuevo.")
        }
    }
}

/**
 * Función para gestionar el menú de operaciones relacionadas con los animales.
 */
fun gestionarAnimales(scanner: Scanner, animalRepo: AnimalRepository) {
    while (true) {
        // Menú de operaciones para la gestión de animales.
        println("\nGestión de Animales:")
        println("1. Agregar Animal")
        println("2. Listar Animales")
        println("3. Eliminar Animal")
        println("4. Volver al menú principal")
        print("Selecciona una opción: ")

        when (scanner.nextLine().toIntOrNull()) {
            1 -> {
                // Lógica para agregar un animal nuevo.
                println("**Agregar Animal**")
                print("Ingresa el ID del animal: ")
                val id = scanner.nextLine().toIntOrNull() ?: continue
                if (animalRepo.existsAnimal(id)) {
                    println("El ID ya existe. Ingresa un ID único.")
                    continue
                }

                print("Ingresa el nombre del animal: ")
                val nombre = scanner.nextLine()

                println("Selecciona la especie del animal:")
                println("1. León")
                println("2. Tigre")
                println("3. Elefante")
                println("4. Águila")
                print("Selecciona una opción: ")
                val especie = when (scanner.nextLine().toIntOrNull()) {
                    1 -> "León"
                    2 -> "Tigre"
                    3 -> "Elefante"
                    4 -> "Águila"
                    else -> {
                        println("Opción no válida. Regresando al menú.")
                        continue
                    }
                }

                print("Ingresa la edad del animal: ")
                val edad = scanner.nextLine().toIntOrNull() ?: continue

                print("Ingresa el peso del animal: ")
                val peso = scanner.nextLine().toDoubleOrNull() ?: continue

                val animal = Animal(id, nombre, especie, edad, peso)
                animalRepo.addAnimal(animal)
                println("Animal agregado exitosamente.")
            }
            2 -> {
                // Mostrar la lista de animales.
                println("Lista de Animales:")
                animalRepo.listAnimals().forEach { println(it) }
            }
            3 -> {
                // Eliminar un animal.
                print("Ingresa el ID del animal que deseas eliminar: ")
                val id = scanner.nextLine().toIntOrNull() ?: continue
                if (animalRepo.removeAnimal(id)) {
                    println("Animal eliminado exitosamente.")
                } else {
                    println("No se encontró un animal con ese ID.")
                }
            }
            4 -> return
            else -> println("Opción no válida. Intenta de nuevo.")
        }
    }
}
