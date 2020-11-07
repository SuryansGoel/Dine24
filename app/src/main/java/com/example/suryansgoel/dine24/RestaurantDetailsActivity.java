package com.example.suryansgoel.dine24;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class RestaurantDetailsActivity extends AppCompatActivity {

    String restname;
    SQLiteDatabase db;
    TextView tv1 , tv2;
    String mail;
    int as;
    String time;
    EditText et1,et2,et3;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_details);
        et1=findViewById(R.id.editText3);
        et2=findViewById(R.id.editText);
        et3=findViewById(R.id.editText7);
        Bundle bn=getIntent().getExtras();
        restname=bn.getString("restname");
        int total,booked=0;
        total=bn.getInt("tot");
        mail=bn.getString("mail");
        db=openOrCreateDatabase("DataBase",MODE_PRIVATE,null);
        Cursor cur=db.rawQuery("select SUM(seats) as TOTAL from "+restname, null);
        tv1=findViewById(R.id.textView);
        tv2=findViewById(R.id.textView3);
        if(cur.moveToFirst()){
            booked=cur.getInt(cur.getColumnIndex("TOTAL"));
        }
        as=total-booked;
        tv1.setText(restname);
        tv2.setText(""+as);
    }
    @Override
    public void onBackPressed() {
        Intent i=new Intent(this,LoggedinActivity.class);
        Bundle b=new Bundle();
        b.putString("mail",mail);
        i.putExtras(b);
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
    public void cnfrm(View v){

        time=et2.getText().toString()+":"+et3.getText().toString();
        int ss;
        ss=Integer.parseInt(et1.getText().toString());
        if(ss<as) {
            String sq = "'" + mail + "'," + ss + ",'" + time + "'";
            db.execSQL("insert into "+restname+" values(" + sq + ")");
            Intent i=new Intent(this,LoggedinActivity.class);
            Bundle b=new Bundle();
            b.putString("mail",mail);
            i.putExtras(b);
            startActivity(i);
            finish();
        }
        else
            et1.setError("This many seats not available");
    }
}
