package com.example.dreamjournel;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Summary extends AppCompatActivity {

    private JavaOpenAIInterface openAI;
    private TextView aiResponseTextView;
    private Button button;

    private String startingText = "Please interpret this dream: ";
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        String apiKey = "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"; // DONT FORGET TO ADD API KEY
        openAI = new OpenAI(apiKey);

        aiResponseTextView = findViewById(R.id.ai_response_text_view);
        aiResponseTextView.setBackgroundColor(getResources().getColor(
                android.R.color.white
        ));
        button = findViewById(R.id.button);
        editText = findViewById(R.id.editTextTextMultiLine);
        editText.setText("");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String prompt = editText.getText().toString();
                if (!prompt.isEmpty()) {
                    new OpenAICompletionTask().execute(prompt);
                }
            }
        });
    }


    private class OpenAICompletionTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... prompts) {
            String prompt = prompts[0];
            try {
                Future<String> responseFuture = openAI.openAICompletion(prompt);
                return responseFuture.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return "Error: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String response) {
            aiResponseTextView.setText(response);
        }
    }
}