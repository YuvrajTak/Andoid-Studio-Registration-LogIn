package com.aplus.registrationpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username,password,repassword;
    Button btSignIn,btSignUp;
    DBHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username=findViewById(R.id.username);
        password=findViewById(R.id.Password);
        repassword=findViewById(R.id.ReEnter);
        btSignIn=findViewById(R.id.btSignIn);
        btSignUp=findViewById(R.id.btSignUp);

        db =new DBHandler(this);


     //   if(_username.length()>6){
            btSignUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String user =username.getText().toString();
                    String pass= password.getText().toString();
                    String repass=repassword.getText().toString();

                    if(user.equals("") || pass.equals("") ||repass.equals("") ){
                        Toast.makeText(MainActivity.this,"Fill all Field",Toast.LENGTH_SHORT).show();
                    } else if (user.length() < 6) {
                        Toast.makeText(MainActivity.this,"Name Length is too Low",Toast.LENGTH_SHORT).show();

                    } else {
                        if (pass.equals(repass)) {
                            Boolean usercheckResult = db.checkusername(user);

                            if (usercheckResult == false) {
                                //  db.insertData(user, pass);

                                Boolean regResult = db.insertData(user, pass);
                                if (regResult == true) {

                                    Toast.makeText(MainActivity.this, "Registration Sucessful", Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(getApplicationContext(), MainLogIN.class);
                                    startActivity(intent);

                                } else {
                                    Toast.makeText(MainActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(MainActivity.this, "user already exist", Toast.LENGTH_SHORT).show();
                            }
                        } else
                            Toast.makeText(MainActivity.this, "Password not matched", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            btSignIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(getApplicationContext(),MainLogIN.class);
                    startActivity(intent);
                }
            });
        }









  //  }
}