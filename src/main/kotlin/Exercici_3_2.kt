import java.io.*

fun main(args: Array<String>) {
    val ruta1 = "Rutes.dat"
    val ruta2 = "Rutes.obj"

    val rutas: MutableList<Ruta> = mutableListOf() //listado de todas las rutas
    try {
        val input = DataInputStream(FileInputStream(ruta1)) //para leer .dat
        val output = ObjectOutputStream(FileOutputStream(ruta2)) //para escribir .obj
        while (input.available() > 0) { //bucle fileinputstream
            val nomRuta = input.readUTF() //lees cada tipo
            val denivell = input.readInt()
            val desnivellacumulat = input.readInt()
            val numPunts = input.readInt()
            val listaDePunts: MutableList<PuntGeo> = mutableListOf() //declaras una lista tiene que coicidir con PuntGeo
            for (i in 1..numPunts){
                val nomPunt = input.readUTF()
                val long = input.readDouble()
                val lat = input.readDouble()
                val punt = PuntGeo(nomPunt,Coordenades(long, lat))
                listaDePunts.add(punt)
            }
            val rutaArchiu = Ruta(nomRuta, denivell, desnivellacumulat, listaDePunts) //Escribe la ruta
            rutas.add(rutaArchiu) //la añade a rutas
        }
        for (ruta in rutas){ //for para mostrar las rutas
            ruta.mostrarRuta()  //muestra
            println(" ")
            output.writeObject(ruta) //escribe en el archivo todas las rutas
        }
        input.close()
        output.close()
    } catch (e: Exception) {
        println("Error al leer el archivo")
    }
}