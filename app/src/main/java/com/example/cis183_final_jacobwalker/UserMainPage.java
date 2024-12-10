package com.example.cis183_final_jacobwalker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class UserMainPage extends AppCompatActivity {

    DatabaseHelper dbHelper;

    TextView tv_j_userMain_addRo;
    TextView tv_j_userMain_search;
    TextView tv_j_userMain_uName;
    TextView tv_j_userMain_techNum;

    ListView lv_j_userMain_listOfRos;

    Button btn_v_userMain_logout;

    Intent intent_j_userMain_add;
    Intent intent_j_userMain_logout;
    Intent intent_j_userMain_roDetails;
    Intent intent_j_userMain_search;

    UserListAdapter adapter;

    ArrayList<RequestOrder> listOfRos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_main_page);

        dbHelper = new DatabaseHelper(this);

        tv_j_userMain_addRo     = findViewById(R.id.tv_v_usermain_addRo);
        tv_j_userMain_search    = findViewById(R.id.tv_v_usermain_searchRo);
        tv_j_userMain_uName     = findViewById(R.id.tv_v_usermain_uName);
        tv_j_userMain_techNum   = findViewById(R.id.tv_v_usermain_techNum);

        btn_v_userMain_logout   = findViewById(R.id.btn_v_usermain_logout);

        lv_j_userMain_listOfRos = findViewById(R.id.lv_v_usermain_listOfRos);

        intent_j_userMain_add       = new Intent(UserMainPage.this, UserAddRo.class);
        intent_j_userMain_logout    = new Intent(UserMainPage.this,MainActivity.class);
        intent_j_userMain_roDetails = new Intent(UserMainPage.this, UserRoDetails.class);
        intent_j_userMain_search    = new Intent(UserMainPage.this, UserSearch.class);

        addRoEventListener();
        searchEventListener();
        logoutButtonListener();
        setTextBoxes();
        fillListView();
        listViewClickerListener();

    }


    private void addRoEventListener()
    {
        tv_j_userMain_addRo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
              startActivity(intent_j_userMain_add);
            }
        });
    }

    private void searchEventListener()
    {
        tv_j_userMain_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_j_userMain_search);
            }
        });
    }

    private void logoutButtonListener()
    {
        btn_v_userMain_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SessionData.setLoggedInTech(null);
                SessionData.setCurSelectedOrder(null);
                startActivity(intent_j_userMain_logout);
            }
        });
    }

    private void listViewClickerListener()
    {
        lv_j_userMain_listOfRos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                SessionData.setCurSelectedOrder(listOfRos.get(i));
                startActivity(intent_j_userMain_roDetails);
            }
        });
    }


    private void setTextBoxes()
    {

        tv_j_userMain_techNum.setText(Integer.toString(SessionData.getLoggedInTech().getTechNum()));
        tv_j_userMain_uName.setText(SessionData.getLoggedInTech().getuName());
    }

    private void fillListView()
    {
        listOfRos = dbHelper.getTechRos(SessionData.getLoggedInTech().getTechNum());

        adapter = new UserListAdapter(this, listOfRos);
        lv_j_userMain_listOfRos.setAdapter(adapter);
    }
}