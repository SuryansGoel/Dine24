package com.example.suryansgoel.dine24;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class SignInActivity extends AppCompatActivity {

    CheckBox cb;
    EditText et1,et2;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        et1=findViewById(R.id.editText1);
        et2=findViewById(R.id.editText2);
        db=openOrCreateDatabase("DataBase",MODE_PRIVATE,null);
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
        cb=findViewById(R.id.checkBox);
        if(cb.isChecked())
            et2.setInputType(97);
        else
            et2.setInputType(129);
    }

    public void login(View v){
        String email=et1.getText().toString();
        String password=et2.getText().toString();
        int flag=0;
        Cursor cur=db.rawQuery("select * from logindata", null);
        if(cur.moveToFirst()) {
            cur.moveToFirst();
            do {
                String cmail = cur.getString(cur.getColumnIndex("email"));
                if (cmail.equalsIgnoreCase(email)) {
                    flag = 1;
                    String cpass = cur.getString(cur.getColumnIndex("password"));
                    if (cpass.equals(password))
                        signedin(email);
                    else {
                        et2.setText("");
                        Toast.makeText(getApplicationContext(), "Wrong Password", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
            } while (cur.moveToNext());
        }
        if(flag==0)
            Toast.makeText(getApplicationContext(),"User Not Registered",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
        finish();
    }

    public void signedin(String email){
        Intent i=new Intent(this,LoggedinActivity.class);
        Bundle b=new Bundle();
        b.putString("mail",email);
        i.putExtras(b);
        startActivity(i);
        finish();
    }

}