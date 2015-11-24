package com.example.kleberstevendiazcoello.myshaveoftheday;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

/**
 * Created by kleberstevendiazcoello on 23/11/15.
 */
public class Fusion_Categod extends ActionBarActivity {
    private Modify_Category mc;
    DatabaseManager Manager ;
    Cursor cursor_category;
    ListView lv_category_merge;
    SimpleCursorAdapter adaptado;
    MyCustmonAdapter adpater_mio;
    ImageButton button_merge;
    private Vibrator vibrator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fusion_category);
        mc = new Modify_Category();
        Manager = new DatabaseManager(this);
        final Vibrator vibe = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE) ;
        lv_category_merge = (ListView)findViewById(R.id.lv_merge_cate);
        button_merge = (ImageButton) findViewById(R.id.img_btn_merge);
        cursor_category = Manager.cargarcursor_Category();
        String[] from = new String[]{Manager.cat_nombre};
        int[] to = new int[]{android.R.id.text1};
        adaptado = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_multiple_choice,cursor_category,from,to,0);
        lv_category_merge.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lv_category_merge.setAdapter(adaptado);
        lv_category_merge.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {

            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }
        });



        button_merge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder_delete = new AlertDialog.Builder(Fusion_Categod.this);
                builder_delete.setTitle("Merge Article");
                builder_delete.setMessage("Are you sure that you want to the Merge this Articles ?");
                builder_delete.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        Toast.makeText(getBaseContext(), "Merge Suceess", Toast.LENGTH_LONG).show();
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
        });






    }


    public void refrescar(){
        Intent i = new Intent(this,Fusion_Categod.class);
        startActivity(i);

    }




    @Override
    public void onBackPressed() {

        Intent i = new Intent(this,Menu_Category.class);
        startActivity(i);

    }



}
