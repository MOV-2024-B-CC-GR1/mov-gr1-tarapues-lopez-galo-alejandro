package org.example.model

/**
 * Data class que representa la entidad Recinto en el sistema.
 * Define atributos básicos para identificar y describir un hábitat.
 */
data class Recinto(
    val id: Int,               // ID único del recinto
    val nombre: String,        // Nombre del recinto
    val tipo: String,          // Tipo de hábitat (Ej: Selva, Desierto, etc.)
    val capacidad: Int         // Cantidad máxima de animales que puede alojar
)
