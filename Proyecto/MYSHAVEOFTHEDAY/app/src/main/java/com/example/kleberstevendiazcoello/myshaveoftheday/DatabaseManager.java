package com.example.kleberstevendiazcoello.myshaveoftheday;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by kleberstevendiazcoello on 14/9/15.
 */
public class DatabaseManager {
    public static final String TABLE_NAME="articulo";
    public static final String ar_id = "_id";
    public static final String ar_nombre = "nombre_articulo";
    public static final String ar_price = "precio_articulo";
    public static final String  ar_date = "date_articulo";
    public static final String ar_last_used = "las_used_articulo";
    public static final String fk_categoria = "fk_categoria";
    public static final String fk_type_article = "fk_type_article";



    public static final String TABLE_NAME_Categoria="categoria";
    public static final String cat_id = "_id_categoria";
    public static final String cat_nombre = "nombre_categoria";
    public static final String cat_des = "descripcion_categoria";

    public static final String TABLE_NAME_Type="type_category";
    public static final String type_id = "_id_type";
    public static final String type_nombre = "type_name";
    public static final String type_cantidad = "type_units";


    public static final String TABLE_NAME_Shave_Day="shave_day";
    public static final String shave_id = "_id_shave";
    public static final String shave_fecha = "shave_day";
    public static final String shave_descripcion = "shave_description";
    public static final String shave_comentaries = "shave_comentaries";


    public static final String TABLE_NAME_Kit_Articulos="Kit_Articulos";
    public static final String kit_fk_ar_id = "fk_articulos";
    public static final String kit_fk_shave_id = "fk_shave";






    public static final String CREATE_TABLE = "create table "+TABLE_NAME+ " ("+ar_id+" integer primary key autoincrement,"
            +ar_nombre+" text not null,"+ar_price+" double not null, "+ar_date+" text not null, "+ar_last_used+" text not null, "+fk_categoria +" integer not null, "+fk_type_article+" integer not null );";

    public static final String CREATE_TABLE_Category = "create table "+TABLE_NAME_Categoria+ " ("+cat_id+" integer primary key autoincrement,"
            +cat_nombre+" text not null,"+cat_des+" text not null );";

    public static  final String CREATE_TABLE_TYPE_CATEGORY= "create table "+TABLE_NAME_Type+" ("+type_id+" integer primary key autoincrement,"
            +type_nombre+" text not null,"+type_cantidad+" double null );";

    public static  final String CREATE_TABLE_Shave_Day= "create table "+TABLE_NAME_Shave_Day+" ("+shave_id+" integer primary key autoincrement,"
            +shave_fecha+" date not null,"+shave_descripcion+" text not null,"+shave_comentaries+" text null );";

    public static  final String CREATE_TABLE_Kit_Articulos="create table "+TABLE_NAME_Kit_Articulos+" ("+kit_fk_ar_id+"integer not null,"
            +kit_fk_shave_id+"integer not null );";

    private SQLiteDatabase db ;
    private DbHelper helper;
    public DatabaseManager(Context context) {
        helper = new DbHelper(context);
        db = helper.getWritableDatabase();
    }


    public void Insertar_Article(String Name , Double price, String date , String lastused , String type_ar, String fk_category){
        ContentValues values = new ContentValues();
        values.put(ar_nombre,Name);
        values.put(ar_price,price);
        values.put(ar_date,date);
        values.put(ar_last_used,lastused);
        values.put(fk_type_article , type_ar);
        values.put(fk_categoria , fk_category);
        db.insert(TABLE_NAME,null,values);

    }


    public void Insertar_Category(String Name , String Des,  String type_cat){
        ContentValues values = new ContentValues();
        values.put(cat_nombre,Name);
        values.put(cat_des,Des);
        db.insert(TABLE_NAME_Categoria,null,values);

    }


    public void Insertar_Shave_Day(String shaveDate,  String shaveDescription, String shaveComentaries){
        ContentValues values = new ContentValues();
        values.put(shave_fecha,shaveDate);
        values.put(shave_descripcion,shaveDescription);
        values.put(shave_comentaries,shaveComentaries);
        db.insert(TABLE_NAME_Shave_Day,null,values);

    }



    public void Insertar_type_Category(String NameType,  String cantidadType){
        ContentValues values = new ContentValues();
        values.put(type_nombre,NameType);
        values.put(type_cantidad,cantidadType);
        db.insert(TABLE_NAME_Type,null,values);

    }

    public void Insertar_Kit_Articulos(String fk_ar_id,  String fk_shave_id){
        ContentValues values = new ContentValues();
        values.put(kit_fk_ar_id,fk_ar_id);
        values.put(kit_fk_shave_id,fk_shave_id);
        db.insert(TABLE_NAME_Kit_Articulos,null,values);

    }


    public Cursor cargarcursor_Articulos(){
        //Cursor c = db.rawQuery("SELECT _id,Nombre,Numero_Clases  FROM Materias Where Nombre='Administracion'" ,null);
        //return c;
        Cursor d = db.rawQuery("SELECT _id , nombre_articulo FROM articulo ",null);
        return d;
    }


    public Cursor getArticlesDes(String Nombre_Articulo){
        Cursor d = db.rawQuery("SELECT nombre_articulo, precio_articulo, date_articulo , las_used_articulo FROM articulo Where nombre_articulo = '"+Nombre_Articulo+"'",null);
        return d;
    }


    public void Delete_article (String Nombre ){
        db.delete(TABLE_NAME, ar_nombre +"=?" ,new String[]{Nombre});
    }


    public Cursor cargarcursor_Category(){
        //Cursor c = db.rawQuery("SELECT _id,Nombre,Numero_Clases  FROM Materias Where Nombre='Administracion'" ,null);
        //return c;
        Cursor d = db.rawQuery("SELECT _id_categoria AS _id , nombre_categoria FROM categoria ",null);
        return d;
    }

    public Cursor cargarcursor_type(){
        //Cursor c = db.rawQuery("SELECT _id,Nombre,Numero_Clases  FROM Materias Where Nombre='Administracion'" ,null);
        //return c;
        Cursor d = db.rawQuery("SELECT _id_type AS _id , type_name FROM type_category ",null);
        return d;
    }




    public void Delete_category (String Nombre ){
        db.delete(TABLE_NAME_Categoria, cat_nombre +"=?" ,new String[]{Nombre});
    }

    public Cursor getCategoryDes(String Nombre_Categoria){
        Cursor d = db.rawQuery("SELECT nombre_categoria, descripcion_categoria FROM categoria Where nombre_categoria = '"+Nombre_Categoria+"'",null);
        return d;
    }



    public void modify_category(String New_Name , String New_description , String Old_Name){
        db.execSQL("UPDATE categoria SET nombre_categoria = '"+New_Name+"', descripcion_categoria = '"+ New_description +"'  WHERE nombre_categoria = '"+Old_Name+"' ");
    }



    public Cursor getcategoryID(String Nombre_Categoria){
        Cursor d = db.rawQuery("SELECT _id_categoria AS _id, nombre_categoria FROM categoria Where nombre_categoria = '"+Nombre_Categoria+"'",null);
        return d;
    }


    public Cursor gettypeID(String nombre_type){
        Cursor d = db.rawQuery("SELECT _id_type AS _id , type_name FROM type_category Where type_name = '"+nombre_type+"'",null);
        return d;
    }

}
