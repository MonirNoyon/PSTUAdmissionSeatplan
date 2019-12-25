package com.example.externaldatabasedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class Myclass extends SQLiteOpenHelper {

    public static  String dbName="seatplan";
    public static int dbVersion= 2;
    public static String dbPath="";
    Context myContext;

    public Myclass( Context context) {
        super(context,dbName,null,dbVersion);

        myContext=context;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    private boolean ExistDatabase() {
        File myfile = new File(dbPath+dbName);
        return myfile.exists();
    }
    private void CopyDatabase(){
        try{
            InputStream myinput= myContext.getAssets().open(dbName);
            OutputStream myoutput = new FileOutputStream(dbPath+dbName);

            byte[] mybuffer = new byte[1024];
            int length;
            while ((length=myinput.read(mybuffer))>0)
            {
                myoutput.write(mybuffer,0,length);
            }
            myoutput.flush();
            myoutput.close();
            myoutput.close();
        }
        catch (Exception ex)
        {

        }
    }
    public void startWork(){
        dbPath= myContext.getFilesDir().getParent()+"/databases/";
        if (!ExistDatabase())
        {
            this.getWritableDatabase();
            CopyDatabase();
        }
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            db.disableWriteAheadLogging();
        }
    }
}
