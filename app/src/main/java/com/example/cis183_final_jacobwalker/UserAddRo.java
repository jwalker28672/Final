package com.example.cis183_final_jacobwalker;

import android.content.Intent;
import android.os.Bundle;
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

public class UserAddRo extends AppCompatActivity {

    DatabaseHelper dbHelper;

    TextView tv_j_userAddRo_roError;
    TextView tv_j_userAddRo_blankFields;

    EditText et_j_userAddRo_roNum;
    EditText et_j_userAddRo_roHours;
    EditText et_j_userAddRo_roDate;

    Spinner  sp_j_userAddRo_roType;

    Button   btn_j_userAddRo_roAdd;
    Button   btn_j_userAddRo_back;

    Intent intent_j_userAddRo_userMain;

    ArrayAdapter<String> dropDownAdapter;

    String roType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_add_ro);

        tv_j_userAddRo_roError     = findViewById(R.id.tv_v_userAddRo_roError);
        tv_j_userAddRo_blankFields = findViewById(R.id.tv_v_userAddRo_blankFields);

        et_j_userAddRo_roNum       = findViewById(R.id.et_v_useraddro_roNum);
        et_j_userAddRo_roHours     = findViewById(R.id.et_v_useraddro_roHours);
        et_j_userAddRo_roDate      = findViewById(R.id.et_v_useraddro_roDate);

        sp_j_userAddRo_roType      = findViewById(R.id.sp_v_useraddro_roTypeDropDown);

        btn_j_userAddRo_roAdd      = findViewById(R.id.btn_v_useraddro_add);
        btn_j_userAddRo_back       = findViewById(R.id.btn_v_useraddro_back);

        intent_j_userAddRo_userMain = new Intent(UserAddRo.this, UserMainPage.class);

        dbHelper = new DatabaseHelper(this);

        dropDownAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, dbHelper.getAllTypeNames());
        sp_j_userAddRo_roType.setAdapter(dropDownAdapter);

        addButtonListener();
        backButtonListener();
        clearText();
        spinnerTypeEventListener();

    }


    private void addButtonListener()
    {
        btn_j_userAddRo_roAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addRo();
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

    private void spinnerTypeEventListener()
    {
        sp_j_userAddRo_roType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    private void addRo()
    {
        int roNum   = Integer.parseInt(et_j_userAddRo_roNum.getText().toString());
        int typeId  = dbHelper.getTypeId(roType);
        float hours = Float.parseFloat(et_j_userAddRo_roHours.getText().toString());
        String date = et_j_userAddRo_roDate.getText().toString();

        //make sure all fields are filled out
        if(!et_j_userAddRo_roNum.getText().toString().isEmpty() && !et_j_userAddRo_roHours.getText().toString().isEmpty() && !date.isEmpty())
        {
            tv_j_userAddRo_blankFields.setVisibility(View.INVISIBLE);

            RequestOrder ro = new RequestOrder();

            ro.setOrderNum(roNum);
            ro.setTechNum(SessionData.getLoggedInTech().getTechNum());
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
                tv_j_userAddRo_roError.setVisibility(View.VISIBLE);
            }

        }
        else
        {
            tv_j_userAddRo_blankFields.setVisibility(View.VISIBLE);
        }


    }


    private void clearText()
    {
        et_j_userAddRo_roNum.setText("");
        et_j_userAddRo_roHours.setText("");
        et_j_userAddRo_roDate.setText("");
    }
}