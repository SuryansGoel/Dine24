package com.example.suryansgoel.dine24;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AdminActivity extends AppCompatActivity {
    EditText et1,et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        et1=findViewById(R.id.editText1);
        et2=findViewById(R.id.editText2);
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

    public void login(View v){

        if((et1.getText().toString()).equals("Admin") && (et2.getText().toString()).equals("Admin")) {
            Intent i = new Intent(this, ControlActivity.class);
            startActivity(i);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
        finish();
    }
}
