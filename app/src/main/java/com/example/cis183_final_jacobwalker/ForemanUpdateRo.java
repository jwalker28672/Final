package com.example.cis183_final_jacobwalker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ForemanUpdateRo extends AppCompatActivity {

    EditText et_j_foremanUpdateRo_assignedTech;
    EditText et_j_foremanUpdateRo_roNum;
    EditText et_j_foremanUpdateRo_roHours;
    EditText et_j_foremanUpdateRo_roDesc;
    EditText et_j_foremanUpdateRo_roDate;

    Spinner sp_j_foremanUpdateRo_roTypeDropDown;

    Button btn_j_foremanUpdateRo_cancel;
    Button btn_j_foremanUpdateRo_update;

    Intent intent_j_foremanUpdateRo_foremanDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_foreman_update_ro);

        et_j_foremanUpdateRo_assignedTech   = findViewById(R.id.et_v_foremanupdatero_assignedTech);
        et_j_foremanUpdateRo_roNum          = findViewById(R.id.et_v_foremanupdatero_roNum);
        et_j_foremanUpdateRo_roHours        = findViewById(R.id.et_v_foremanupdatero_roHours);
        et_j_foremanUpdateRo_roDesc         = findViewById(R.id.et_v_foremanupdatero_roDesc);
        et_j_foremanUpdateRo_roDate         = findViewById(R.id.et_v_foremanupdatero_roDate);

        sp_j_foremanUpdateRo_roTypeDropDown = findViewById(R.id.sp_v_foremanupdatero_roType);

        btn_j_foremanUpdateRo_cancel        = findViewById(R.id.btn_v_foremanupdatero_cancel);
        btn_j_foremanUpdateRo_update        = findViewById(R.id.btn_v_foremanupdatero_update);

        intent_j_foremanUpdateRo_foremanDetails = new Intent(ForemanUpdateRo.this, ForemanRoDetails.class);

        cancelButtonListener();
        updateButtonListener();

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
            public void onClick(View view) {
                startActivity(intent_j_foremanUpdateRo_foremanDetails);
            }
        });
    }
}