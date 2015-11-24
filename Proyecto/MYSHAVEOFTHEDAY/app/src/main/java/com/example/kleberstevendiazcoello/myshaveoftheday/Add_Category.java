package com.example.kleberstevendiazcoello.myshaveoftheday;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by kleberstevendiazcoello on 15/9/15.
 */
public class Add_Category extends ActionBarActivity {
    DatabaseManager Manager ;
    EditText name_category;
    EditText description_category;
    CheckBox disponsable_category;
    CheckBox consuable_category;
    CheckBox no_consuable_category;
    ImageButton save_category;
    String type_cat = "null";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_category);
        Manager = new DatabaseManager(this);
        name_category = (EditText) findViewById(R.id.txtname_cat);
        description_category = (EditText) findViewById(R.id.txtdes_cat);
        disponsable_category = (CheckBox) findViewById(R.id.chec_disp_cat);
        consuable_category = (CheckBox) findViewById(R.id.chec_cons_cat);
        no_consuable_category = (CheckBox) findViewById(R.id.chec_noco_cat);
        save_category = (ImageButton) findViewById(R.id.imgbtn_addcategory);

        save_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = name_category.getText().toString();
                String description = description_category.getText().toString();

                if(disponsable_category.isChecked()){
                    type_cat = "disponsable";
                }
                if(consuable_category.isChecked()){
                    type_cat = "consumable";
                }
                if(no_consuable_category.isChecked()){
                    type_cat = "No-consumable";
                }

                Manager.Insertar_Category(name,description,type_cat);
                Toast.makeText(getBaseContext(), "Se ha ingresado exitosamente el articulo  " + name + "", Toast.LENGTH_LONG).show();
                regresar();

            }
        });


    }

    private void regresar() {
        Intent i = new Intent(this,Menu_Category.class);
        startActivity(i);
    }
    @Override
    public void onBackPressed() {

        Intent i = new Intent(this,Menu_Category.class);
        startActivity(i);

    }
}
