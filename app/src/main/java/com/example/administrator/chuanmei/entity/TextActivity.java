package com.example.administrator.chuanmei.entity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.chuanmei.R;

public class TextActivity extends AppCompatActivity {
    EditText edt_shu;
    Button but_an;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        edt_shu= (EditText) findViewById(R.id.editText);
        but_an= (Button) findViewById(R.id.button);
        but_an.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=edt_shu.getText().toString();
                Toast.makeText(TextActivity.this, name, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
