package sk.ai.net.sample.llama2.model

import kotlinx.io.Source
import kotlin.time.measureTime

class Llama2Model(
    modelSource: Source,
    tokenizerSource: Source,
) {

    val model = Llama2Utils.buildLlama2(modelSource)
    val tokenize = TokenizerUtils.buildTokenizer(tokenizerSource, model.config.vocabSize)

    fun generate(
        prompt: String?, steps: Int, temperature: Float,
        promptStreamHandler: (String) -> Unit,
        statisticsHandler: (String) -> Unit
    ) {
        // process the prompt, if any
        val promptTokens: IntArray = if (prompt != null) {
            tokenize.encode(prompt)
        } else {
            IntArray(0)
        }

        val time = measureTime {
            model.generate(promptTokens, steps, temperature) { next ->
                // following BOS token (1), sentencepiece decoder strips any leading whitespace (see PR#89)
                val tokenStr = tokenize.decode(next)
                promptStreamHandler(tokenStr)
                print(tokenStr)
            }
        }.inWholeMilliseconds
        statisticsHandler("${(steps) / time.toDouble() * 1000}")
    }
}