package com.example.kleberstevendiazcoello.myshaveoftheday;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TabHost;

/**
 * Created by kleberstevendiazcoello on 24/10/15.
 */
public class Shave_Record extends ActionBarActivity {
    DatabaseManager Manager ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shave_record);
        Manager = new DatabaseManager(this);
        ImageButton img_articles= (ImageButton) findViewById(R.id.imgbtn_article);
        ImageButton img_category= (ImageButton) findViewById(R.id.imgbtn_category);
        TabHost tabhost = (TabHost) findViewById(R.id.tabHost);
        tabhost.setup();
        TabHost.TabSpec tabSpec = tabhost.newTabSpec("ListShave");
        tabSpec.setContent(R.id.tabview);
        tabSpec.setIndicator("Shave");
        tabhost.addTab(tabSpec);
        tabSpec = tabhost.newTabSpec("List_S");
        tabSpec.setContent(R.id.tabviewShe);
        tabSpec.setIndicator("Listed");
        tabhost.addTab(tabSpec);


        img_articles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ingresar_menu();
            }
        });


        img_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ingresar_menu_categoria();

            }
        });

    }



    public void ingresar_menu(){
        Intent i = new Intent(this,Menu_articles.class);
        startActivity(i);
    }
    public void ingresar_menu_categoria(){
        Intent i = new Intent(this,Menu_Category.class);
        startActivity(i);
    }
    @Override
    public void onBackPressed() {

        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);

    }

}
