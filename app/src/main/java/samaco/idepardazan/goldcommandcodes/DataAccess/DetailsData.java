package samaco.idepardazan.goldcommandcodes.DataAccess;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import samaco.idepardazan.goldcommandcodes.Entities.MyData;


public class DetailsData {

    private String DB_PATH;
    private static String DB_NAME="goldcommanddb.sqlite";
    private SQLiteDatabase database;



    public ArrayList<MyData>  SelectDetails(Context context,String group_id) {

        Cursor cursor;
        ArrayList<MyData> HeadTable=new ArrayList<MyData>();
        MyData myData=new MyData();
        ExternalDbOpenHelper dbOpenHelper = new ExternalDbOpenHelper(context, DB_NAME);
        database = dbOpenHelper.openDataBase();

        if (group_id.equals("10000"))
            cursor=database.rawQuery("SELECT id,title,smsno,smsformat,smsdetails,group_id,smsdetails2,smsdetails3,state FROM tblhead where myfav=1 order by priority",null);
        else
            cursor=database.rawQuery("SELECT id,title,smsno,smsformat,smsdetails,group_id,smsdetails2,smsdetails3,state FROM tblhead where group_id="+group_id+" order by priority",null);

        if (cursor.getCount()>0)
        {

        cursor.moveToFirst();
        HeadTable=new ArrayList<MyData>();


    myData.setId(cursor.getString(0));
    myData.setTitle(cursor.getString(1).trim());
    myData.setSmsno(cursor.getString(2).trim());
    myData.setSmsformat(cursor.getString(3).trim());
    myData.setSmsdetails(cursor.getString(4).trim());
    myData.setGroup_id(cursor.getString(5).trim());

    myData.setSmsdetails2(cursor.getString(6).trim());
    myData.setSmsdetails3(cursor.getString(7).trim());
    myData.setState(cursor.getString(8).trim());

    HeadTable.add(myData);

}//if (cursor.getCount()>0)

        while (cursor.moveToNext())
        {
            myData=new MyData();

            myData.setId(cursor.getString(0));
            myData.setTitle(cursor.getString(1).trim());
            myData.setSmsno(cursor.getString(2).trim());
            myData.setSmsformat(cursor.getString(3).trim());
            myData.setSmsdetails(cursor.getString(4).trim());
            myData.setGroup_id(cursor.getString(5).trim());

            myData.setSmsdetails2(cursor.getString(6).trim());
            myData.setSmsdetails3(cursor.getString(7).trim());
            myData.setState(cursor.getString(8).trim());

            HeadTable.add(myData);

        }//while (cursor.moveToNext())

        return  (HeadTable);

    }

    public void SetFavorite(Context context,String Id)
    {

        ExternalDbOpenHelper dbOpenHelper = new ExternalDbOpenHelper(context, DB_NAME);
        database = dbOpenHelper.openDataBase();

        database.execSQL("Update tblhead Set myfav=1 Where id="+Id);


    }

    public void SetUnFavorite(Context context,String Id)
    {

        ExternalDbOpenHelper dbOpenHelper = new ExternalDbOpenHelper(context, DB_NAME);
        database = dbOpenHelper.openDataBase();

        database.execSQL("Update tblhead Set myfav=0 Where id="+Id);
    }

    public Boolean GetFavoriteStatus(Context context,String Id)
    {
        Boolean Result=false;
        Cursor cursor;
        ExternalDbOpenHelper dbOpenHelper = new ExternalDbOpenHelper(context, DB_NAME);
        database = dbOpenHelper.openDataBase();

        cursor=database.rawQuery("SELECT myfav FROM tblhead where id="+Id,null);
        cursor.moveToFirst();

        if (cursor.getString(0).equals("1"))
            Result=true;

        return Result;
    }
}


