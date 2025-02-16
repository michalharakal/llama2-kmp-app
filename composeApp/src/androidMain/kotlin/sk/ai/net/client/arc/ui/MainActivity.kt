package sk.ai.net.client.arc.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.io.asSource
import kotlinx.io.buffered
import sk.ai.net.sample.llama2.model.Llama2Model
import java.io.InputStream

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Suppose we have a file to read from:
        val tokenizerInputStream: InputStream? = assets.open("tokenizer.bin");
        val storiesInputStream: InputStream? = assets.open("stories15M.bin");


        setContent {
            if ((tokenizerInputStream != null) && (storiesInputStream != null)) {
                val llama2 =
                    Llama2Model(
                        modelSource = storiesInputStream.asSource().buffered(),
                        tokenizerSource = tokenizerInputStream.asSource().buffered()
                    )
                App(llama2)
            }
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    //App()
}