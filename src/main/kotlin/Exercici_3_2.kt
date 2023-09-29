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