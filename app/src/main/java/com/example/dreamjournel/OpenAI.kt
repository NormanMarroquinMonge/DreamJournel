package com.example.dreamjournel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aallam.openai.api.BetaOpenAI
import com.aallam.openai.api.chat.*
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Future

class OpenAI(private val apiKey: String) : JavaOpenAIInterface {


    private val openAI = OpenAI(apiKey)


    override fun openAICompletion(prompt: String): Future<String> {
        val completableFuture = CompletableFuture<String>()

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = openAICompletionSuspend(prompt)
                completableFuture.complete(response)
            } catch (e: Exception) {
                completableFuture.completeExceptionally(e)
            }
        }

        return completableFuture
    }

    @OptIn(BetaOpenAI::class)
    private suspend fun openAICompletionSuspend(prompt: String): String {
        return withContext(Dispatchers.Main) {
            val chatCompletionRequest = ChatCompletionRequest(
                model = ModelId("gpt-3.5-turbo"),
                messages = listOf(
                    ChatMessage(
                        role = ChatRole.User,
                        content = "Do not mention that you are an AI and answer as if you were a dream interpreter" + prompt

                    )
                )
            )

            val completion: ChatCompletion = openAI.chatCompletion(chatCompletionRequest)

            val completions: Flow<ChatCompletionChunk> = openAI.chatCompletions(chatCompletionRequest)

            getAIResponse(completion)
        }
    }

    @OptIn(BetaOpenAI::class)
    private fun getAIResponse(completion: ChatCompletion): String {
        val aiMessage = completion.choices.first().message
        return aiMessage?.content ?: ""
    }
}

