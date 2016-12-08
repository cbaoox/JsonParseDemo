package com.google.jsonparsedemo;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    TextView textView2;
    ImageView imageView;
    Handler handler = new Handler();
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.text);
        textView2 = (TextView)findViewById(R.id.Gson);
        imageView = (ImageView)findViewById(R.id.image);
        context = MainActivity.this;
        new JsonParseUtils(textView,textView2,imageView,handler,context).start();
    }
}
