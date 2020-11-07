package com.example.suryansgoel.dine24;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class ControlActivity extends AppCompatActivity {

    boolean flag=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);
    }
    @Override
    public void onBackPressed() {
        if (flag) {
            Intent i=new Intent(this,MainActivity.class);
            startActivity(i);
            finish();
            return;
        }
        flag=true;
        Toast.makeText(this, "Please click BACK again to Sign Out", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                flag=false;
            }
        }, 2000);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.exititem){
            finish();
        }
        else if(item.getItemId()==R.id.soitem){
            Intent i=new Intent(this,MainActivity.class);
            startActivity(i);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void clearbook(View v){
        Intent i1=new Intent(this,DltbookActivity.class);
        startActivity(i1);
        finish();
    }

    public void nxtpg(View v){
        Intent i1=new Intent(this,AddresActivity.class);
        startActivity(i1);
        finish();
    }
}