package com.example.sois.healthcare;


        import android.content.Intent;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

public class  MainActivity extends AppCompatActivity {
    DBhelperclass db;
    EditText et1, et2;
    Button bt1;
    TextView textView;
    String Email, Password;
    Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DBhelperclass(getApplicationContext());

        et1 =  findViewById(R.id.et1);
        et2 =  findViewById(R.id.et2);
        bt1 =  findViewById(R.id.btlogin);
        textView =  findViewById(R.id.textView);
        //insert.setOnClickListener((View.OnClickListener) this);
        //next.setOnClickListener((View.OnClickListener) this);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String Email = et1.getText().toString();
                String Password = et2.getText().toString();
                boolean validate=db.validate(Email,Password);
                if(validate) {
                    dash();
                }
                else{
                    Toast.makeText(getApplicationContext(),"PLEASE ENTER THE VALID ID",Toast.LENGTH_LONG).show();
                }

            }


        });


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(i);
            }


        });


    }


    public void dash() {
        Intent i = new Intent(getApplicationContext(), Main3Activity.class);
        startActivity(i);
    }



}
