package com.example.sois.healthcare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.view.Gravity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.widget.Toast.makeText;

public class Main3Activity extends AppCompatActivity {
    EditText health, remedy, exercise, search;
    DBhelperclass db;
    ListView listv;
    List mylist;
    String name1;
    String age;
    String gender;
    String height;
    String weight;
    String blood;
    String freetime;
    String email;
    String password;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Toast.makeText(getApplicationContext(), "WELCOME", Toast.LENGTH_LONG).show();

        search = findViewById(R.id.editsearch);
        listv = findViewById(R.id.myList3);



        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchClick();
            }
        });
        mylist = new ArrayList<>();
        //String[] listItems = new String[]{name};
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mylist);
        listv.setAdapter(adapter);
    }





    private void searchClick() {
        adapter.clear();
        String searchInoutText = search.getText().toString();
        Toast.makeText(getApplicationContext(), searchInoutText, Toast.LENGTH_LONG).show();

        db = new DBhelperclass(getApplicationContext());
        String[] res = db.fetchall(searchInoutText);


        if (res != null) {

            Log.v("fetcehd", Arrays.toString(res));


            for (int i = 0; i < res.length; i++) {
                if (res[i] != null)
                    adapter.add(res[i]);
            }

        }

    }
}




