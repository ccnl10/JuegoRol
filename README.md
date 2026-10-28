# Juego de rol por turnos (UT1 · Bloque 5)

Solución del ejercicio "Juego de rol por turnos".

## Abrir en IntelliJ IDEA
1. Descomprime el ZIP.
2. File → Open… y elige la carpeta `JuegoRol` (la que contiene `build.gradle.kts`).
3. Acepta "Trust Project" y espera a que Gradle descargue lo necesario (necesita Internet la primera vez).
4. Abre `src/main/kotlin/rol/Main.kt` y pulsa el triángulo verde junto a `fun main()`.

Si IntelliJ pide un JDK, elige uno de la versión 17 o superior (File → Project Structure → SDK).

## Archivos (paquete `rol`)
- `Arma.kt`: enum class Arma
- `Personaje.kt`: clase abstracta Personaje y la extensión barraVida()
- `Heroes.kt`: Guerrero, Mago y Arquero
- `Enemigos.kt`: Enemigo, Goblin, Trol y Dragon
- `Habilidades.kt`: interfaces Sanador y Volador
- `Partida.kt`: object Partida (y data class Pocion del reto)
- `Main.kt`: prueba de las partes 1-4, menú y main

Para comprobar la prueba de la parte 4, descomenta la primera línea de `main()`.
