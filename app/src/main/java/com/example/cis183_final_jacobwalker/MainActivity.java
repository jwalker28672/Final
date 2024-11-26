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

public class MainActivity extends AppCompatActivity {

    EditText et_j_main_uName;
    EditText ept_j_main_password;

    Button btn_j_main_login;

    Intent intent_j_userPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        et_j_main_uName     = findViewById(R.id.et_v_main_uName);
        ept_j_main_password = findViewById(R.id.ept_v_main_password);

        btn_j_main_login    = findViewById(R.id.btn_v_main_login);

        intent_j_userPage   = new Intent(MainActivity.this, UserMainPage.class);

        loginButtonListener();
    }


    private void loginButtonListener()
    {
        btn_j_main_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_j_userPage);
            }
        });
    }
}