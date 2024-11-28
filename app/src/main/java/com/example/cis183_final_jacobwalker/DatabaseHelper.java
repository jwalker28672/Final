package com.example.cis183_final_jacobwalker;

import android.content.Context;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String database_name = "Techs.db";
    public static final String tech_table_name = "techs";
    public static final String ro_table_name = "requestOrders";

    public DatabaseHelper(Context c){ super(c,database_name,null,1);}

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE " + tech_table_name + " (techNum integer primary key, uName varchar(50), fName varchar(50), lName varchar(50), password varchar(50), foreman boolean);");
        db.execSQL("CREATE TABLE " + ro_table_name + " (roNum integer primary key, techNum integer, hours float, type varchar(50), foreign key (tehcNum) references " + tech_table_name + " (techNum));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        db.execSQL("DROP TABLE IF EXISTS " + tech_table_name + ";");
        db.execSQL("DROP TABLE IF EXISTS " + ro_table_name + ";");

        onCreate(db);
    }

    //initializes both tables
    private void initTables()
    {
        initTechs();
        initRequestOrders();
    }

    //add initial set of data for both tables
    private void initTechs()
    {
        if(countRecordsFromTable(tech_table_name) == 0)
        {
            SQLiteDatabase db = this.getWritableDatabase();

            db.execSQL("INSERT INTO " + tech_table_name + "(techNum, uName, fName, lName, password, foreman) VALUES ('137607', 'SHayes', 'Spencer', 'Hayes', 'SHayes607', true);");
            db.execSQL("INSERT INTO " + tech_table_name + "(techNum, uName, fName, lName, password, foreman) VALUES ('137635', 'HFox', 'Hanna', 'Fox', 'HFox635', false);");
            db.execSQL("INSERT INTO " + tech_table_name + "(techNum, uName, fName, lName, password, foreman) VALUES ('137378', 'ICruz', 'Ian', 'Cruz', 'ICruz378', false);");
            db.execSQL("INSERT INTO " + tech_table_name + "(techNum, uName, fName, lName, password, foreman) VALUES ('137862', 'DBryant', 'Donald', 'Bryant', 'DBryant862', false);");

        }
    }

    private void initRequestOrders()
    {
        if(countRecordsFromTable(ro_table_name) == 0)
        {

        }
    }

    public int countRecordsFromTable(String tableName)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        int numRows = (int) DatabaseUtils.queryNumEntries(db, tableName);

        db.close();

        return numRows;
    }
}
