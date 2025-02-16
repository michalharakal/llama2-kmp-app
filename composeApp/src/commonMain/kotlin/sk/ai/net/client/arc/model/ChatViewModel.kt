package sk.ai.net.client.arc.model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import sk.ai.net.sample.llama2.model.Llama2Model

class ChatViewModel(private val llama2: Llama2Model) : ViewModel() {

    private val _uiState = MutableStateFlow(ChatUiState())
    val uiState: StateFlow<ChatUiState> = _uiState

    fun onInputChanged(newText: String) {
        _uiState.update { state ->
            state.copy(inputText = newText)
        }
    }

    fun sendMessage() {
        val currentText = _uiState.value.inputText
        if (currentText.isBlank()) return

        // Add the user's message
        _uiState.update { state ->
            state.copy(
                messages = state.messages + Message(currentText, true),
                inputText = ""
            )
        }

        // Optionally simulate a bot answer
        simulateBotAnswer()
    }

    private fun simulateBotAnswer() {
        llama2.generate(
            _uiState.value.inputText,
            1,
            0.5f,
            { botAnswer ->
                _uiState.update { state ->
                    state.copy(
                        messages = state.messages + Message(botAnswer, isUserMessage = false)
                    )
                }
            },
            { _ ->
                // No statistics to display
            }
        )
        val botAnswer = "Hello, I'm your weather agent. I'll get back to you soon."

    }
}
