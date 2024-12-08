package org.example.model

/**
 * Data class que representa la entidad Animal en el sistema.
 * Esta clase es inmutable, ya que las propiedades no se pueden cambiar una vez creadas.
 * Incluye atributos básicos para identificar y describir un animal.
 */
data class Animal(
    val id: Int,               // ID único del animal
    val nombre: String,        // Nombre del animal
    val especie: String,       // Especie a la que pertenece el animal
    val edad: Int,            // Edad del animal en años
    val peso: Double           // Peso del animal en kilogramos
)
