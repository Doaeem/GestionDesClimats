package com.example.gestiondesclimats;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDataClimat extends SQLiteOpenHelper {

    public static String DB_NAME = "Climat.db";
    public static String TABLE_NAME = "Climat";
    public static String COL1 = "Id";
    public static String COL2 = "NomVille";
    public static String COL3 = "Pays";
    public static String COL4 = "Temperature";
    public static String COL5 = "PourcentageNuages";

    public MyDataClimat(Context c) {
        super(c, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = " create table " + TABLE_NAME + " ( " + COL1 + " integer primary key autoincrement, " + COL2 + " text, " + COL3 + " text, " + COL4 + " integer, " + COL5 + " integer )";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = " drop table " + TABLE_NAME;
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

    public static long AddClimat(SQLiteDatabase db, Climat e){
        ContentValues content = new ContentValues();
        content.put(COL2, e.getNomVille());
        content.put(COL3, e.getPays());
        content.put(COL4, e.getTemperature());
        content.put(COL5, e.getPourcentageNuages());
        return db.insert(TABLE_NAME,null,content);
    }

    public static long UpdateClimat(SQLiteDatabase db, Climat e){
        ContentValues content = new ContentValues();
        content.put(COL2, e.getNomVille());
        content.put(COL3, e.getPays());
        content.put(COL4, e.getTemperature());
        content.put(COL5, e.getPourcentageNuages());
        return db.update(TABLE_NAME,content,"Id = " + e.getId(),null);
    }

    public static long DeleteClimat(SQLiteDatabase db, int id){
        return db.delete(TABLE_NAME,"Id = " + id,null);
    }

    public static ArrayList<Climat> getAllClimats(SQLiteDatabase sqLiteDatabase){
        ArrayList<Climat> climats = new ArrayList<>();

        Cursor cur = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME,null);

        while(cur.moveToNext()){
            Climat e = new Climat();

            e.setId(cur.getInt(0));
            e.setNomVille(cur.getString(1));
            e.setPays(cur.getString(2));
            e.setTemperature(cur.getInt(3));
            e.setPourcentageNuages(cur.getInt(4));
            climats.add(e);
        }
        return climats;
    }

    public static Climat getOneClimat(SQLiteDatabase sqLiteDatabase, int id){
        Climat climats = null;

        Cursor cur = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME + " WHERE Id = " + id,null);

        if(cur.moveToNext()){
            climats.setId(cur.getInt(0));
            climats.setNomVille(cur.getString(1));
            climats.setPays(cur.getString(2));
            climats.setTemperature(cur.getInt(3));
            climats.setPourcentageNuages(cur.getInt(4));
        }
        return climats;
    }
}
