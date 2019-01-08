package com.example.nafsan.loginregister;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME= "userdetails.db";
    private static final String TABLE_NAME= "user_details";
    private static final String ID= "Id";
    private static final String NAME= "Name";
    private static final String EMAIL= "Email";
    private static final String USER_NAME= "user name";
    private static final String PASSWORD= "Password";
    private static final int VERSION_NUMBER= 2;
    private static final String CREATE_TABLE= "CREATE TABLE "+TABLE_NAME+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+NAME+" VARCHAR(255) NOT NULL,"+EMAIL+" TEXT NOT NULL,"+USER_NAME+"TEXT NOT NULL,"+PASSWORD+" TEXT NOT NULL)";

    private Context context;
    private static final  String DROP_TABLE= "DROP TABLE IF EXISTS "+TABLE_NAME;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try{
            db.execSQL(CREATE_TABLE);
            Toast.makeText(context,"Oncreate is called",Toast.LENGTH_LONG).show();
        }
        catch (Exception e)
        {
            Toast.makeText(context,"Exception",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(DROP_TABLE);
        try{
            db.execSQL(CREATE_TABLE);
            Toast.makeText(context,"On Upgrade is called",Toast.LENGTH_LONG).show();
        }
        catch (Exception e)
        {
            Toast.makeText(context,"Exception",Toast.LENGTH_LONG).show();
        }
    }

    public long insertData(UserDetails userDetails)
    {
        SQLiteDatabase sqLiteDatabase= this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(NAME,userDetails.getName());
        contentValues.put(EMAIL,userDetails.getEmail());
        contentValues.put(USER_NAME, userDetails.getUsername());
        contentValues.put(PASSWORD,userDetails.getPassword());

        long rowID= sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        return rowID;
    }

    public Boolean findPassword(String user,String pass){
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("Select * From "+TABLE_NAME,null);
        Boolean result= false;
        if(cursor.getCount()==0){
            Toast.makeText(context,"No data is found",Toast.LENGTH_LONG).show();

        }
        else{
            while (cursor.moveToNext())
            {
                String username= cursor.getString(3);
                String password= cursor.getString(4);

                if(username.equals(user)&& password.equals(pass)){
                    result=true;
                    break;
                }
            }
        }


        return  result;
    }
}
