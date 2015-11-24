package com.example.kleberstevendiazcoello.myshaveoftheday;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by kleberstevendiazcoello on 10/11/15.
 */
public class View_category extends ActionBarActivity {
    private Modify_Category mc;
    DatabaseManager Manager ;
    Cursor cursor_category;
    ListView lv_category;
    SimpleCursorAdapter adaptado;
    MyCustmonAdapter adpater_mio;
    private Vibrator vibrator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_category);
        mc = new Modify_Category();
        Manager = new DatabaseManager(this);
        final Vibrator vibe = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE) ;
        lv_category = (ListView)findViewById(R.id.lv_view_category);
        cursor_category = Manager.cargarcursor_Category();
        String[] from = new String[]{Manager.cat_nombre};
        int[] to = new int[]{android.R.id.text1};
        adaptado = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_1,cursor_category,from,to,0);
        lv_category.setAdapter(adaptado);
        lv_category.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                vibe.vibrate(50);
                final String text = (String) ((TextView) view).getText();

                final String Opciones[] = new String[]{"Modify", "Delete"};
                String pick = "Pick a Option";
                AlertDialog.Builder builder = new AlertDialog.Builder(View_category.this);
                builder.setTitle("Pick a Option")
                        .setItems(Opciones, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                if (Opciones[which].equals("Modify")) {
                                    mc.set_name_category(text);
                                    Toast.makeText(getBaseContext(), "" + text + "", Toast.LENGTH_LONG).show();
                                    go_to_modificar_category();
                                }

                                if (Opciones[which].equals("Delete")) {
                                    AlertDialog.Builder builder_delete = new AlertDialog.Builder(View_category.this);
                                    builder_delete.setTitle("Delete Article");
                                    builder_delete.setMessage("Are you sure that you want to the delete it ?");
                                    builder_delete.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            Manager.Delete_category(text);
                                            Toast.makeText(getBaseContext(), "Delete Completed!", Toast.LENGTH_LONG).show();
                                            refrescar();
                                        }
                                    });
                                    builder_delete.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText(getBaseContext(), "Click No ", Toast.LENGTH_LONG).show();
                                        }
                                    });

                                    AlertDialog alertDialog = builder_delete.create();
                                    alertDialog.show();
                                }

                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();


                return false;
            }
        });


    }


    public void refrescar(){
        Intent i = new Intent(this,View_category.class);
        startActivity(i);

    }


    public void go_to_modificar_category(){
        Intent i = new Intent(this,Modify_Category.class);
        startActivity(i);

    }

    @Override
    public void onBackPressed() {

        Intent i = new Intent(this,Menu_Category.class);
        startActivity(i);

    }


}
