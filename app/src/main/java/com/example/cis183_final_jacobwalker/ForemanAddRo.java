package com.example.cis183_final_jacobwalker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class ForemanAddRo extends AppCompatActivity {

    DatabaseHelper dbHelper;

    TextView tv_j_foremanAddRo_roError;
    TextView tv_j_foremanAddRo_BlankFields;

    EditText et_j_foremanAddRo_techAssigned;
    EditText et_j_foremanAddRo_roNum;
    EditText et_j_foremanAddRo_roHours;
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

        tv_j_foremanAddRo_roError         = findViewById(R.id.tv_v_foremanAddRo_roError);
        tv_j_foremanAddRo_BlankFields     = findViewById(R.id.tv_v_foremanAddRo_blankFields);

        et_j_foremanAddRo_techAssigned    = findViewById(R.id.et_v_foremanaddro_techAssigned);
        et_j_foremanAddRo_roNum           = findViewById(R.id.et_v_foremanaddro_roNum);
        et_j_foremanAddRo_roHours         = findViewById(R.id.et_v_foremanaddro_roHours);
        et_j_foremanAddRo_roDate          = findViewById(R.id.et_v_foremanaddro_roDate);

        btn_j_foremanAddRo_add            = findViewById(R.id.btn_v_foremanaddro_add);
        btn_j_foremanAddRo_cancel         = findViewById(R.id.btn_v_foremanaddro_cancel);

        sp_j_foremanAddRo_roTypesDropDown = findViewById(R.id.sp_v_foremanaddro_roType);

        dbHelper = new DatabaseHelper(this);

        intent_j_foremanAddRo_foremanMain = new Intent(ForemanAddRo.this, ForemanMainPage.class);

        dropDownAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, dbHelper.getAllTypeNames());
        sp_j_foremanAddRo_roTypesDropDown.setAdapter(dropDownAdapter);


        cancelButtonListener();
        addButtonListener();
        spinnerEventListener();
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

                AddRo();
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

    private void AddRo()
    {
        int roNum   = Integer.parseInt(et_j_foremanAddRo_roNum.getText().toString());
        int techNum = Integer.parseInt(et_j_foremanAddRo_techAssigned.getText().toString());
        int typeId  = dbHelper.getTypeId(roType);
        float hours = Float.parseFloat(et_j_foremanAddRo_roHours.getText().toString());
        String date = et_j_foremanAddRo_roDate.getText().toString();

        //make sure all fields are filled out
        if(!et_j_foremanAddRo_roNum.getText().toString().isEmpty() && !et_j_foremanAddRo_techAssigned.getText().toString().isEmpty() && !et_j_foremanAddRo_roHours.getText().toString().isEmpty() && !date.isEmpty())
        {
            tv_j_foremanAddRo_BlankFields.setVisibility(View.INVISIBLE);

            RequestOrder ro = new RequestOrder();

            ro.setOrderNum(roNum);
            ro.setTechNum(techNum);
            ro.setHours(hours);
            ro.setDate(date);
            ro.setTypeId(typeId);

            //check if ro already exist
            if(!dbHelper.checkRoExists(roNum))
            {

                dbHelper.addRo(ro);
                clearText();
            }
            else
            {
                tv_j_foremanAddRo_roError.setVisibility(View.VISIBLE);
            }

        }
        else
        {
            tv_j_foremanAddRo_BlankFields.setVisibility(View.VISIBLE);
        }


    }

    private void clearText()
    {
        et_j_foremanAddRo_roNum.setText("");
        et_j_foremanAddRo_techAssigned.setText("");
        et_j_foremanAddRo_roHours.setText("");
        et_j_foremanAddRo_roDate.setText("");
    }
}