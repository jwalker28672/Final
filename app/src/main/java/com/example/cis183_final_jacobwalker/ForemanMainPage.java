package com.example.cis183_final_jacobwalker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ForemanMainPage extends AppCompatActivity {

    TextView tv_j_foremanMain_uName;
    TextView tv_j_foremanMain_techNum;
    TextView tv_j_foremanMain_Add;
    TextView tv_j_foremanMain_search;

    Button btn_j_foremanMain_logout;

    ListView lv_j_foremanMain_listOfRos;

    Intent intent_j_foremanMain_logout;

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

        intent_j_foremanMain_logout = new Intent(ForemanMainPage.this,MainActivity.class);



    }

    private void logoutButtonListener()
    {
        btn_j_foremanMain_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_j_foremanMain_logout);
            }
        });
    }
}