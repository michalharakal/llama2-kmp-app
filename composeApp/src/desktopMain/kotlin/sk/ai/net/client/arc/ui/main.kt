package sk.ai.net.client.arc.ui

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.io.asSource
import kotlinx.io.buffered
import sk.ai.net.sample.llama2.model.Llama2Model

fun readResource(resourcePath: String) = object {}.javaClass.getResourceAsStream(resourcePath)

fun main() = application {


    Window(
        onCloseRequest = ::exitApplication,
        title = "LLama2",
    ) {

        val llama2 = Llama2Model(
            modelSource = readResource("/stories15M.bin")!!.asSource().buffered(),
            tokenizerSource = readResource("/tokenizer.bin")!!.asSource().buffered()
        )

        App(llama2)
    }
}