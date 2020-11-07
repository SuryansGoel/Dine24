package com.example.suryansgoel.dine24;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    SQLiteDatabase db;
    EditText et1,et2,et3,et4;
    CheckBox cb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db=openOrCreateDatabase("DataBase",MODE_PRIVATE,null);
        et1= findViewById(R.id.editText1);
        et2= findViewById(R.id.editText2);
        et3= findViewById(R.id.editText3);
        et4= findViewById(R.id.editText4);
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

    public void shwpass(View v){
        cb=findViewById(R.id.checkBox2);
        if(cb.isChecked())
            et4.setInputType(97);
        else
            et4.setInputType(129);
    }

    public void signup(View v){
        int flag=0,complete=1;

        String name=et1.getText().toString();
        String phone=et2.getText().toString();
        String email=et3.getText().toString();
        String pass=et4.getText().toString();
        if(name.equals("")) {
            et1.setError("This field cannot be empty");
            complete = 0;
        }
        if(phone.equals("")) {
            et2.setError("This field cannot be empty");
            complete = 0;
        }
        if(email.equals("")) {
            et3.setError("This field cannot be empty");
            complete = 0;
        }
        if(pass.equals("")) {
            et4.setError("This field cannot be empty");
            complete = 0;
        }
        if(pass.length()<8 && complete==1){
            complete=0;
            et4.setError("Password must contain atleast 6 characters");
        }

        if(complete==1) {
            Cursor cur = db.rawQuery("select * from logindata", null);
            if (cur.moveToFirst()) {
                cur.moveToFirst();
                do {
                    String cmail = cur.getString(cur.getColumnIndex("email"));
                    if (cmail.equalsIgnoreCase(email)) {
                        flag = 1;
                        break;
                    }
                } while (cur.moveToNext());
            }
            if (flag == 0) {
                String sq = "'" + email + "','" + pass + "','" + name + "','" + phone + "'";
                db.execSQL("insert into logindata values(" + sq + ")");
                db.execSQL("create table if not exists "+email+"(rest_name varchar, seats varchar, time varchar)");
                Toast.makeText(getApplicationContext(), "Successfully Registered", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(getApplicationContext(), SignInActivity.class);
                        startActivity(i);
                        finish();
                    }
                }, 1500);

            } else
                Toast.makeText(getApplicationContext(), "Already Registered", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
        finish();
    }
}
