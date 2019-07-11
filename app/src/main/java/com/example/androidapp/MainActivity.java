package com.example.androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.androidapp.data.Human;

public class MainActivity extends AppCompatActivity {
    EditText name,age,surname;
    Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.nameField);
        surname = findViewById(R.id.surnameField);
        age = findViewById(R.id.ageField);
        submitBtn = findViewById(R.id.submitBtn);

        submitBtn.setOnClickListener((e) -> submitAction());
    }

    public void submitAction(){
        Human human = saveHuman();
        Intent humanSubmitIntent = new Intent();
        humanSubmitIntent.putExtra("Human", human);
        startActivity(humanSubmitIntent);
    }

    private Human saveHuman(){
        return new Human(name.getText().toString(), surname.getText().toString(),
                age.getText().toString());
    }
}
