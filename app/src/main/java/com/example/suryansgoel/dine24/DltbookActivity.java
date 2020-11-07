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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class DltbookActivity extends AppCompatActivity implements OnItemClickListener {

    ListView lv;
    String restname[];
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dltbook);
        lv = findViewById(R.id.listView1);
        int c=0,n;
        db=openOrCreateDatabase("DataBase",MODE_PRIVATE,null);
        Cursor cur=db.rawQuery("select * from restname", null);
        if(cur.getCount()<=0) n=1;
        else n=cur.getCount();
        restname=new String[n];
        restname[0]="No Restaurant Found";
        if(cur.moveToFirst()) {
            cur.moveToFirst();
            do {
                restname[c] = cur.getString(cur.getColumnIndex("rest_name"));
                c++;
            } while (cur.moveToNext());
        }
            ArrayAdapter<?> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, restname);
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(this);
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
            this.finishAffinity();
        else if(item.getItemId()==R.id.soitem){
            Intent i=new Intent(this,MainActivity.class);
            startActivity(i);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> av, View v, int pos, long id) {
        String name=(String)av.getItemAtPosition(pos);
        Intent in=new Intent(this,SlctuserActivity.class);
        Bundle b=new Bundle();
        b.putString("restname",name);
        in.putExtras(b);
        startActivity(in);
        finish();
    }
}
