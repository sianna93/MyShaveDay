package com.example.kleberstevendiazcoello.myshaveoftheday;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

/**
 * Created by kleberstevendiazcoello on 14/9/15.
 */
public class Add_article extends ActionBarActivity {
    DatabaseManager Manager ;
    EditText name_article;
    EditText date_article;
    EditText price_article;
    EditText lastused_article;
    CheckBox disponsable_article;
    CheckBox consumable_article;
    ImageButton save_article;
    ImageView articlesImageview;
    String type_ar = "null";
    Spinner spi_cat;
    Spinner spi_type;
    Cursor cursor_cat , cursor_type , cat , typ;
    String str_cat , str_type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_article);
        Manager = new DatabaseManager(this);
        articlesImageview = (ImageView) findViewById(R.id.imageViewArticles);
        name_article = (EditText) findViewById(R.id.txtname_a);
        date_article = (EditText) findViewById(R.id.txtdate);
        price_article = (EditText) findViewById(R.id.txtprice);
        lastused_article = (EditText) findViewById(R.id.txtlastused);
        spi_cat = (Spinner)findViewById(R.id.spin_add_Category);
        spi_type = (Spinner)findViewById(R.id.spin_add_type);
        //disponsable_article = (CheckBox) findViewById(R.id.checkdisponsble);
        //consumable_article = (CheckBox) findViewById(R.id.checkconsumable);
        save_article = (ImageButton) findViewById(R.id.image_save);

        cursor_cat = Manager.cargarcursor_Category();
        String[] from = new String[]{Manager.cat_nombre};
        int[] to = new int[]{android.R.id.text1};

        SimpleCursorAdapter mAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_spinner_item, cursor_cat, from, to,0);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spi_cat = (Spinner)findViewById(R.id.spin_add_Category);
        spi_cat.setAdapter(mAdapter);

        spi_cat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) spi_cat.getSelectedItem();
                String cat_string = cursor.getString(cursor.getColumnIndex("nombre_categoria"));

                cat = Manager.getcategoryID(cat_string);
                if (cat.moveToFirst()) {
                    //Recorremos el cursor hasta que no haya más registros
                    do {
                        str_cat = cat.getString(0);


                    } while (cat.moveToNext());
                }

                //Toast.makeText(getBaseContext(), str_cat, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        cursor_type = Manager.cargarcursor_type();
        String[] from2 = new String[]{Manager.type_nombre};
        int[] to2 = new int[]{android.R.id.text1};

        SimpleCursorAdapter mAdaptertype = new SimpleCursorAdapter(this, android.R.layout.simple_spinner_item, cursor_type, from2, to2,0);
        mAdaptertype.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spi_type = (Spinner)findViewById(R.id.spin_add_type);
        spi_type.setAdapter(mAdaptertype);

        spi_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) spi_type.getSelectedItem();
                String type_string = cursor.getString(cursor.getColumnIndex("type_name"));

                typ = Manager.gettypeID(type_string);
                if (typ.moveToFirst()) {
                    //Recorremos el cursor hasta que no haya más registros
                    do {
                        str_type = typ.getString(0);


                    } while (typ.moveToNext());
                }

                //Toast.makeText(getBaseContext(), str_type, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        save_article.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category_str = spi_cat.getSelectedItem().toString();


                //
                String name = name_article.getText().toString();
                String date = date_article.getText().toString();
                String price = price_article.getText().toString();
                String lastused = lastused_article.getText().toString();

                /*ARREGLA ESTO*/
                /*if(disponsable_article.isChecked()){
                    type_ar = "disponsable";
                }
                if(consumable_article.isChecked()){
                    type_ar = "consumable";
                }*/

                Double pricedouble =Double.parseDouble(price);

                //Toast.makeText(getBaseContext(), ""+str_type+""+str_cat+"", Toast.LENGTH_LONG).show();
                Manager.Insertar_Article(name,pricedouble,date,lastused,str_type,str_cat);
                //
                regresar();

            }
        });


        articlesImageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Article Image"),1);


            }
        });




    }

    public void onActivityResult(int reqCode,int resCode , Intent data){
        if(resCode == RESULT_OK){
            if(reqCode==1)
                articlesImageview.setImageURI(data.getData());
        }
    }

    private void regresar() {
        Intent i = new Intent(this,Menu_articles.class);
        startActivity(i);
    }
    @Override
    public void onBackPressed() {

        Intent i = new Intent(this,Menu_articles.class);
        startActivity(i);

    }
}
