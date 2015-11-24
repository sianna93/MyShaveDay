package com.example.kleberstevendiazcoello.myshaveoftheday;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by kleberstevendiazcoello on 10/11/15.
 */
public class Modify_Category extends ActionBarActivity {

    Cursor c;
    DatabaseManager Manager;
    EditText  editname ,descriptionedit;
    ImageButton btn_modify_cate;
    String mod_name,mod_des;
    String str_des, str_name;

    private static String Name_category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_category);
        Manager = new DatabaseManager(this);
        btn_modify_cate = (ImageButton) findViewById(R.id.btn_modify_category);
        editname = (EditText) findViewById(R.id.edttxt_name_cat_mod);
        descriptionedit = (EditText) findViewById(R.id.edttxt_des_cat_mod);
        c = Manager.getCategoryDes(Name_category);
        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                str_name = c.getString(0);
                str_des = c.getString(1);

            } while (c.moveToNext());
        }

        editname.setText(str_name);
        descriptionedit.setText(str_des);


        btn_modify_cate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mod_name = editname.getText().toString();
                mod_des = descriptionedit.getText().toString();

                Manager.modify_category(mod_name,mod_des,Name_category);
                Toast.makeText(getBaseContext(), "Modify Succees", Toast.LENGTH_LONG).show();
                return_view_catecory();


            }
        });



    }
    public void set_name_category(String name){
        this.Name_category=name;

    }
    public void return_view_catecory(){
        Intent i = new Intent(this,Modify_Category.class);
        startActivity(i);

    }
    @Override
    public void onBackPressed() {

        Intent i = new Intent(this,View_category.class);
        startActivity(i);

    }
}