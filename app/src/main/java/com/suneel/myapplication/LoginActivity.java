package com.suneel.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    TextView textView;
    EditText Email, password;
    Button btn;
    Db_helper db_helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Email = findViewById(R.id.EmailAddress);
        password = findViewById(R.id.password);
        db_helper =new Db_helper(this);
        btn = findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = Email.getText().toString();
                String password1 = password.getText().toString();
                if(email.equals("")||password1.equals("")){
                    Toast.makeText(LoginActivity.this, "Please enter details", Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean checkemailpassword = db_helper.checkemailpassword(email,password1);
                    if(checkemailpassword==true){
                        Toast.makeText(LoginActivity.this, "LoginSuccessfull", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(LoginActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        textView = findViewById(R.id.rgstr);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}