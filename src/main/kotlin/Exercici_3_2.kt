import java.io.*

fun main(args: Array<String>) {
    val ruta1 = "Rutes.dat"
    val ruta2 = "Rutes.obj"

    val rutas: MutableList<Ruta> = mutableListOf()
    try {
        val input = DataInputStream(FileInputStream(ruta1))
        val output = ObjectOutputStream(FileOutputStream(ruta2))
        while (input.available() > 0) {
            val nomRuta = input.readUTF()
            val denivell = input.readInt()
            val desnivellacumulat = input.readInt()
            val numPunts = input.readInt()
            val listaDePunts: MutableList<PuntGeo> = mutableListOf()
            for (i in 1..numPunts){
                val nomPunt = input.readUTF()
                val long = input.readDouble()
                val lat = input.readDouble()
                val punt = PuntGeo(nomPunt,Coordenades(long, lat))
                listaDePunts.add(punt)
            }
            val rutaArchiu = Ruta(nomRuta, denivell, desnivellacumulat, listaDePunts)
            rutas.add(rutaArchiu)
        }
        for (ruta in rutas){
            ruta.mostrarRuta()
            println(" ")
            output.writeObject(ruta)
        }
        input.close()
        output.close()
    } catch (e: Exception) {
        println("Error al leer el archivo")
    }
}

class Coordenades(var latitud: Double, var longitud: Double) : Serializable {
    companion object {
        private const val serialVersionUID: Long = 1
    }
}

class PuntGeo(var nom: String, var coord: Coordenades) : Serializable {
    companion object {
        private const val serialVersionUID: Long = 1
    }
}

class Ruta(var nom: String, var desnivell: Int, var desnivellAcumulat: Int, var llistaDePunts: MutableList<PuntGeo>) :
    Serializable {
    companion object {
        private const val serialVersionUID: Long = 1
    }

    fun addPunt(p: PuntGeo) {
        llistaDePunts.add(p)
    }

    fun getPunt(i: Int): PuntGeo {
        return llistaDePunts.get(i)
    }

    fun getPuntNom(i: Int): String {
        return llistaDePunts.get(i).nom
    }

    fun getPuntLatitud(i: Int): Double {
        return llistaDePunts.get(i).coord.latitud
    }

    fun getPuntLongitud(i: Int): Double {
        return llistaDePunts.get(i).coord.longitud
    }

    fun size(): Int {
        return llistaDePunts.size
    }

    fun mostrarRuta() {
        println("Ruta: $nom")
        println("Desnivell: $desnivell")
        println("Desnivell acumulat: $desnivellAcumulat")
        println("Te ${llistaDePunts.size} punts")
        for (i in 0 until llistaDePunts.size) {
            val point: PuntGeo = llistaDePunts[i]
            println("Punt $i: ${point.nom} (${point.coord.latitud} ,${point.coord.longitud})")
        }
    }
}