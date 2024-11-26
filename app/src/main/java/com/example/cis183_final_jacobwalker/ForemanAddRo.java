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

public class ForemanAddRo extends AppCompatActivity {

    EditText et_j_foremanAddRo_techAssigned;
    EditText et_j_foremanAddRo_roNum;
    EditText et_j_foremanAddRo_roHours;
    EditText et_j_foremanAdddRo_roDesc;
    EditText et_j_foremanAddRo_roDate;

    Spinner sp_j_foremanAddRo_roTypesDropDown;

    Button btn_j_foremanAddRo_cancel;
    Button btn_j_foremanAddRo_add;

    Intent intent_j_foremanAddRo_foremanMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_foreman_add_ro);

        et_j_foremanAddRo_techAssigned    = findViewById(R.id.et_v_foremanaddro_techAssigned);
        et_j_foremanAddRo_roNum           = findViewById(R.id.et_v_foremanaddro_roNum);
        et_j_foremanAddRo_roHours         = findViewById(R.id.et_v_foremanaddro_roHours);
        et_j_foremanAdddRo_roDesc         = findViewById(R.id.et_v_foremanaddro_roDesc);
        et_j_foremanAddRo_roDate          = findViewById(R.id.et_v_foremanaddro_roDate);

        sp_j_foremanAddRo_roTypesDropDown = findViewById(R.id.sp_v_foremanaddro_roType);

        intent_j_foremanAddRo_foremanMain = new Intent(ForemanAddRo.this, ForemanMainPage.class);

        cancelButtonListener();
        addButtonListener();


    }

    private void cancelButtonListener()
    {
        btn_j_foremanAddRo_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_j_foremanAddRo_foremanMain);
            }
        });
    }

    private void addButtonListener()
    {
        btn_j_foremanAddRo_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_j_foremanAddRo_foremanMain);
            }
        });
    }
}