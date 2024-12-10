package com.example.cis183_final_jacobwalker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ForemanMainPage extends AppCompatActivity {

    DatabaseHelper dbHelper;

    TextView tv_j_foremanMain_uName;
    TextView tv_j_foremanMain_techNum;
    TextView tv_j_foremanMain_Add;
    TextView tv_j_foremanMain_search;

    Button btn_j_foremanMain_logout;

    ListView lv_j_foremanMain_listOfRos;

    Intent intent_j_foremanMain_logout;
    Intent intent_j_foremanMain_addRo;
    Intent intent_j_foremanMain_roDetails;
    Intent intent_j_foremanMain_foremanSearch;

    ForemanListAdapter adapter;
    ArrayList<RequestOrder> listOfRos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_foreman_main_page);

        tv_j_foremanMain_uName   = findViewById(R.id.tv_v_foremanmain_uName);
        tv_j_foremanMain_techNum = findViewById(R.id.tv_v_foremanmain_techNum);
        tv_j_foremanMain_Add     = findViewById(R.id.tv_v_foremanmain_addRo);
        tv_j_foremanMain_search  = findViewById(R.id.tv_v_foremanmain_searchRo);

        btn_j_foremanMain_logout = findViewById(R.id.btn_V_foremanmain_logout);

        lv_j_foremanMain_listOfRos = findViewById(R.id.lv_v_foremanmain_listOfRos);

        dbHelper = new DatabaseHelper(this);

        intent_j_foremanMain_logout        = new Intent(ForemanMainPage.this,MainActivity.class);
        intent_j_foremanMain_addRo         = new Intent(ForemanMainPage.this, ForemanAddRo.class);
        intent_j_foremanMain_roDetails     = new Intent(ForemanMainPage.this, ForemanRoDetails.class);
        intent_j_foremanMain_foremanSearch = new Intent(ForemanMainPage.this, ForemanSearch.class);

        logoutButtonListener();
        setTextBoxes();
        addRoEventListener();
        searchEventListener();
        fillListView();
        listViewClickerListener();

    }

    private void listViewClickerListener()
    {
        lv_j_foremanMain_listOfRos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                SessionData.setCurSelectedOrder(listOfRos.get(i));

                startActivity(intent_j_foremanMain_roDetails);
            }
        });
    }

    private void logoutButtonListener()
    {
        btn_j_foremanMain_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                SessionData.setLoggedInTech(null);
                SessionData.setCurSelectedOrder(null);
                startActivity(intent_j_foremanMain_logout);
            }
        });
    }

    private void setTextBoxes()
    {

        tv_j_foremanMain_techNum.setText(Integer.toString(SessionData.getLoggedInTech().getTechNum()));
        tv_j_foremanMain_uName.setText(SessionData.getLoggedInTech().getuName());
    }

    private void addRoEventListener()
    {
        tv_j_foremanMain_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_j_foremanMain_addRo);
            }
        });
    }

    private void searchEventListener()
    {
        tv_j_foremanMain_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_j_foremanMain_foremanSearch);
            }
        });
    }

    private void fillListView()
    {
        listOfRos = dbHelper.getAllRos();

        adapter = new ForemanListAdapter(this, listOfRos);
        lv_j_foremanMain_listOfRos.setAdapter(adapter);
    }
}