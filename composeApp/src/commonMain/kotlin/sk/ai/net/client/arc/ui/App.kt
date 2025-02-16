package sk.ai.net.client.arc.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.*
import kotlinx.io.Source
import org.jetbrains.compose.ui.tooling.preview.Preview
import sk.ai.net.client.arc.model.ChatViewModel
import sk.ai.net.sample.llama2.model.Llama2Model

@Preview
@Composable
fun App(llama2: Llama2Model) {
    MaterialTheme(
        colorScheme = lightColorScheme(), // or darkColorScheme()
        typography = Typography()
    ) {
        MainScreen(
            ChatViewModel(llama2)
        )
    }
}