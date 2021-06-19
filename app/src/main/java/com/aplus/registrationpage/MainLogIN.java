package com.aplus.registrationpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainLogIN extends AppCompatActivity {

    EditText username,password;
    Button btLogin;

    DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_log_i_n);

        username=findViewById(R.id.log_user);
        password=findViewById(R.id.log_pass);
        btLogin=findViewById(R.id.btLog);

        db=new DBHandler(this);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=username.getText().toString();
                String pass=password.getText().toString();

                if(user.equals("")||pass.equals("")){
                    Toast.makeText(MainLogIN.this, "Enter the Crediantials", Toast.LENGTH_SHORT).show();
                }
                else {
                  Boolean result=  db.checkUsernamePassword(user,pass);
                  if(result==true){
                      Intent intent= new Intent(getApplicationContext(),HomePage.class);
                      startActivity(intent);

                  }
                  else {
                      Toast.makeText(MainLogIN.this, "Invalid Crediantials", Toast.LENGTH_SHORT).show();
                  }
                }
            }
        });

    }
}