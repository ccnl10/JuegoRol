package rol

// Reto: poción para el inventario
data class Pocion(val nombre: String, val curacion: Int)

object Partida {
    val heroes = mutableListOf<Personaje>()
    val enemigos = mutableListOf<Enemigo>()
    var ronda = 0

    // Reto: inventario de pociones (poción -> unidades)
    val inventario = mutableMapOf(
        Pocion("pequeña", 20) to 3,
        Pocion("grande", 60) to 1
    )

    fun mostrarEquipos() {
        println("--- HÉROES ---")
        heroes.forEach { mostrar(it) }
        println("--- ENEMIGOS ---")
        enemigos.forEach { mostrar(it) }
    }

    private fun mostrar(p: Personaje) {
        var extra = ""
        if (p is Volador) extra += " [vuela]"
        if (p is Sanador) extra += " [cura]"
        println("$p ${p.barraVida()}$extra")
    }

    fun jugarRonda() {
        ronda++
        println("===== RONDA $ronda =====")
        // 1. Cada héroe vivo ataca al primer enemigo vivo
        for (h in heroes.filter { it.estaVivo }) {
            val objetivo = enemigos.find { it.estaVivo } ?: break
            h.atacar(objetivo)
        }
        // 2. Cada enemigo vivo ataca al héroe vivo con más vida
        for (e in enemigos.filter { it.estaVivo }) {
            val objetivo = heroes.filter { it.estaVivo }.maxByOrNull { it.vida } ?: break
            e.atacar(objetivo)
        }
        // 3. Los sanadores curan al compañero herido con menos vida
        curarEquipo(heroes)
        curarEquipo(enemigos)
    }

    private fun curarEquipo(equipo: List<Personaje>) {
        for (s in equipo.filter { it.estaVivo }.filterIsInstance<Sanador>()) {
            val herido = equipo.filter { it.estaVivo && it.vida < it.vidaMaxima }
                .minByOrNull { it.vida } ?: return
            s.curar(herido)
        }
    }

    fun combateTerminado(): Boolean =
        heroes.all { !it.estaVivo } || enemigos.all { !it.estaVivo }

    fun mostrarResultado() {
        if (heroes.any { it.estaVivo }) {
            val oro = enemigos.filter { !it.estaVivo }.map { it.recompensa }.sum()
            println("¡Victoria en $ronda rondas! Oro conseguido: $oro")
        } else {
            println("Los héroes han caído en la ronda $ronda...")
        }
    }

    fun ranking() {
        val ordenados = (heroes + enemigos).sortedByDescending { it.vida }
        for ((i, p) in ordenados.withIndex()) {
            println("${i + 1}. ${p.nombre}: ${p.vida}")
        }
    }

    fun buscar(nombre: String): Personaje? =
        (heroes + enemigos).find { it.nombre.equals(nombre, ignoreCase = true) }

    // ---------- Reto ----------
    fun usarPocion(nombreHeroe: String, nombrePocion: String) {
        val heroe = heroes.find { it.nombre.equals(nombreHeroe, ignoreCase = true) }
        if (heroe == null) {
            println("No hay ningún héroe con ese nombre.")
            return
        }
        if (!heroe.estaVivo) {
            println("${heroe.nombre} ha caído: ya no puede usar pociones.")
            return
        }
        val pocion = inventario.keys.find { it.nombre.equals(nombrePocion, ignoreCase = true) }
        if (pocion == null) {
            println("No te quedan pociones de ese tipo.")
            return
        }
        heroe.vida += pocion.curacion
        val quedan = (inventario[pocion] ?: 0) - 1
        if (quedan > 0) inventario[pocion] = quedan else inventario.remove(pocion)
        println("${heroe.nombre} bebe una poción ${pocion.nombre} (vida: ${heroe.vida})")
    }

    fun mostrarInventario() {
        if (inventario.isEmpty()) println("El inventario está vacío.")
        for ((pocion, unidades) in inventario) {
            println("- Poción ${pocion.nombre} (+${pocion.curacion}): $unidades")
        }
    }

    fun estadisticas() {
        val porClase = (heroes + enemigos).groupBy { it::class.simpleName }
        for ((clase, lista) in porClase) {
            println("$clase: ${lista.size} (vivos: ${lista.count { it.estaVivo }})")
        }
        println("Vida total de los héroes: ${heroes.map { it.vida }.sum()}")
        println("Vida total de los enemigos: ${enemigos.map { it.vida }.sum()}")
    }
}
