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

public class UserMainPage extends AppCompatActivity {

    TextView tv_j_usermain_addRo;
    TextView tv_j_usermain_search;
    TextView tv_j_usermain_uName;
    TextView tv_j_usermain_techNum;

    ListView lv_j_usermain_listOfRos;

    Button btn_v_usermain_logout;

    Intent intent_j_usermain_add;
    Intent intent_j_usermain_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_main_page);

        tv_j_usermain_addRo     = findViewById(R.id.tv_v_usermain_addRo);
        tv_j_usermain_search    = findViewById(R.id.tv_v_usermain_searchRo);
        tv_j_usermain_uName     = findViewById(R.id.tv_v_usermain_uName);
        tv_j_usermain_techNum   = findViewById(R.id.tv_v_usermain_techNum);

        btn_v_usermain_logout     = findViewById(R.id.btn_v_usermain_logout);

        lv_j_usermain_listOfRos = findViewById(R.id.lv_v_usermain_listOfRos);

        intent_j_usermain_add   = new Intent(UserMainPage.this, UserAddRo.class);
        intent_j_usermain_logout  = new Intent(UserMainPage.this,MainActivity.class);

        addRoEventListener();
        logoutButtonListener();

    }


    private void addRoEventListener()
    {
        tv_j_usermain_addRo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
              startActivity(intent_j_usermain_add);
            }
        });
    }

    private void logoutButtonListener()
    {
        btn_v_usermain_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_j_usermain_logout);
            }
        });
    }
}