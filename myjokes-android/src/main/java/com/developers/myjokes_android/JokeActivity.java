package com.developers.myjokes_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    public static final String JOKE_KEY = "jokes";
    TextView jokeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        jokeText = (TextView) findViewById(R.id.joke_text_view);
        String key = getIntent().getExtras().getString(JOKE_KEY);
        jokeText.setText(key);
    }
}
