package com.example.amul.texttospeech;

import android.os.AsyncTask;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextToSpeech tospeech;
    int result;
    EditText editText;
    String text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.Text1);
        tospeech = new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

                if (status == TextToSpeech.SUCCESS) {
                    result = tospeech.setLanguage(Locale.UK);
                } else {
                    Toast.makeText(getApplicationContext(), "This feature is not available", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (tospeech != null) {
            tospeech.stop();
            tospeech.shutdown();
        }
    }

    public void TTS(View view) {
        switch (view.getId()) {
            case R.id.Button1:
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Toast.makeText(getApplicationContext(), "This feature is not available", Toast.LENGTH_SHORT).show();
                } else {
                    text = editText.getText().toString();
                    tospeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                }
                break;
            case R.id.Button2:
                if (tospeech != null) {
                    tospeech.stop();
//                    tospeech.shutdown();
                }
                break;

        }

    }
}
