package rol

class Guerrero(nombre: String, val armadura: Int) : Personaje(nombre, 120, Arma.ESPADA) {
    override val ataqueBase = 12

    // Contra un volador solo hace la mitad del daño
    override fun calcularDano(objetivo: Personaje): Int {
        val dano = super.calcularDano(objetivo)
        return if (objetivo is Volador) dano / 2 else dano
    }

    // La armadura reduce el daño, pero siempre recibe al menos 1
    override fun recibirDano(cantidad: Int) {
        super.recibirDano(maxOf(cantidad - armadura, 1))
    }
}

class Mago(nombre: String, var mana: Int) : Personaje(nombre, 80, Arma.BASTON), Sanador, Volador {
    override val ataqueBase = 6
    override val poderCuracion = 25
    override val altitud = 10

    override fun atacar(objetivo: Personaje) {
        if (mana >= 10) {
            mana -= 10
            println("$nombre lanza una bola de fuego a ${objetivo.nombre} (maná: $mana)")
            objetivo.recibirDano(calcularDano(objetivo) * 2)
        } else {
            super.atacar(objetivo)
        }
    }
}

class Arquero(nombre: String, var flechas: Int) : Personaje(nombre, 90, Arma.ARCO) {
    override val ataqueBase = 9

    override fun atacar(objetivo: Personaje) {
        if (flechas > 0) {
            flechas--
            super.atacar(objetivo)
        } else {
            println("$nombre no tiene flechas y golpea con el puño")
            objetivo.recibirDano(2)
        }
    }
}
