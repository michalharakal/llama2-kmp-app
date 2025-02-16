import kotlinx.io.Source
import kotlinx.io.readByteArray


fun Source.readFloatLe(): Float {
    return bytesToFloat(readByteArray(4))
}

fun Source.readFloatLeArray(size: Int): FloatArray {
    val floats = FloatArray(size)
    for (i in 0 until size) {
        floats[i] = readFloatLe()
    }
    return floats
}