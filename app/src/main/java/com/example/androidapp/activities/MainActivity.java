package com.example.androidapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.androidapp.R;
import com.example.androidapp.activities.adapter.ViewPagerAdapter;
import com.example.androidapp.activities.fragments.DataCollector;
import com.example.androidapp.activities.fragments.HumanResult;
import com.example.androidapp.data.Human;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    EditText nameField = null;
    EditText ageField = null;
    EditText surnameField = null;
    Button submitBtn;

    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private TabLayout tabLayout;

    private DataCollector dataCollector;
    private HumanResult humanResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.view_pager);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        dataCollector = new DataCollector();
        humanResult = new HumanResult();

        adapter.addFragment(dataCollector, "Data collector One");
        adapter.addFragment(humanResult, "Human result");

        viewPager.setAdapter(adapter);

        tabLayout = findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

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
