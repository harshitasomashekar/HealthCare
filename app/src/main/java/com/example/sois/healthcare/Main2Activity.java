package com.example.sois.healthcare;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main2Activity extends AppCompatActivity {
    EditText name,age,gender,height,weight,blood,ft,email,password;
    Button finish;
    String s1,s2,s3,s4,s5,s6,s7,s8,s9;
    DBhelperclass db;
    Boolean result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        name = (EditText)findViewById(R.id.editName);
        age = (EditText)findViewById(R.id.editage);
        gender = (EditText)findViewById(R.id.editgender);
        height = (EditText)findViewById(R.id.editheight);
        weight = (EditText)findViewById(R.id.editweight);
        blood = (EditText)findViewById(R.id.editblood);
        ft = (EditText)findViewById(R.id.editfree);
        email = (EditText)findViewById(R.id.editemail);
        password = (EditText)findViewById(R.id.editpass);
        finish= (Button)findViewById(R.id.btok);
        db= new DBhelperclass(this);


        finish.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                s1 = name.getText().toString();
                s2 = age.getText().toString();
                s3 = gender.getText().toString();
                s4 = height.getText().toString();
                s5 = weight.getText().toString();
                s6 = blood.getText().toString();
                s7 = ft.getText().toString();
                s8 = email.getText().toString();
                s9 = password.getText().toString();
                signin();


                if (signin() == true) {

                    boolean result = db.insertdata(s1, s2, s3, s4, s5, s6, s7, s8, s9);
                    if (result == true)
                        Toast.makeText(getApplicationContext(), "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getApplicationContext(), "insert not successful", Toast.LENGTH_SHORT).show();

                }
            }



        });

    }

    private boolean signin() {
        if(TextUtils.isEmpty(name.getText().toString().trim()) || TextUtils.isEmpty(age.getText().toString().trim()) || TextUtils.isEmpty(gender.getText().toString().trim()) || TextUtils.isEmpty(height.getText().toString().trim()) || TextUtils.isEmpty(weight.getText().toString().trim())|| TextUtils.isEmpty(blood.getText().toString().trim()) || TextUtils.isEmpty(ft.getText().toString().trim())|| TextUtils.isEmpty(email.getText().toString().trim()) ||TextUtils.isEmpty(password.getText().toString().trim())) {
            name.setError("Fields Can't be empty");
            age.setError("Fields Can't be empty");
            gender.setError("Fields Can't be empty");
            height.setError("Fields Can't be empty");
            weight.setError("Fields Can't be empty");
            blood.setError("Fields Can't be empty");
            ft.setError("Fields Can't be empty");
            email.setError("Fields Can't be empty");
            password.setError("Fields Can't be empty");
            return false;
        }else if(!emailValidator(email.getText().toString().trim())) {
            email.setError("Please Enter Valid Email Address");
            return false;
            }else {
                 Toast.makeText(getApplicationContext(), "Registration Sucessful", Toast.LENGTH_SHORT).show();
            Intent i;
            i = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
            return true;
        }



    }
    //email Validation
    public boolean emailValidator(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z0-9]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();

    }
}
