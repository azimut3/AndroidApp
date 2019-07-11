package com.example.androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.androidapp.data.Human;

public class MainActivity extends AppCompatActivity {
    EditText nameField = null;
    EditText ageField = null;
    EditText surnameField = null;
    Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameField = findViewById(R.id.nameField);
        surnameField = findViewById(R.id.surnameField);
        ageField = findViewById(R.id.ageField);
        submitBtn = findViewById(R.id.submitBtn);

        submitBtn.setOnClickListener((e) -> submitAction());
    }

    public void submitAction(){
        String name = nameField.getText().toString();
        String surname = surnameField.getText().toString();
        String age = ageField.getText().toString();
        if (name != null && surname != null && age != null) {
            Human human = saveHuman(name, surname, age);
            Intent humanSubmitIntent = new Intent(this, HumanActivity.class);
            humanSubmitIntent.putExtra("human", human);
            startActivity(humanSubmitIntent);
        }
    }

    private Human saveHuman(String name, String surname, String age){
        return new Human(name, surname, age);
    }
}
