package com.example.suryansgoel.dine24;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddresActivity extends AppCompatActivity {

    SQLiteDatabase db;
    EditText et1,et2,et3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addres);
        db=openOrCreateDatabase("DataBase",MODE_PRIVATE,null);
    }
    @Override
    public void onBackPressed() {
        Intent i=new Intent(this,ControlActivity.class);
        startActivity(i);
        finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.exititem)
            finish();
        else if(item.getItemId()==R.id.soitem){
            Intent i=new Intent(this,MainActivity.class);
            startActivity(i);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void adre(View v){
        et1=findViewById(R.id.editText1);
        et2=findViewById(R.id.editText5);
        et3=findViewById(R.id.editText6);
        String rn=et1.getText().toString();
        String city=et2.getText().toString();
        int totseats=Integer.parseInt(et3.getText().toString());
        db.execSQL("create table if not exists "+rn+"(email varchar,seats int,time varchar)");
        String sq = "'" + rn + "','" + city + "',"+totseats;
        db.execSQL("insert into restname values("+sq+")");
        Toast.makeText(getApplicationContext(), "Successfully Added", Toast.LENGTH_SHORT).show();
    }
}
