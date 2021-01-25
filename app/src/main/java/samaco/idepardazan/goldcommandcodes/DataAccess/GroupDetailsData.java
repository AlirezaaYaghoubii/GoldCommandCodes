package samaco.idepardazan.goldcommandcodes.DataAccess;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import samaco.idepardazan.goldcommandcodes.Entities.MyGroupData;

/**
 * Created by Alireza on 06/13/2015.
 */
public class GroupDetailsData {

    private String DB_PATH;
    private static String DB_NAME="goldcommanddb.sqlite";
    private SQLiteDatabase database;


    public ArrayList<MyGroupData> SelectGroupDetails(Context context,String group_id) {

        ArrayList<MyGroupData> HeadTable=new ArrayList<MyGroupData>();
        MyGroupData myGroupData=new MyGroupData();
        ExternalDbOpenHelper dbOpenHelper = new ExternalDbOpenHelper(context, DB_NAME);
        database = dbOpenHelper.openDataBase();

        Cursor cursor=database.rawQuery("SELECT id,group_id,title FROM tblgroup where main_id="+group_id+" order by priority",null);

        cursor.moveToFirst();
        HeadTable=new ArrayList<MyGroupData>();


        myGroupData.setId(cursor.getString(0));
        myGroupData.setMain_Id(cursor.getString(1).trim());
        myGroupData.setTitle(cursor.getString(2).trim());


        HeadTable.add(myGroupData);


        while (cursor.moveToNext())
        {
            myGroupData=new MyGroupData();

            myGroupData.setId(cursor.getString(0));
            myGroupData.setMain_Id(cursor.getString(1).trim());
            myGroupData.setTitle(cursor.getString(2).trim());

            HeadTable.add(myGroupData);

        }//while (cursor.moveToNext())

        return  (HeadTable);

    }

}
