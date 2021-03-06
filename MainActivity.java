package com.example.mziccard.myapplication;

import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

public class MainActivity extends AppCompatActivity {

    // todo API_KEY should not be stored in plain sight
    private static final String API_KEY = "AIzaSyAl2J3Vk8UDkpPnRekKliLuhroV7XYsAYk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textView = (TextView) findViewById(R.id.text_view);
        final Handler textViewHandler = new Handler();

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                TranslateOptions options = TranslateOptions.newBuilder()
                        .setApiKey(API_KEY)
                        .build();
                Translate translate = options.getService();
                final Translation translation =
                        translate.translate("where you going?",
                                Translate.TranslateOption.targetLanguage("si"));
                textViewHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (textView != null) {
                            textView.setText(translation.getTranslatedText());
                        }
                    }
                });
                return null;
            }
        }.execute();
    }
}
