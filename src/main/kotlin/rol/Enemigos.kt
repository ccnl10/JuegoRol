package rol

abstract class Enemigo(nombre: String, vidaMaxima: Int, arma: Arma, val recompensa: Int) :
    Personaje(nombre, vidaMaxima, arma)

class Goblin(nombre: String) : Enemigo(nombre, 50, Arma.DAGA, 30) {
    override val ataqueBase = 5
}

class Trol(nombre: String) : Enemigo(nombre, 150, Arma.MAZA, 150), Sanador {
    override val ataqueBase = 10
    override val poderCuracion = 15
}

class Dragon(nombre: String) : Enemigo(nombre, 200, Arma.GARRAS, 500), Volador {
    override val ataqueBase = 18
    override val altitud = 300
}
