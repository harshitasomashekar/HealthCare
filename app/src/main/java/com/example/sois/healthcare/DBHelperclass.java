package com.example.sois.healthcare;

    import android.content.ContentValues;
    import android.content.Context;
    import android.database.Cursor;
    import android.database.sqlite.SQLiteDatabase;
    import android.database.sqlite.SQLiteOpenHelper;
    import android.util.Log;
    import android.widget.ListView;
    import android.widget.Toast;

    import java.util.ArrayList;

class DBhelperclass extends SQLiteOpenHelper {
    SQLiteDatabase db;
    Cursor c;
    public static final String DATABASE_NAME = "register.db";
    public static final String TABLE_NAME = "register";
    public static final String COL_1 = "NAME";
    public static final String COL_2 = "AGE";
    public static final String COL_3 = "GENDER";
    public static final String COL_4 = "HEIGHT";
    public static final String COL_5 = "WEIGHT";
    public static final String COL_6 = "BLOOD_GROUP";
    public static final String COL_7 = "FREE_TIME";
    public static final String COL_8 = "EMAIL_ID";
    public static final String COL_9 = "PASSWORD";


    public DBhelperclass(Context context) {
        super(context, DATABASE_NAME, null, 2);
        SQLiteDatabase db = this.getWritableDatabase();


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(NAME TEXT,AGE TEXT,GENDER TEXT,HEIGHT TEXT,WEIGHT TEXT,BLOOD_GROUP TEXT,FREE_TIME TEXT,EMAIL_ID TEXT,PASSWORD TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public boolean insertdata(String NAME, String AGE, String GENDER, String HEIGHT, String WEIGHT, String BLOOD_GROUP, String FREETIME, String EMAIL_ID, String PASSWORD) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_1, NAME);
        values.put(COL_2, AGE);
        values.put(COL_3, GENDER);
        values.put(COL_4, HEIGHT);
        values.put(COL_5, WEIGHT);
        values.put(COL_6, BLOOD_GROUP);
        values.put(COL_7, FREETIME);
        values.put(COL_8, EMAIL_ID);
        values.put(COL_9, PASSWORD);
        db.insert(TABLE_NAME, null, values);
        long result = db.insert(TABLE_NAME, null, values);
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean validate(String Email, String Password) {
        Log.v("validate",Email);
        Log.v("validate",Password);
        SQLiteDatabase db = this.getReadableDatabase();
        c = db.rawQuery(" SELECT * FROM register  WHERE EMAIL_ID =? AND  PASSWORD=?", new String[]{Email, Password});
        if (c != null) {
            if (c.getCount() > 0) {
                c.moveToNext();
                Log.d("mytag", "LOGGED IN");
                return true;

            } else {
                Log.d("mytag", "this is my tag");
            }
        }
        return false;
    }



    public String[] fetchall(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT  * FROM register  WHERE NAME='" + name + "'";
        //c = db.rawQuery(" SELECT COL_2 FROM register where COL_1 = ?" , new String[]{name});

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            String[] res = new String[8];
            res[0] = cursor.getString(cursor.getColumnIndex("NAME"));
            res[1] = cursor.getString(cursor.getColumnIndex("AGE"));
            res[2] = cursor.getString(cursor.getColumnIndex("GENDER"));
            res[3] = cursor.getString(cursor.getColumnIndex("HEIGHT"));
            res[4] = cursor.getString(cursor.getColumnIndex("WEIGHT"));
            res[5] = cursor.getString(cursor.getColumnIndex("BLOOD_GROUP"));
            cursor.close();
            return res;
        } else {
            cursor.close();
            return null;
        }
    }




}


