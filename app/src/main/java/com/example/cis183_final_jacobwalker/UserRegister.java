package com.example.cis183_final_jacobwalker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UserRegister extends AppCompatActivity {

    DatabaseHelper dbHelper;

    EditText et_j_register_techNum;
    EditText et_j_register_uName;
    EditText et_j_register_fName;
    EditText et_j_register_lName;
    EditText et_j_register_password;

    Button btn_j_register_cancel;
    Button btn_j_register_register;

    Intent intent_j_register_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_register);

        et_j_register_techNum   = findViewById(R.id.et_v_register_techNum);
        et_j_register_uName     = findViewById(R.id.et_v_register_uName);
        et_j_register_fName     = findViewById(R.id.et_v_register_fName);
        et_j_register_lName     = findViewById(R.id.et_v_register_lName);
        et_j_register_password  = findViewById(R.id.et_v_register_password);

        btn_j_register_register = findViewById(R.id.btn_v_register_register);
        btn_j_register_cancel   = findViewById(R.id.btn_v_register_cancel);

        intent_j_register_main = new Intent(UserRegister.this, MainActivity.class);

        dbHelper = new DatabaseHelper(this);

        cancelButtonListener();
        registerButtonListener();

    }

    private void cancelButtonListener()
    {
        btn_j_register_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_j_register_main);
            }
        });
    }

    private void registerButtonListener()
    {
        btn_j_register_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(registeruser())
                {
                    startActivity(intent_j_register_main);
                }
            }
        });
    }

    private boolean registeruser()
    {
        int techNum = 0;
        String uName = "";
        String fName = "";
        String lName = "";
        String password = "";
        boolean foreman = false;


        techNum = Integer.parseInt(et_j_register_techNum.getText().toString());
        uName = et_j_register_uName.getText().toString();
        fName = et_j_register_fName.getText().toString();
        lName = et_j_register_lName.getText().toString();

        if(techNum !=0 && !uName.isEmpty() && !fName.isEmpty() && !lName.isEmpty() && !password.isEmpty())
        {
            if(!dbHelper.checkUserExists(uName))
            {
                Tech t = new Tech();

                t.setTechNum(techNum);
                t.setuName(uName);
                t.setfName(fName);
                t.setlName(lName);
                t.setPassword(password);
                t.setForeman(foreman);

                dbHelper.addTech(t);

                return true;
            }
        }


        return false;
    }
}