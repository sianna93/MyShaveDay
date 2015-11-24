package com.example.kleberstevendiazcoello.myshaveoftheday;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by kleberstevendiazcoello on 14/9/15.
 */
public class Menu_articles extends ActionBarActivity {

    DatabaseManager Manager ;
    private String[] menu_articles ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_articles);
        Manager = new DatabaseManager(this);
        menu_articles = new String[]{"View","Add"};

        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,menu_articles);
        ListView lv = (ListView) findViewById(R.id.lstarticles);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(menu_articles[position].equals("Add")){
                    abrir_ingreso_art();

                }
                if(menu_articles[position].equals("View")){
                    abrir_ingreso_view();

                }
            }
        });

    }

    public void abrir_ingreso_art(){

        Intent i = new Intent(this,Add_article.class);
        startActivity(i);

    }
    public void abrir_ingreso_view(){

        Intent i = new Intent(this,View_Article.class);
        startActivity(i);

    }

    @Override
    public void onBackPressed() {

        Intent i = new Intent(this,Shave_Record.class);
        startActivity(i);

    }


}
