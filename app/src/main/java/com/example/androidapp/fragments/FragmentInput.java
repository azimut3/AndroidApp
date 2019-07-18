package com.example.androidapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.androidapp.R;
import com.example.androidapp.data.Human;
import com.example.androidapp.managers.FrgmntMngr;

public class FragmentInput extends Fragment {
    EditText nameField;
    EditText ageField;
    EditText surnameField;
    Button submitBtn;

    public FragmentInput() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input, container, false);

        nameField = view.findViewById(R.id.nameField);
        surnameField = view.findViewById(R.id.surnameField);
        ageField = view.findViewById(R.id.ageField);
        submitBtn = view.findViewById(R.id.submitBtn);

        submitBtn.setOnClickListener((e) -> submitAction());

        return view;
    }

    public void submitAction(){
        String name = nameField.getText().toString();
        String surname = surnameField.getText().toString();
        String age = ageField.getText().toString();
        FragmentResult resultFrmnt = (FragmentResult)FrgmntMngr.getManager().replaceFragment(
                FrgmntMngr.getManager().getElement(FrgmntMngr.RESULT_FRAGMENT));
        if (name != null && surname != null && age != null) {
            Human human = Human.saveHuman(name, surname, age);
            //Intent humanSubmitIntent = new Intent(this, HumanActivity.class);
            //humanSubmitIntent.putExtra("human", human);
            //startActivity(humanSubmitIntent);
            resultFrmnt.setResultFieldText(human.toString());
        }


    }
}
