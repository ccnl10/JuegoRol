package rol

interface Sanador {
    val nombre: String
    val poderCuracion: Int

    fun curar(objetivo: Personaje) {
        if (!objetivo.estaVivo) {
            println("$nombre no puede curar a ${objetivo.nombre}: ha caído")
            return
        }
        objetivo.vida += poderCuracion
        println("$nombre cura a ${objetivo.nombre} (vida: ${objetivo.vida})")
    }
}

interface Volador {
    val altitud: Int

    fun volar(): String = "vuela a $altitud m de altura"
}
