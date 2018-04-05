package com.wmps.paul_mac.dbtry;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase mdb ;
    EditText name;
    EditText age;
    EditText school;
    Button submit;
    Button show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        school = findViewById(R.id.school);
        submit = findViewById(R.id.submit);
        show = findViewById(R.id.show);
        mdb  = openOrCreateDatabase("try.db",MODE_PRIVATE,null);

        mdb.execSQL("CREATE TABLE IF NOT EXISTS try(name varchar, age intger, school varchar);");


        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String i1 = name.getText().toString();
                String i2 = school.getText().toString();
                int i3 = Integer.parseInt(age.getText().toString());
                mdb.execSQL("INSERT INTO TRY VALUES('"+i1+"',"+i3+",'"+i2+"');");
                Cursor rs = mdb.rawQuery("Select * from try",null);
                rs.moveToFirst();
                String n,s;
                int a;
                ArrayList clist = new ArrayList<>();
                int count = rs.getCount();
                for(int i = 0 ; i < count ; i ++) {
                    n = rs.getString(0);
                    s = rs.getString(1);
                    a = rs.getInt(2);
                    clist.add(n+s+Integer.toString(a)+ " ");
                    Toast.makeText(getApplicationContext(),clist.toString(),Toast.LENGTH_SHORT).show();
                    rs.moveToNext();
                }

            }
        });
    }
}
