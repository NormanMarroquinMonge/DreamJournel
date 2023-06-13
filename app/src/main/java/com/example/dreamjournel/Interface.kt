package com.example.dreamjournel


import kotlinx.coroutines.flow.Flow
import java.util.concurrent.Future

interface JavaOpenAIInterface {

    /*
    Serves as a connection between the Kotlin class OpenAI and the Java class Summary
    The OpenAICompletion function takes in the users prompt which is a string.
    The Future stands for what the AI will respond with once it takes in your prompt.
     */
    fun openAICompletion(prompt: String): Future<String>


}