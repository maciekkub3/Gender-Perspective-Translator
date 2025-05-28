package com.example.gpt

import android.R.attr.onClick
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.gpt.data.AiRepository
import com.example.gpt.data.network.models.ChatRequest
import com.example.gpt.ui.theme.GptTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var aiRepository: AiRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GptTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                    ) {

                        Button(onClick = {
                            lifecycleScope.launch {
                            val response = aiRepository.getResponse(
                            ChatRequest(model = "gpt-3.5-turbo", input = "Hello, how are you?")
                        )
                        Log.d("Response", response.toString())
                        }
                            }

                        ) {
                            Text(text = "Click me")
                        }

                    }

                }
            }
        }
    }
}

