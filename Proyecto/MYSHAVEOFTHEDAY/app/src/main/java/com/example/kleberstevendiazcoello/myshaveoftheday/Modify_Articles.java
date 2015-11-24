package com.example.kleberstevendiazcoello.myshaveoftheday;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by kleberstevendiazcoello on 8/11/15.
 */
public class Modify_Articles extends ActionBarActivity {
    Cursor c;
    DatabaseManager Manager ;
    Double str_precio_ar ;
    String str_name_ar ,str_date_ar ,str_last_art;
    TextView date_purchase ;
    TextView last_used;
    TextView price ;
    TextView Name_articulo;
    EditText editconsu ;

    private static String Name_article;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_articles);
        Manager = new DatabaseManager(this);
        date_purchase = (TextView) findViewById(R.id.txtdate_mod);
        last_used= (TextView) findViewById(R.id.txtlast_mod);
        price = (TextView) findViewById(R.id.txtprice_mod);
        Name_articulo = (TextView) findViewById(R.id.txtname_mod);
        editconsu = (EditText) findViewById(R.id.txtused_mod);
        c = Manager.getArticlesDes(Name_article);
        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                str_name_ar= c.getString(0);
                str_precio_ar= c.getDouble(1);
                str_date_ar= c.getString(2);
                str_last_art= c.getString(3);

            } while(c.moveToNext());
        }
        String sx = String.valueOf(str_precio_ar);


        date_purchase.setText(str_date_ar);
        last_used.setText(str_last_art);
        price.setText(sx);
        Name_articulo.setText(str_name_ar);

    }

    public void set_name_article(String name){
        this.Name_article=name;

    }
    @Override
    public void onBackPressed() {

        Intent i = new Intent(this,View_Article.class);
        startActivity(i);

    }


}
