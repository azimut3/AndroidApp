package com.example.androidapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.androidapp.R;
import com.example.androidapp.data.Human;

public class HumanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.human_result);

        Intent intent = getIntent();
        Human human = intent.getParcelableExtra("human");
        EditText humanInfo = findViewById(R.id.resultField);
        humanInfo.setText(human.toString());
    }

    public void closeAction(View view){
        finish();
    }
}
