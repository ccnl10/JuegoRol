package rol

// Prueba de las partes 1 a 4. Ejecútala sola (los id empiezan en 1)
fun pruebaPersonajes() {
    val g = Guerrero("Brunilda", 5)
    val m = Mago("Merlín", 20)
    val a = Arquero("Ariadna", 1)
    val d = Dragon("Ignis")
    val t = Trol("Grog")
    println(listOf(g, m, a, d, t))
    g.atacar(t); g.atacar(d)
    m.atacar(t); m.atacar(t); m.atacar(t)
    a.atacar(d); a.atacar(d)
    d.atacar(g)
    t.vida = 500; println(t.barraVida())    // el setter no deja pasar de 150
    t.vida = -20; println(t.estaVivo)       // ni bajar de 0
    m.curar(t); m.curar(g)
    println("${d.nombre} ${d.volar()}")
}

fun mostrarInfo(nombre: String) {
    val p = Partida.buscar(nombre)
    if (p == null) {
        println("No hay ningún personaje con ese nombre.")
        return
    }
    println("$p ${p.barraVida()} · arma: ${p.arma.texto} (+${p.arma.dano})")
    if (p is Volador) println("   ${p.nombre} ${p.volar()}")
    if (p is Sanador) println("   Cura ${p.poderCuracion} puntos de vida")
    if (p is Mago) println("   Maná: ${p.mana}")
    if (p is Arquero) println("   Flechas: ${p.flechas}")
    if (p is Guerrero) println("   Armadura: ${p.armadura}")
    if (p is Enemigo) println("   Recompensa: ${p.recompensa} de oro")
}

fun leerTexto(mensaje: String): String {
    print(mensaje)
    return readlnOrNull()?.trim() ?: ""
}

fun jugarRondaMenu(hastaElFinal: Boolean) {
    if (Partida.combateTerminado()) {
        println("El combate ha terminado.")
        Partida.mostrarResultado()
        return
    }
    if (hastaElFinal) {
        while (!Partida.combateTerminado()) Partida.jugarRonda()
    } else {
        Partida.jugarRonda()
    }
    if (Partida.combateTerminado()) Partida.mostrarResultado()
}

fun main() {
    // Descomenta esta línea (y comenta el resto) para comprobar la parte 4
    // pruebaPersonajes(); return

    Partida.heroes.addAll(listOf(Guerrero("Brunilda", 6), Mago("Merlín", 30), Arquero("Ariadna", 10)))
    Partida.enemigos.addAll(listOf(Goblin("Snik"), Trol("Grog"), Dragon("Ignis")))

    while (true) {
        println("--- MENÚ ---")
        println("1. Ver equipos")
        println("2. Jugar ronda")
        println("3. Jugar hasta el final")
        println("4. Ver ranking")
        println("5. Información de un personaje")
        println("6. Usar poción (reto)")
        println("7. Estadísticas (reto)")
        println("0. Salir")
        print("Elige una opción: ")
        when (readlnOrNull()?.trim()?.toIntOrNull()) {
            1 -> Partida.mostrarEquipos()
            2 -> jugarRondaMenu(false)
            3 -> jugarRondaMenu(true)
            4 -> Partida.ranking()
            5 -> mostrarInfo(leerTexto("Nombre: "))
            6 -> {
                Partida.mostrarInventario()
                if (Partida.inventario.isNotEmpty()) {
                    val heroe = leerTexto("Héroe: ")
                    val pocion = leerTexto("Poción: ")
                    Partida.usarPocion(heroe, pocion)
                }
            }
            7 -> Partida.estadisticas()
            0 -> {
                println("¡Hasta la próxima aventura!")
                return
            }
            else -> println("Opción no válida.")
        }
    }
}
