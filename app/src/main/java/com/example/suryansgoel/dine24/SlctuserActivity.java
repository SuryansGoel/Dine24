package com.example.suryansgoel.dine24;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SlctuserActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    String restname;
    SQLiteDatabase db;
    ListView lv;
    String custname[];
    String str[];
    protected void onCreate(Bundle savedInstanceState) {
        Bundle b=getIntent().getExtras();
        restname=b.getString("restname");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slctuser);
        lv = findViewById(R.id.listView1);
        int c=0,n;
        db=openOrCreateDatabase("DataBase",MODE_PRIVATE,null);
        Cursor cur=db.rawQuery("select * from "+restname, null);
        if(cur.getCount()<=0) n=1;
        else n=cur.getCount();
        custname=new String[n];
        str=new String[n];
        str[0]="No Booking Found";
        if(cur.moveToFirst()) {
            cur.moveToFirst();
            do {
                custname[c] = cur.getString(cur.getColumnIndex("email"));
                str[c] = "mail:" + custname[c] + " seats:" + cur.getInt(cur.getColumnIndex("seats")) +
                        " time:" + cur.getString(cur.getColumnIndex("time"));
                c++;
            } while (cur.moveToNext());
        }
            ArrayAdapter<?> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, str);
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(this);
    }
    @Override
    public void onBackPressed() {
        Intent i=new Intent(this,DltbookActivity.class);
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

    @Override
    public void onItemClick(AdapterView<?> av, View v, int pos, long id) {
        db.execSQL("delete from "+restname+" where email='"+custname[pos]+"'");
        db.delete(custname[pos],"rest_name='"+restname+"'",null);
    }
}
