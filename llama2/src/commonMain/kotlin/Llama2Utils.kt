import kotlinx.io.Source

class Llama2Utils {
    companion object {
        fun buildLlama2(
            source: Source
        ): Llama2 {
            //val (config, weights) = fileSystem.read("$rootDir/$checkpoint".toPath()) {
            val config1 = ConfigUtil.from(source)
            val (config, weights) = config1 to WeightsUtil.from(config1, source)

            val state = RunState(config)
            return Llama2(config, weights, state)
        }
    }
}