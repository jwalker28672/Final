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

public class ForemanRoDetails extends AppCompatActivity {

    TextView tv_j_foremanRoDetails_assignedTech;
    TextView tv_j_foremanRoDetails_roNum;
    TextView tv_j_foremanRoDetails_roHours;
    TextView tv_j_foremanRoDetails_roType;
    TextView tv_j_foremanRoDetails_roDesc;
    TextView tv_j_foremanRoDetails_roDate;
    TextView tv_j_foremanRoDetails_update;

    Button btn_foremanRoDetails_home;

    Intent intent_j_foremanRoDetails_foremanMain;
    Intent intent_j_foremanRoDetails_foremanUpdateRo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_foreman_ro_details);

        tv_j_foremanRoDetails_assignedTech = findViewById(R.id.tv_v_foremanrodetails_assingedTech);
        tv_j_foremanRoDetails_roNum        = findViewById(R.id.tv_v_foremanrodetails_roNum);
        tv_j_foremanRoDetails_roHours      = findViewById(R.id.tv_v_foremanrodetails_roHours);
        tv_j_foremanRoDetails_roType       = findViewById(R.id.tv_v_foremanrodetails_roType);
        tv_j_foremanRoDetails_roDesc       = findViewById(R.id.tv_v_foremanrodetails_roDesc);
        tv_j_foremanRoDetails_roDate       = findViewById(R.id.tv_v_foremanrodetails_roDate);
        tv_j_foremanRoDetails_update       = findViewById(R.id.tv_v_foremanrodetails_update);

        btn_foremanRoDetails_home          = findViewById(R.id.btn_v_foremanrodetails_home);

        intent_j_foremanRoDetails_foremanMain     = new Intent(ForemanRoDetails.this, ForemanMainPage.class);
        intent_j_foremanRoDetails_foremanUpdateRo = new Intent(ForemanRoDetails.this, ForemanUpdateRo.class);

        updateRoEventListener();
        homeButtonListener();
    }

    private void updateRoEventListener()
    {
        tv_j_foremanRoDetails_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_j_foremanRoDetails_foremanUpdateRo);
            }
        });
    }

    private void homeButtonListener()
    {
        btn_foremanRoDetails_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_j_foremanRoDetails_foremanMain);
            }
        });
    }
}