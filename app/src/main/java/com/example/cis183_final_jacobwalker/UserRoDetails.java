package com.example.cis183_final_jacobwalker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UserRoDetails extends AppCompatActivity {

    TextView tv_j_userRoDetails_roNum;
    TextView tv_j_userRoDetails_roHours;
    TextView tv_j_userRoDetails_roType;
    TextView tv_j_userRoDetails_roDesc;
    TextView tv_j_userRoDetails_roDate;
    TextView tv_j_userRoDetails_update;

    Button btn_j_userRoDetails_back;

    Intent intent_j_userRoDetails_update;
    Intent intent_j_userRoDetails_userMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_ro_details);

        tv_j_userRoDetails_roNum = findViewById(R.id.tv_v_userrodetails_roNum);
        tv_j_userRoDetails_roHours = findViewById(R.id.tv_v_userrodetails_roHours);
        tv_j_userRoDetails_roType  = findViewById(R.id.tv_v_userrodetails_roType);
        tv_j_userRoDetails_roDesc  = findViewById(R.id.tv_v_userrodetails_roDesc);
        tv_j_userRoDetails_roDate  = findViewById(R.id.tv_v_userrodetails_roDate);
        tv_j_userRoDetails_update  = findViewById(R.id.tv_v_userrodetails_update);

        btn_j_userRoDetails_back   = findViewById(R.id.btn_v_userrodetails_back);

        intent_j_userRoDetails_update   = new Intent(UserRoDetails.this,UserUpdateRo.class);
        intent_j_userRoDetails_userMain = new Intent(UserRoDetails.this, UserMainPage.class);

        updateRoEventListener();
    }

    private void updateRoEventListener()
    {
        tv_j_userRoDetails_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_j_userRoDetails_update);
            }
        });
    }

    private void backButtonListener()
    {
        btn_j_userRoDetails_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_j_userRoDetails_userMain);
            }
        });
    }
}