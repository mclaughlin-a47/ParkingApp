package example.com;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

    public class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(@Nullable Context context) {
            super(context, "Login.db", null, 1);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("Create table user(email text primary key, password text)");
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists user");
        }
        //insert into database
        public boolean insert(String email, String password){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("email", email);
            contentValues.put("password", password);
            Long ins =db.insert("user", null, contentValues);
            if(ins==-1) return false;
            else return true;
        }
        //check if email exists
        public Boolean checkEmail(String email){

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("Select * from user where email=?", new
                    String[]{email});

                if(cursor.getCount()>0 ) return false;
                else return true;
        }
        //check if Password is correct
        public Boolean checkPassword(String Email, String password){
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("Select * from user where email=? and password=?", new String[]{Email, password});
            if(cursor.getCount()>0) return true;
            else return false;
        }
    }
