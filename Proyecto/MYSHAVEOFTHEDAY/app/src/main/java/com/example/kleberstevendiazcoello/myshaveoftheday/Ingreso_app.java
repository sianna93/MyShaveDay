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
public class Ingreso_app extends ActionBarActivity {
    DatabaseManager Manager ;
    private String[] menu ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingreso_app);
        Manager = new DatabaseManager(this);
        menu = new String[]{"Articles","Category","Historial"};

        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,menu);
        ListView lv = (ListView) findViewById(R.id.listmenue);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(menu[position].equals("Articles")){
                    abrir_ingreso_art();

                }

                if(menu[position].equals("Category")){
                    abrir_ingreso_cat();

                }


            }
        });

    }

    public void abrir_ingreso_art(){

        Intent i = new Intent(this,Menu_articles.class);
        startActivity(i);

    }

    public void abrir_ingreso_cat(){

        Intent i = new Intent(this,Menu_Category.class);
        startActivity(i);

    }
}



