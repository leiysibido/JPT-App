package com.example.translator;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static  DatabaseAccess instance;
    Cursor c = null;

    private  DatabaseAccess(Context context){
        this.openHelper=new DatabaseOpenHelper(context);

    }

    public static DatabaseAccess getInstance(Context context){
        if (instance==null){
            instance=new DatabaseAccess(context);
        }
        return instance;
    }

    public void open(){
        this.db=openHelper.getWritableDatabase();
    }
    public void close(){
        if (db!=null){
            this.db.close();
        }
    }
    public String getDefinition(String salita){
        c=db.rawQuery("select Japanese from Language where English = '"+salita+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String definition = c.getString(0);
            buffer.append(""+definition);
        }
        return  buffer.toString();
    }
}
