package rol

abstract class Personaje(val nombre: String, val vidaMaxima: Int, val arma: Arma) {

    companion object {
        private var contador = 0
    }

    val id: Int

    init {
        contador++
        id = contador
    }

    // El setter mantiene la vida entre 0 y vidaMaxima
    var vida: Int = vidaMaxima
        set(valor) {
            field = if (valor < 0) 0 else if (valor > vidaMaxima) vidaMaxima else valor
        }

    val estaVivo: Boolean
        get() = vida > 0

    abstract val ataqueBase: Int

    open fun calcularDano(objetivo: Personaje): Int = ataqueBase + arma.dano

    open fun atacar(objetivo: Personaje) {
        println("$nombre ataca a ${objetivo.nombre} con ${arma.texto}")
        objetivo.recibirDano(calcularDano(objetivo))
    }

    open fun recibirDano(cantidad: Int) {
        vida -= cantidad
        println("   $nombre recibe $cantidad de daño (vida: $vida)")
        if (!estaVivo) println("   ¡$nombre ha caído!")
    }

    override fun toString() = "#$id $nombre (${this::class.simpleName})"
}

// Función de extensión: barra de 10 casillas con la vida que queda
fun Personaje.barraVida(): String {
    val llenos = vida * 10 / vidaMaxima
    return "[" + "#".repeat(llenos) + "-".repeat(10 - llenos) + "] $vida/$vidaMaxima"
}
