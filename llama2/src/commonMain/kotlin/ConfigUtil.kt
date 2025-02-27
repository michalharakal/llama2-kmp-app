import kotlinx.io.Source
import kotlinx.io.readIntLe
import kotlin.math.abs

class ConfigUtil {

    companion object {
        fun from(buffer: Source): Config {
            val dim = buffer.readIntLe()
            val hiddenDim = buffer.readIntLe()
            val nLayers = buffer.readIntLe()
            val nHeads = buffer.readIntLe()
            val nKvHeads = buffer.readIntLe()
            val vocabSize = abs(buffer.readIntLe())
            val seqLen = buffer.readIntLe()
            return Config(dim, hiddenDim, nLayers, nHeads, nKvHeads, vocabSize, seqLen)
        }
    }

}