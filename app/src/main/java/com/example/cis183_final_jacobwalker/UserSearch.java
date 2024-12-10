package com.example.cis183_final_jacobwalker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class UserSearch extends AppCompatActivity {

    DatabaseHelper dbHelper;

    EditText et_j_userSearch_roNum;
    EditText et_j_userSearch_hours;

    Spinner sp_j_userSearch_typeDropDown;
    Spinner sp_j_userSearch_opDropDown;

    Button btn_j_userSearch_home;
    Button btn_j_userSearch_search;

    ListView lv_j_userSearch_results;

    UserListAdapter listAdapter;

    ArrayAdapter<String> typeAdapter;
    ArrayAdapter<String> opAdapter;

    ArrayList<RequestOrder> listOfRos;

    ArrayList<String> types;
    ArrayList<String> operator;

    Intent intent_userSearch_userMain;

    int op;

    String type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_search);

        dbHelper = new DatabaseHelper(this);

        et_j_userSearch_roNum        = findViewById(R.id.et_v_userSearch_roNum);
        et_j_userSearch_hours        = findViewById(R.id.et_v_userSearch_hours);

        sp_j_userSearch_typeDropDown = findViewById(R.id.sp_v_userSearch_roType);
        sp_j_userSearch_opDropDown   = findViewById(R.id.sp_v_userSearch_op);

        btn_j_userSearch_home        = findViewById(R.id.btn_v_userSearch_home);
        btn_j_userSearch_search      = findViewById(R.id.btn_v_userSearch_search);

        lv_j_userSearch_results      = findViewById(R.id.lv_v_userSearch_results);

        listOfRos = new ArrayList<>();

        operator = new ArrayList<>(Arrays.asList("<",">"));

        opAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,operator);
        sp_j_userSearch_opDropDown.setAdapter(opAdapter);

        types = new ArrayList<>(Arrays.asList(""));
        types.addAll(dbHelper.getAllTypeNames());
        type = types.get(0);

        typeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, types);
        sp_j_userSearch_typeDropDown.setAdapter(typeAdapter);

        intent_userSearch_userMain = new Intent(UserSearch.this, UserMainPage.class);

        homeButtonListener();
        searchButtonListener();
        spinnerOpEventListener();
        spinnerTypeEventListener();

    }

    private void homeButtonListener()
    {
        btn_j_userSearch_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_userSearch_userMain);
            }
        });
    }

    private void searchButtonListener()
    {
        btn_j_userSearch_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //String technum;
                String roNum = "";
                String hours = "";

                //technum = Integer.toString(SessionData.getLoggedInTech().getTechNum());
                roNum = et_j_userSearch_roNum.getText().toString();
                hours = et_j_userSearch_hours.toString();

                Log.d("Type: " ,type);

                listOfRos = dbHelper.userSearchDatabase(roNum, hours, type, op);


                fillResults();

            }
        });
    }

    private void spinnerTypeEventListener()
    {
        sp_j_userSearch_typeDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        sp_j_userSearch_opDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        listAdapter = new UserListAdapter(this,listOfRos);
        lv_j_userSearch_results.setAdapter(listAdapter);
    }
}