package com.example.saveactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private EditText edit姓名;
    private EditText edit学号;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textView姓名 = findViewById(R.id.text姓名);
        final TextView textView学号 = findViewById(R.id.text学号);
        edit姓名 = (EditText) findViewById(R.id.text姓名);
        edit学号 = (EditText) findViewById(R.id.text学号);
        Button button储存 = findViewById(R.id.button储存);
        Button button清除 = findViewById(R.id.button清除);

        button储存.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputText信息 = edit学号.getText().toString()+edit姓名.getText().toString();
                save(inputText信息);
            }
        });
        button清除.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView姓名.setText("");
                textView学号.setText("");
            }
        });
    }

  /*  protected void onDestroy() {
        super.onDestroy();

    }*/

    public void save(String inputText信息) {
        final TextView textView = findViewById(R.id.text);
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            out = openFileOutput("data", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputText信息);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                    textView.setText("姓名和学号是:"+inputText信息);
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
