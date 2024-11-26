package com.example.cis183_final_jacobwalker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class UserUpdateRo extends AppCompatActivity {

    EditText et_j_userUpDateRo_roNum;
    EditText et_j_userUpDateRo_roHours;
    EditText et_j_userUpDateRo_roDesc;
    EditText et_j_userUpDateRo_roDate;

    Spinner sp_j_userUpDateRo_roTypeDropDown;

    Button btn_j_userUpdateRo_cancel;
    Button btn_j_userUpdateRo_update;

    Intent intent_j_userUpdateRo_userDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_update_ro);

        et_j_userUpDateRo_roNum          = findViewById(R.id.et_v_userupdatero_roNum);
        et_j_userUpDateRo_roHours        = findViewById(R.id.et_v_userupdatero_roHours);
        et_j_userUpDateRo_roDesc         = findViewById(R.id.et_v_userupdatero_roDesc);
        et_j_userUpDateRo_roDate         = findViewById(R.id.et_v_userupdatero_roDate);

        sp_j_userUpDateRo_roTypeDropDown = findViewById(R.id.sp_v_userupdatero_roTypeDropDown);

        btn_j_userUpdateRo_cancel = findViewById(R.id.btn_v_userupdatero_cancel);
        btn_j_userUpdateRo_update = findViewById(R.id.btn_v_userupdatero_update);

        intent_j_userUpdateRo_userDetails = new Intent(UserUpdateRo.this, UserRoDetails.class);

        cancelButtonListener();
        updateButtonListener();

    }

    private void cancelButtonListener()
    {
        btn_j_userUpdateRo_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_j_userUpdateRo_userDetails);
            }
        });
    }

    private void updateButtonListener()
    {
        btn_j_userUpdateRo_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_j_userUpdateRo_userDetails);
            }
        });
    }

}