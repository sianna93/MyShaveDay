package com.example.kleberstevendiazcoello.myshaveoftheday;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

/**
 * Created by Kleber on 24/02/2015.
 */
/*
*Esta clase el MyCustomAdapater que va a extender de SimpleCursorAdapater
* Ya que al tener la app un fondo ndegro hara que los datos mostados en la lista simplemente cambien
 * a un diferente color y tamaño para asi poder ser visible para los usuarios
*
*
 */
public class MyCustmonAdapter extends SimpleCursorAdapter {
    public MyCustmonAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
    }

    /*
    *se sobrescribe el metodo getview y dentro de el se cambia
    * el color y tamaño de las letras
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View view =  super.getView(position, convertView, parent);

        TextView tv = (TextView) view.findViewById(android.R.id.text1);
        tv.setTextColor(Color.BLUE);
        tv.setTextSize(25);

        return view;
    }

}
