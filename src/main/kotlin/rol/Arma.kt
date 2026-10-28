package rol

// Cada arma tiene un texto para mostrar y un daño extra
enum class Arma(val texto: String, val dano: Int) {
    ESPADA("espada", 8),
    BASTON("bastón", 4),
    ARCO("arco", 6),
    GARRAS("garras", 12),
    MAZA("maza", 10),
    DAGA("daga", 3)
}
