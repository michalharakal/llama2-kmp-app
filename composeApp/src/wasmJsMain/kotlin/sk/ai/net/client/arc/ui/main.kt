package sk.ai.net.client.arc.ui

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import kotlinx.io.buffered
import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem
import sk.ai.net.sample.llama2.model.Llama2Model

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {
        SystemFileSystem.source(Path("stories15M.bin")).buffered().use { stories ->
            SystemFileSystem.source(Path("tokenizer.bin")).buffered().use { tokenizer ->


                App(
                    Llama2Model(
                        modelSource = stories, tokenizerSource = tokenizer
                    )
                )
            }
        }
    }
}