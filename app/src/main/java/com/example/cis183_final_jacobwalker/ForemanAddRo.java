package com.example.cis183_final_jacobwalker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ForemanAddRo extends AppCompatActivity {

    DatabaseHelper dbHelper;

    EditText et_j_foremanAddRo_techAssigned;
    EditText et_j_foremanAddRo_roNum;
    EditText et_j_foremanAddRo_roHours;
    EditText et_j_foremanAddRo_roDesc;
    EditText et_j_foremanAddRo_roDate;

    Spinner sp_j_foremanAddRo_roTypesDropDown;

    Button btn_j_foremanAddRo_cancel;
    Button btn_j_foremanAddRo_add;

    ArrayAdapter<String> dropDownAdapter;

    Intent intent_j_foremanAddRo_foremanMain;

    String roType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_foreman_add_ro);

        et_j_foremanAddRo_techAssigned    = findViewById(R.id.et_v_foremanaddro_techAssigned);
        et_j_foremanAddRo_roNum           = findViewById(R.id.et_v_foremanaddro_roNum);
        et_j_foremanAddRo_roHours         = findViewById(R.id.et_v_foremanaddro_roHours);
        et_j_foremanAddRo_roDesc          = findViewById(R.id.et_v_foremanaddro_roDesc);
        et_j_foremanAddRo_roDate          = findViewById(R.id.et_v_foremanaddro_roDate);

        btn_j_foremanAddRo_add            = findViewById(R.id.btn_v_foremanaddro_add);
        btn_j_foremanAddRo_cancel         = findViewById(R.id.btn_v_foremanaddro_cancel);

        sp_j_foremanAddRo_roTypesDropDown = findViewById(R.id.sp_v_foremanaddro_roType);

        dbHelper = new DatabaseHelper(this);

        intent_j_foremanAddRo_foremanMain = new Intent(ForemanAddRo.this, ForemanMainPage.class);

        dropDownAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, dbHelper.getAllRoTypes());
        sp_j_foremanAddRo_roTypesDropDown.setAdapter(dropDownAdapter);

        cancelButtonListener();
        addButtonListener();
        spinnerEventListener();


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

    private void spinnerEventListener()
    {
        sp_j_foremanAddRo_roTypesDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                roType = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}