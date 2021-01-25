

package samaco.idepardazan.goldcommandcodes.DataAccess;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import samaco.idepardazan.goldcommandcodes.Entities.MyGroupData;

/**
 * Created by Alireza on 06/13/2015.
 */
public class AboutMeData {

    private String DB_PATH;
    private static String DB_NAME="goldcommanddb.sqlite";
    private SQLiteDatabase database;


    public ArrayList<MyGroupData> SelectData(Context context) {

        ArrayList<MyGroupData> HeadTable=new ArrayList<MyGroupData>();
        MyGroupData myGroupData=new MyGroupData();
        ExternalDbOpenHelper dbOpenHelper = new ExternalDbOpenHelper(context, DB_NAME);
        database = dbOpenHelper.openDataBase();

        Cursor cursor=database.rawQuery("SELECT id,title FROM tblref  order by id",null);

        cursor.moveToFirst();
        HeadTable=new ArrayList<MyGroupData>();


        myGroupData.setId(cursor.getString(0));
        myGroupData.setTitle(cursor.getString(1).trim());


        HeadTable.add(myGroupData);


        while (cursor.moveToNext())
        {
            myGroupData=new MyGroupData();

            myGroupData.setId(cursor.getString(0));
            myGroupData.setTitle(cursor.getString(1).trim());

            HeadTable.add(myGroupData);

        }//while (cursor.moveToNext())

        return  (HeadTable);

    }

}
