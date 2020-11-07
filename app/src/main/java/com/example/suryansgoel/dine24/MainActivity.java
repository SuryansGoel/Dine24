package com.example.suryansgoel.dine24;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Intent i;
    boolean flag=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SQLiteDatabase db=openOrCreateDatabase("DataBase",MODE_PRIVATE,null);
        db.execSQL("create table if not exists logindata(email varchar,password varchar,cust_name varchar,phone varchar)");
        db.execSQL("create table if not exists restname(rest_name varchar,city varchar,totseat int)");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.exititem)
            finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (flag) {
            super.onBackPressed();
            return;
        }

        this.flag = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                flag=false;
            }
        }, 2000);
    }

    public void register(View v){
        i=new Intent(this,RegisterActivity.class);
        startActivity(i);
        finish();
    }
    public void login(View v){
        i=new Intent(this,SignInActivity.class);
        startActivity(i);
        finish();
    }
    public void admin(View v){
        i=new Intent(this,AdminActivity.class);
        startActivity(i);
        finish();
    }
}
