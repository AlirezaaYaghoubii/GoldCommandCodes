package samaco.idepardazan.goldcommandcodes.DataAccess;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import samaco.idepardazan.goldcommandcodes.Entities.MyGroupData;

public class SearchData {

    private static String DB_NAME="goldcommanddb.sqlite";
    private SQLiteDatabase database;


    public ArrayList<MyGroupData> SelectSearchData(Context context,String Title) {

        ArrayList<MyGroupData> HeadTable=new ArrayList<MyGroupData>();
        MyGroupData myGroupData=new MyGroupData();
        ExternalDbOpenHelper dbOpenHelper = new ExternalDbOpenHelper(context, DB_NAME);
        database = dbOpenHelper.openDataBase();

        Cursor cursor;

        if (Title.equals(""))
        cursor=database.rawQuery("SELECT id,group_id,tag || ' - ' || title As Title ,smsno,smsformat,smsdetails,smsdetails2,smsdetails3,state FROM tblhead where 1=0",null);
        else
        cursor=database.rawQuery("SELECT id,group_id,tag || ' - ' || title As Title ,smsno,smsformat,smsdetails,smsdetails2,smsdetails3,state FROM tblhead where title like '%"
                +Title+"%' or tag like '%"+Title+"%' or smsno like '%"+Title+"%' order by title LIMIT 20",null);

        HeadTable = new ArrayList<MyGroupData>();


        if (cursor != null && cursor.moveToFirst()) {

            do {

                myGroupData=new MyGroupData();

                myGroupData.setId(cursor.getString(0));
                myGroupData.setMain_Id(cursor.getString(1).trim());
                myGroupData.setTitle(cursor.getString(2).trim());
                myGroupData.setSmsno(cursor.getString(3).trim());
                myGroupData.setSmsformat(cursor.getString(4).trim());
                myGroupData.setSmsdetails(cursor.getString(5).trim());

                myGroupData.setSmsdetails2(cursor.getString(6).trim());
                myGroupData.setSmsdetails3(cursor.getString(7).trim());
                myGroupData.setState(cursor.getString(8).trim());


                HeadTable.add(myGroupData);

            } while (cursor.moveToNext());

            }//if (cursor != null && cursor.moveToFirst()) {


        return  (HeadTable);

    }


}
