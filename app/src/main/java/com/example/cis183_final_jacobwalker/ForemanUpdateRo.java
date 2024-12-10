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

public class ForemanUpdateRo extends AppCompatActivity {

    DatabaseHelper dbHelper;

    TextView tv_j_foremanUpdateRo_roNum;

    EditText et_j_foremanUpdateRo_assignedTech;
    EditText et_j_foremanUpdateRo_roHours;
    EditText et_j_foremanUpdateRo_roDate;

    Spinner sp_j_foremanUpdateRo_roTypeDropDown;

    Button btn_j_foremanUpdateRo_cancel;
    Button btn_j_foremanUpdateRo_update;

    Intent intent_j_foremanUpdateRo_foremanDetails;

    ArrayAdapter<String> dropDownAdapter;

    String roType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_foreman_update_ro);

        tv_j_foremanUpdateRo_roNum          = findViewById(R.id.tv_v_foremanupdatero_roNum);

        et_j_foremanUpdateRo_assignedTech   = findViewById(R.id.et_v_foremanupdatero_assignedTech);
        et_j_foremanUpdateRo_roHours        = findViewById(R.id.et_v_foremanupdatero_roHours);
        et_j_foremanUpdateRo_roDate         = findViewById(R.id.et_v_foremanupdatero_roDate);

        sp_j_foremanUpdateRo_roTypeDropDown = findViewById(R.id.sp_v_foremanupdatero_roType);

        btn_j_foremanUpdateRo_cancel        = findViewById(R.id.btn_v_foremanupdatero_cancel);
        btn_j_foremanUpdateRo_update        = findViewById(R.id.btn_v_foremanupdatero_update);

        dbHelper = new DatabaseHelper(this);

        intent_j_foremanUpdateRo_foremanDetails = new Intent(ForemanUpdateRo.this, ForemanRoDetails.class);

        dropDownAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, dbHelper.getAllTypeNames());
        sp_j_foremanUpdateRo_roTypeDropDown.setAdapter(dropDownAdapter);

        cancelButtonListener();
        updateButtonListener();
        spinnerEventListener();
        fillTextBoxes();

    }

    private void cancelButtonListener()
    {
        btn_j_foremanUpdateRo_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_j_foremanUpdateRo_foremanDetails);
            }
        });
    }

    private void updateButtonListener()
    {
        btn_j_foremanUpdateRo_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                updateRo();
                startActivity(intent_j_foremanUpdateRo_foremanDetails);
            }
        });
    }

    private void spinnerEventListener()
    {
        sp_j_foremanUpdateRo_roTypeDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        tv_j_foremanUpdateRo_roNum.setText(Integer.toString(SessionData.getCurSelectedOrder().getOrderNum()));
        et_j_foremanUpdateRo_assignedTech.setText(Integer.toString(SessionData.getCurSelectedOrder().getTechNum()));
        et_j_foremanUpdateRo_roHours.setText(Float.toString(SessionData.getCurSelectedOrder().getHours()));
        sp_j_foremanUpdateRo_roTypeDropDown.setSelection(position);
        et_j_foremanUpdateRo_roDate.setText(SessionData.getCurSelectedOrder().getDate());

    }

    private void updateRo()
    {
        RequestOrder ro = new RequestOrder();

        ro.setOrderNum(Integer.parseInt(tv_j_foremanUpdateRo_roNum.getText().toString()));
        ro.setTechNum(Integer.parseInt(et_j_foremanUpdateRo_assignedTech.getText().toString()));
        ro.setHours(Float.parseFloat(et_j_foremanUpdateRo_roHours.getText().toString()));
        ro.setTypeId(dbHelper.getTypeId(roType));
        ro.setDate(et_j_foremanUpdateRo_roDate.getText().toString());

        //verify data is being captured correctly
        //Log.d("Details: ", Integer.toString(ro.getTechNum()) + " " + ro.getTypeId());
        dbHelper.updateRo(SessionData.getCurSelectedOrder().orderNum, ro);

        SessionData.setCurSelectedOrder(ro);

    }
}