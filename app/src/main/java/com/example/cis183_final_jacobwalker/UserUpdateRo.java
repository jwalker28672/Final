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

public class UserUpdateRo extends AppCompatActivity {

    DatabaseHelper dbHelper;

    TextView tv_j_userUpdateRo_roNum;

    EditText et_j_userUpDateRo_roHours;
    EditText et_j_userUpDateRo_roDate;

    Spinner sp_j_userUpDateRo_roTypeDropDown;

    Button btn_j_userUpdateRo_cancel;
    Button btn_j_userUpdateRo_update;

    Intent intent_j_userUpdateRo_userDetails;

    ArrayAdapter<String> dropDownAdapter;

    String roType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_update_ro);

        tv_j_userUpdateRo_roNum          = findViewById(R.id.tv_v_userupdatero_roNum);

        et_j_userUpDateRo_roHours        = findViewById(R.id.et_v_userupdatero_roHours);
        et_j_userUpDateRo_roDate         = findViewById(R.id.et_v_userupdatero_roDate);

        sp_j_userUpDateRo_roTypeDropDown = findViewById(R.id.sp_v_userupdatero_roTypeDropDown);

        btn_j_userUpdateRo_cancel        = findViewById(R.id.btn_v_userupdatero_cancel);
        btn_j_userUpdateRo_update        = findViewById(R.id.btn_v_userupdatero_update);

        intent_j_userUpdateRo_userDetails = new Intent(UserUpdateRo.this, UserRoDetails.class);

        dbHelper = new DatabaseHelper(this);

        dropDownAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, dbHelper.getAllTypeNames());
        sp_j_userUpDateRo_roTypeDropDown.setAdapter(dropDownAdapter);

        cancelButtonListener();
        updateButtonListener();
        spinnerEventListener();
        fillTextBoxes();

    }

    private void cancelButtonListener()
    {
        btn_j_userUpdateRo_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_j_userUpdateRo_userDetails);
            }
        });
    }

    private void updateButtonListener()
    {
        btn_j_userUpdateRo_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                updateRo();
                startActivity(intent_j_userUpdateRo_userDetails);
            }
        });
    }

    private void spinnerEventListener()
    {
        sp_j_userUpDateRo_roTypeDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    private void fillTextBoxes()
    {
        String name = dbHelper.getTypeName(SessionData.getCurSelectedOrder().getTypeId());
        int position = dropDownAdapter.getPosition(name);

        tv_j_userUpdateRo_roNum.setText(Integer.toString(SessionData.getCurSelectedOrder().getOrderNum()));
        et_j_userUpDateRo_roHours.setText(Float.toString(SessionData.getCurSelectedOrder().getHours()));
        sp_j_userUpDateRo_roTypeDropDown.setSelection(position);
        et_j_userUpDateRo_roDate.setText(SessionData.getCurSelectedOrder().getDate());

    }

    private void updateRo()
    {
        RequestOrder ro = new RequestOrder();

        ro.setOrderNum(Integer.parseInt(tv_j_userUpdateRo_roNum.getText().toString()));
        ro.setTechNum(SessionData.getLoggedInTech().getTechNum());
        ro.setHours(Float.parseFloat(et_j_userUpDateRo_roHours.getText().toString()));
        ro.setTypeId(dbHelper.getTypeId(roType));
        ro.setDate(et_j_userUpDateRo_roDate.getText().toString());


        dbHelper.updateRo(SessionData.getCurSelectedOrder().orderNum, ro);

        SessionData.setCurSelectedOrder(ro);

    }

}