package com.example.cis183_final_jacobwalker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UserAddRo extends AppCompatActivity {

    EditText et_j_userAddRo_roNum;
    EditText et_j_userAddRo_roHours;
    EditText et_j_useraddRo_roDesc;
    EditText et_j_useraddRo_roDate;

    Spinner  sp_j_userAddRo_roType;

    Button   btn_j_userAddRo_roAdd;
    Button   btn_j_userAddRo_back;

    Intent intent_j_userAddRo_userMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_add_ro);

        et_j_userAddRo_roNum   = findViewById(R.id.et_v_useraddro_roNum);
        et_j_userAddRo_roHours = findViewById(R.id.et_v_useraddro_roHours);
        et_j_useraddRo_roDesc  = findViewById(R.id.et_v_useraddro_roDesc);
        et_j_useraddRo_roDate  = findViewById(R.id.et_v_useraddro_roDate);

        sp_j_userAddRo_roType  = findViewById(R.id.sp_v_useraddro_roTypeDropDown);

        btn_j_userAddRo_roAdd = findViewById(R.id.btn_v_useraddro_add);
        btn_j_userAddRo_back  = findViewById(R.id.btn_v_useraddro_back);

        intent_j_userAddRo_userMain = new Intent(UserAddRo.this, UserMainPage.class);

        addButtonListener();
        backButtonListener();

    }


    private void addButtonListener()
    {
        btn_j_userAddRo_roAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_j_userAddRo_userMain);
            }
        });
    }

    private void backButtonListener()
    {
        btn_j_userAddRo_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_j_userAddRo_userMain);
            }
        });
    }
}