package com.example.kleberstevendiazcoello.myshaveoftheday;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by kleberstevendiazcoello on 15/9/15.
 */
public class Menu_Category extends ActionBarActivity {
    DatabaseManager Manager ;
    private String[] menu_category ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_category);
        Manager = new DatabaseManager(this);
        menu_category = new String[]{"Add","Fusion","View"};

        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,menu_category);
        ListView lv = (ListView) findViewById(R.id.lst_category);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(menu_category[position].equals("Add")){
                    abrir_ingreso_add_category();

                }
                if(menu_category[position].equals("View")){
                    abrir_ingreso_view_category();

                }
                if(menu_category[position].equals("Fusion")){
                    abrir_ingreso_view_Fusion();

                }
            }
        });

    }

    public void abrir_ingreso_add_category(){

        Intent i = new Intent(this,Add_Category.class);
        startActivity(i);

    }
    public void abrir_ingreso_view_category(){

        Intent i = new Intent(this,View_category.class);
        startActivity(i);

    }
    public void abrir_ingreso_view_Fusion(){

        Intent i = new Intent(this,Fusion_Categod.class);
        startActivity(i);

    }

    @Override
    public void onBackPressed() {

        Intent i = new Intent(this,Shave_Record.class);
        startActivity(i);

    }
}
