package com.example.cis183_final_jacobwalker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class UserRoDetails extends AppCompatActivity {

    DatabaseHelper dbHelper;

    TextView tv_j_userRoDetails_roNum;
    TextView tv_j_userRoDetails_roHours;
    TextView tv_j_userRoDetails_roType;
    TextView tv_j_userRoDetails_roDate;
    TextView tv_j_userRoDetails_update;

    Button btn_j_userRoDetails_home;

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
        tv_j_userRoDetails_roDate  = findViewById(R.id.tv_v_userrodetails_roDate);
        tv_j_userRoDetails_update  = findViewById(R.id.tv_v_userrodetails_update);

        btn_j_userRoDetails_home   = findViewById(R.id.btn_v_userrodetails_home);

        intent_j_userRoDetails_update   = new Intent(UserRoDetails.this,UserUpdateRo.class);
        intent_j_userRoDetails_userMain = new Intent(UserRoDetails.this, UserMainPage.class);

        dbHelper = new DatabaseHelper(this);

        updateRoEventListener();
        homeButtonListener();
        fillDetails();
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

    private void homeButtonListener()
    {
        btn_j_userRoDetails_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SessionData.setCurSelectedOrder(null);
                startActivity(intent_j_userRoDetails_userMain);
            }
        });
    }

    private void fillDetails()
    {
        tv_j_userRoDetails_roNum.setText(Integer.toString(SessionData.getCurSelectedOrder().getOrderNum()));
        tv_j_userRoDetails_roHours.setText(Float.toString(SessionData.getCurSelectedOrder().getHours()));
        tv_j_userRoDetails_roType.setText(dbHelper.getTypeName(SessionData.getCurSelectedOrder().getTypeId()));
        tv_j_userRoDetails_roDate.setText(SessionData.getCurSelectedOrder().getDate());
    }
}