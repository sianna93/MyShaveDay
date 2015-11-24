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
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by kleberstevendiazcoello on 8/11/15.
 */
public class View_Article extends ActionBarActivity {
    private Modify_Articles ma;
    DatabaseManager Manager ;
    Cursor cursor;
    SimpleCursorAdapter adapter;
    MyCustmonAdapter adpater_mio;
    private Vibrator vibrator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_article);
        Manager = new DatabaseManager(this);
        ma = new Modify_Articles();
        final Vibrator vibe = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE) ;
        cursor = Manager.cargarcursor_Articulos();
        final ListView List = (ListView) findViewById(R.id.listview_viewArticles);
        String[] from = new String[]{Manager.ar_nombre};
        int[] to = new int[]{android.R.id.text1};
        adapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_1,cursor,from,to,0);
        //adpater_mio = new MyCustmonAdapter(this,android.R.layout.simple_list_item_1,cursor,from,to,0);
        List.setAdapter(adapter);
        //List.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        List.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                vibe.vibrate(50);
                final String text = (String) ((TextView)view).getText();

                /*AlertDialog.Builder builder = new AlertDialog.Builder(View_Article.this);
                builder.setTitle("Delete Article");
                builder.setMessage("Are you sure that you want to the delete it ?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Manager.Delete_article(text);
                        Toast.makeText(getBaseContext(), "Delete Completed!", Toast.LENGTH_LONG).show();
                        refrescar();
                    }
                });builder.setNegativeButton("NO",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getBaseContext(), "Click No ", Toast.LENGTH_LONG).show();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();*/
                final String Opciones[] = new String[]{"Modify","Delete"};
                String pick = "Pick a Option";
                AlertDialog.Builder builder = new AlertDialog.Builder(View_Article.this);
                builder.setTitle("Pick a Option")
                        .setItems(Opciones, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                if(Opciones[which].equals("Modify")){
                                    ma.set_name_article(text);
                                    Toast.makeText(getBaseContext(), ""+text+"", Toast.LENGTH_LONG).show();
                                    go_to_modificar_articles();
                                }

                                if(Opciones[which].equals("Delete")){
                                    AlertDialog.Builder builder_delete = new AlertDialog.Builder(View_Article.this);
                                    builder_delete.setTitle("Delete Article");
                                    builder_delete.setMessage("Are you sure that you want to the delete it ?");
                                    builder_delete.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            Manager.Delete_article(text);
                                            Toast.makeText(getBaseContext(), "Delete Completed!", Toast.LENGTH_LONG).show();
                                            refrescar();
                                        }
                                    });builder_delete.setNegativeButton("NO",new DialogInterface.OnClickListener() {
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

       /* List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String text2 = (String) ((TextView)view).getText();
                ma.set_name_article(text2);
                Toast.makeText(getBaseContext(), ""+text2+"", Toast.LENGTH_LONG).show();
                go_to_modificar_articles();
            }
        });*/



        TabHost tabhost = (TabHost) findViewById(R.id.tabhostViewArticles);
        tabhost.setup();
        TabHost.TabSpec tabSpec = tabhost.newTabSpec("ViewArticleList");
        tabSpec.setContent(R.id.tabviewlist);
        tabSpec.setIndicator("List");
        tabhost.addTab(tabSpec);

        tabSpec = tabhost.newTabSpec("ViewArticleCalendar");
        tabSpec.setContent(R.id.tabviewcalendar);
        tabSpec.setIndicator("Calendar");
        tabhost.addTab(tabSpec);






    }


    public void refrescar(){
        Intent i = new Intent(this,View_Article.class);
        startActivity(i);

    }


    public void go_to_modificar_articles(){
        Intent i = new Intent(this,Modify_Articles.class);
        startActivity(i);

    }

    @Override
    public void onBackPressed() {

        Intent i = new Intent(this,Menu_articles.class);
        startActivity(i);

    }




}


