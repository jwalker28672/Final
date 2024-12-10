package com.example.cis183_final_jacobwalker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String database_name = "Techs.db";
    public static final String tech_table_name = "techs";
    public static final String ro_table_name = "requestOrders";
    public static final String type_table_name = "roType";

    public DatabaseHelper(Context c){ super(c,database_name,null,2);}

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE " + tech_table_name + " (techNum integer primary key, uName varchar(50), fName varchar(50), lName varchar(50), password varchar(50), foreman boolean);");
        db.execSQL("CREATE TABLE " + ro_table_name + " (roNum integer primary key, techNum integer, hours float, typeId integer, date varchar(10), foreign key (techNum) references " + tech_table_name + " (techNum));");
        db.execSQL("CREATE TABLE " + type_table_name + " (typeId integer,  typeName varchar(50) primary key , foreign key (typeId) references " + ro_table_name + " (typeId));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        db.execSQL("DROP TABLE IF EXISTS " + tech_table_name + ";");
        db.execSQL("DROP TABLE IF EXISTS " + ro_table_name + ";");
        db.execSQL("DROP TABLE IF EXISTS " + type_table_name + ";");

        onCreate(db);
    }

    //initializes both tables
    public void initTables()
    {
        initTechs();
        initRequestOrders();
        initRoType();
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

            db.close();
        }
    }

    private void initRequestOrders()
    {
        if(countRecordsFromTable(ro_table_name) == 0)
        {
            SQLiteDatabase db = this.getWritableDatabase();

            db.execSQL("INSERT INTO " + ro_table_name + "(roNum, techNum, hours, typeId, date) VALUES ('322135', '137378' , '2.1', '0', '12-5-2024')");
            db.execSQL("INSERT INTO " + ro_table_name + "(roNum, techNum, hours, typeId, date) VALUES ('322136', '137862' , '0.6', '1', '12-2-2024')");
            db.execSQL("INSERT INTO " + ro_table_name + "(roNum, techNum, hours, typeId, date) VALUES ('322137', '137635' , '1.2', '4', '12-4-2024')");
            db.execSQL("INSERT INTO " + ro_table_name + "(roNum, techNum, hours, typeId, date) VALUES ('322138', '137607' , '4.3', '1', '12-5-2024')");
            db.execSQL("INSERT INTO " + ro_table_name + "(roNum, techNum, hours, typeId, date) VALUES ('322139', '137862' , '0.3', '6', '12-6-2024')");

            db.close();
        }
    }

    private void initRoType()
    {
        if(countRecordsFromTable(type_table_name) == 0)
        {
            SQLiteDatabase db = this.getWritableDatabase();

            db.execSQL("INSERT INTO " + type_table_name + "(typeId, typeName) VALUES ('0', 'EngineRepair')");
            db.execSQL("INSERT INTO " + type_table_name + "(typeId, typeName) VALUES ('1', 'AutomaticTransmission')");
            db.execSQL("INSERT INTO " + type_table_name + "(typeId, typeName) VALUES ('2', 'ManualDriveTrain')");
            db.execSQL("INSERT INTO " + type_table_name + "(typeId, typeName) VALUES ('3', 'Suspension/Steering')");
            db.execSQL("INSERT INTO " + type_table_name + "(typeId, typeName) VALUES ('4', 'Brakes')");
            db.execSQL("INSERT INTO " + type_table_name + "(typeId, typeName) VALUES ('5', 'Electrical')");
            db.execSQL("INSERT INTO " + type_table_name + "(typeId, typeName) VALUES ('6', 'HVAC')");
            db.execSQL("INSERT INTO " + type_table_name + "(typeId, typeName) VALUES ('7', 'EnginePerformance')");

            db.close();
        }
    }

    public int countRecordsFromTable(String tableName)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        int numRows = (int) DatabaseUtils.queryNumEntries(db, tableName);

        db.close();

        Log.d("Number of records: ", Integer.toString(numRows));

        return numRows;
    }

    public boolean isForeman(String uName)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectStatement = "SELECT foreman FROM " + tech_table_name + " WHERE uName = '" + uName + "';";

        Cursor cursor = db.rawQuery(selectStatement, null);

        if (cursor != null)
        {
            cursor.moveToNext();

            Integer foreman = cursor.getInt(0);
            Log.d("Db bool: ", Integer.toString(foreman));
            if(foreman == 1)
            {
                Log.d("Database: ", "Is Foreman");
                return true;
            }
        }

        return false;
    }

    public void setLoggedinUser(String uName)
    {
        Tech loggedinTech = new Tech();

        String selectQuery = "SELECT * FROM " + tech_table_name + " WHERE uName = '" + uName + "';";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor != null)
        {
            cursor.moveToFirst();

            loggedinTech.setTechNum(cursor.getInt(0));
            loggedinTech.setuName(cursor.getString(1));
            loggedinTech.setfName(cursor.getString(2));
            loggedinTech.setlName(cursor.getString(3));
            loggedinTech.setPassword(cursor.getString(4));

            SessionData.setLoggedInTech(loggedinTech);

            db.close();

        }

    }

    public boolean validPassword(String uName, String password)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectStatement = "SELECT uName , password FROM " + tech_table_name;

        Cursor cursor = db.rawQuery(selectStatement, null);

        while (cursor.moveToNext())
        {
            String name = cursor.getString(0);
            String pWord = cursor.getString((1));
            if(uName.equals(name) && password.equals(pWord))
            {
                db.close();
                return true;
            }
        }

        db.close();

        return false;
    }

    public boolean checkUserExists(String uName)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectStatement = "SELECT uName FROM " + tech_table_name;

        Cursor cursor = db.rawQuery(selectStatement, null);

        while (cursor.moveToNext())
        {
            String name = cursor.getString(0);
            if(uName.equals(name))
            {
                db.close();
                return true;
            }
        }

        db.close();
        return false;
    }

    public boolean checkRoExists(int num)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectStatement = "SELECT roNum FROM " + ro_table_name;

        Cursor cursor = db.rawQuery(selectStatement, null);

        while (cursor.moveToNext())
        {
            int roNum = cursor.getInt(0);
            if(num == roNum)
            {
                return true;
            }
        }

        return false;
    }

    public void addRo(RequestOrder ro)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("INSERT INTO " + ro_table_name + " Values ('" + ro.getOrderNum() + "','" + ro.getTechNum() + "','" + ro.getHours() + "','" + ro.getTypeId() + "' , '" + ro.getDate() + "');");

        db.close();
    }

    public  void updateRo(int num, RequestOrder ro)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("UPDATE " + ro_table_name + " SET techNum = '" + ro.getTechNum() + "' , hours = '" +  ro.getHours() + "' ,  typeId = '" + ro.getTypeId() + "' , date = '" + ro.getDate() + "'" + " WHERE roNum = '" + num + "'");

        db.close();
    }

    public ArrayList<RequestOrder> getAllRos()
    {
        ArrayList<RequestOrder> listOfRos = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String selectStatement = "SELECT * FROM " + ro_table_name ;

        Cursor cursor = db.rawQuery(selectStatement, null);

        while(cursor.moveToNext())
        {
            RequestOrder ro = new RequestOrder();

            ro.setOrderNum(cursor.getInt(0));
            ro.setTechNum(cursor.getInt(1));
            ro.setHours(cursor.getFloat(2));
            ro.setTypeId(cursor.getInt(3));
            ro.setDate(cursor.getString(4));

            listOfRos.add(ro);

        }

        db.close();

        return listOfRos;
    }

    public ArrayList<RequestOrder> getTechRos(int t)
    {
        ArrayList<RequestOrder> listOfRos = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String selectStatement = "SELECT * FROM " + ro_table_name + " WHERE techNum ='" + t + "';";

        Cursor cursor = db.rawQuery(selectStatement, null);

        while(cursor.moveToNext())
        {
            RequestOrder ro = new RequestOrder();

            ro.setOrderNum(cursor.getInt(0));
            ro.setTechNum(cursor.getInt(1));
            ro.setHours(cursor.getFloat(2));
            ro.setTypeId(cursor.getInt(3));
            ro.setDate(cursor.getString(4));

            listOfRos.add(ro);

        }

        db.close();


        return listOfRos;
    }

    public ArrayList<String> getAllTypeNames()
    {
        ArrayList<String> listOfRoTypes = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String selectStatement = "SELECT typeName FROM " + type_table_name;

        Cursor cursor = db.rawQuery(selectStatement, null);

        while (cursor.moveToNext())
        {
            String type;

            type = cursor.getString(0);

            listOfRoTypes.add(type);
        }

        db.close();

        return listOfRoTypes;
    }

    public int getTypeId(String n)
    {
        int id = 0;

        SQLiteDatabase db = this.getReadableDatabase();

        String selectStatement = "SELECT * FROM " + type_table_name ;

        Cursor cursor = db.rawQuery(selectStatement, null);

        while(cursor.moveToNext())
        {
            String name = cursor.getString(1);
            if(n.equals(name))
            {
                id = cursor.getInt(0);
            }
        }

        db.close();

        return id;
    }

    public String getTypeName(int i)
    {
        String name = "";

        SQLiteDatabase db = this.getReadableDatabase();

        String selectStatement = "SELECT typeName FROM " + type_table_name  + " WHERE typeId ='" + i + "';";

        Cursor cursor = db.rawQuery(selectStatement, null);

        if(cursor != null)
        {
            cursor.moveToFirst();

            name = cursor.getString(0);
        }

        db.close();

        return name;
    }

    @SuppressLint("Range")
    public ArrayList<RequestOrder> foremanSearchDatabase (String rN, String tN, String h, String t, int o)
    {
        ArrayList<RequestOrder> listOfRos = new ArrayList<>();

        int id = getTypeId(t);

        String selectStatement = "Select * from " + ro_table_name + " Where ";

        if(rN.isEmpty())
        {
            selectStatement += "roNum is not null";
        }
        else
        {
            selectStatement += "roNum = '" + rN + "' ";
        }
        selectStatement += " AND ";
        if(tN.isEmpty())
        {
            selectStatement += "techNum is not null ";
        }
        else
        {
            selectStatement += "techNum = '" + tN + "' ";
        }
        selectStatement += " AND ";
        if(h.isEmpty())
        {
            selectStatement += "hours is not null ";
        }
        else if(o == 0)
        {
            selectStatement += "hours < '" + h + "' ";
        }
        else if (o == 1)
        {
            selectStatement += "hours > '" + h + "' ";
        }
        selectStatement += " AND ";
        if(id == 0)
        {
            selectStatement += "typeId is not null ";
        }
        else
        {
            selectStatement += "typeId = '" + id + "' ";
        }

        selectStatement += ";";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectStatement, null);

        while(cursor.moveToNext())
        {
            RequestOrder ro = new RequestOrder();

            ro.setOrderNum(cursor.getInt(cursor.getColumnIndex("roNum")));
            ro.setTechNum(cursor.getInt(cursor.getColumnIndex("techNum")));
            ro.setHours(cursor.getFloat(cursor.getColumnIndex("hours")));
            ro.setTypeId(cursor.getInt(cursor.getColumnIndex("typeId")));
            ro.setDate(cursor.getString(cursor.getColumnIndex("date")));

            listOfRos.add(ro);

        }

        db.close();

        return listOfRos;
    }

    @SuppressLint("Range")
    public ArrayList<RequestOrder> userSearchDatabase (String rN, String h, String t, int o)
    {
        ArrayList<RequestOrder> listOfRos = new ArrayList<>();
        String tech = Integer.toString(SessionData.getLoggedInTech().getTechNum());

        int id = getTypeId(t);
        String selectStatement = "Select * from " + ro_table_name + " Where ";

        if(rN.isEmpty())
        {
            selectStatement += "roNum is not null";
        }
        else
        {
            selectStatement += "roNum = '" + rN + "' ";
        }
        selectStatement += " AND ";
        if(h.isEmpty())
        {
            selectStatement += "hours is not null ";
        }
        else if(o == 0)
        {
            selectStatement += "hours < '" + h + "' ";
        }
        else if (o == 1)
        {
            selectStatement += "hours > '" + h + "' ";
        }
        selectStatement += " AND ";
        if(id == 0)
        {
            selectStatement += "typeId is not null ";
        }
        else
        {
            selectStatement += "typeId = '" + id + "' ";
        }

        selectStatement += " AND techNum= '" + tech + "'  ";

        selectStatement += ";";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectStatement, null);

        while(cursor.moveToNext())
        {
            RequestOrder ro = new RequestOrder();

            ro.setOrderNum(cursor.getInt(cursor.getColumnIndex("roNum")));
            ro.setTechNum(cursor.getInt(cursor.getColumnIndex("techNum")));
            ro.setHours(cursor.getFloat(cursor.getColumnIndex("hours")));
            ro.setTypeId(cursor.getInt(cursor.getColumnIndex("typeId")));
            ro.setDate(cursor.getString(cursor.getColumnIndex("date")));

            Log.d("Database: ", Integer.toString(ro.getTypeId()));

            listOfRos.add(ro);

        }

        db.close();

        return listOfRos;
    }

}
