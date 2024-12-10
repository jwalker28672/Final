package com.example.cis183_final_jacobwalker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class ForemanSearch extends AppCompatActivity {

    DatabaseHelper dbHelper;

    EditText et_j_foremanSearch_techNum;
    EditText et_j_foremanSearch_roNum;
    EditText et_j_foremanSearch_hours;

    Spinner sp_j_foremanSearch_typeDropDown;
    Spinner sp_j_foremanSearch_opDropDown;

    Button btn_j_foremanSearch_home;
    Button btn_j_foremanSearch_search;

    ListView lv_j_foremanSearch_results;

    ForemanListAdapter listAdapter;

    ArrayAdapter<String> typeAdapter;
    ArrayAdapter<String> opAdapter;

    ArrayList<RequestOrder> listOfRos;

    ArrayList<String> types;
    ArrayList<String> operator;

    Intent intent_foremanSearch_foremanMain;

    int op;

    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_foreman_search);

        dbHelper = new DatabaseHelper(this);

        et_j_foremanSearch_techNum      = findViewById(R.id.et_v_foremansearch_techNum);
        et_j_foremanSearch_roNum        = findViewById(R.id.et_v_foremansearch_roNum);
        et_j_foremanSearch_hours        = findViewById(R.id.et_v_foremansearch_hours);

        sp_j_foremanSearch_typeDropDown = findViewById(R.id.sp_v_foremansearch_roType);
        sp_j_foremanSearch_opDropDown   = findViewById(R.id.sp_v_foremansearch_op);

        btn_j_foremanSearch_home        = findViewById(R.id.btn_v_foremansearch_home);
        btn_j_foremanSearch_search      = findViewById(R.id.btn_v_foremansearch_search);

        lv_j_foremanSearch_results      = findViewById(R.id.lv_v_foremansearch_results);

        listOfRos = new ArrayList<>();

        operator = new ArrayList<>(Arrays.asList("<",">"));

        opAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,operator);
        sp_j_foremanSearch_opDropDown.setAdapter(opAdapter);

        types = new ArrayList<>(Arrays.asList(""));
        types.addAll(dbHelper.getAllTypeNames());
        type = types.get(0);

        typeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, types);
        sp_j_foremanSearch_typeDropDown.setAdapter(typeAdapter);

        intent_foremanSearch_foremanMain = new Intent(ForemanSearch.this, ForemanMainPage.class);


        homeButtonListener();
        searchButtonListener();
        spinnerTypeEventListener();
        spinnerOpEventListener();

    }

    private void homeButtonListener()
    {
        btn_j_foremanSearch_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_foremanSearch_foremanMain);
            }
        });
    }

    private void searchButtonListener()
    {
        btn_j_foremanSearch_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String techNum ="";
                String roNum = "";
                String hours = "";

                techNum = et_j_foremanSearch_techNum.getText().toString();
                roNum = et_j_foremanSearch_roNum.getText().toString();
                hours = et_j_foremanSearch_hours.toString();

                listOfRos = dbHelper.foremanSearchDatabase(roNum, techNum, hours, type, op);

                fillResults();

            }
        });
    }

    private void spinnerTypeEventListener()
    {
        sp_j_foremanSearch_typeDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                type = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void spinnerOpEventListener()
    {
        sp_j_foremanSearch_opDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                op = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void fillResults()
    {
        listAdapter = new ForemanListAdapter(this,listOfRos);
        lv_j_foremanSearch_results.setAdapter(listAdapter);
    }
}