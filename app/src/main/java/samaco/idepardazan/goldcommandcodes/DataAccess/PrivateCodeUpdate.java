package samaco.idepardazan.goldcommandcodes.DataAccess;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import samaco.idepardazan.goldcommandcodes.Entities.MyData;

public class PrivateCodeUpdate {

    private String DB_PATH;
    private static String DB_NAME="goldcommanddb.sqlite";
    private SQLiteDatabase database;


    public Integer GetMaxId(Context context) {

        Integer Max_Id=5000;
        Cursor cursor;
        ExternalDbOpenHelper dbOpenHelper = new ExternalDbOpenHelper(context, DB_NAME);
        database = dbOpenHelper.openDataBase();

        cursor=database.rawQuery("select max(id) from tblhead where id>5000",null);

        if (cursor.getCount()>0)
        {
            try {
                cursor.moveToFirst();
                Max_Id = cursor.getInt(0);
            }
           catch (Exception e)
           {

            }

        }//if (cursor.getCount()>0)

        if (Max_Id==null || Max_Id==0)
            Max_Id=5000;

        Max_Id++;

        return Max_Id;
    }


    public void TblHead_Insert(Context context,String Title,String Smsno,String Smsformat,String Smsdetails)
    {
        ExternalDbOpenHelper dbOpenHelper = new ExternalDbOpenHelper(context, DB_NAME);
        database = dbOpenHelper.openDataBase();
        Integer Max_Id=GetMaxId(context);
        String Tag="کدهای شخصی";

        database.execSQL("insert into tblhead (id,group_id,title,smsno,smsformat,smsdetails,priority,tag,myfav) values("
                +Max_Id+",5000,'"+Title+"','"+Smsno+"','"+Smsformat+"','"+Smsdetails+"',1,'"+Tag+"',0)");
    }

    public void TblHead_Update(Context context,String Id,String Title,String Smsno,String Smsformat,String Smsdetails)
    {
        ExternalDbOpenHelper dbOpenHelper = new ExternalDbOpenHelper(context, DB_NAME);
        database = dbOpenHelper.openDataBase();

        database.execSQL("update tblhead set title='"+Title+"',smsno='"+Smsno+"',smsformat='"
                +Smsformat+"',smsdetails='"+Smsdetails+"' where id='"+Id+"'");
    }


    public void TblHead_Delete(Context context,String Id)
    {
        ExternalDbOpenHelper dbOpenHelper = new ExternalDbOpenHelper(context, DB_NAME);
        database = dbOpenHelper.openDataBase();

        database.execSQL("delete from tblhead  where id='"+Id+"'");
    }

    public MyData GetCodeData(Context context,String Id) {

        MyData myData=new MyData();
        Cursor cursor;
        ExternalDbOpenHelper dbOpenHelper = new ExternalDbOpenHelper(context, DB_NAME);
        database = dbOpenHelper.openDataBase();

        cursor=database.rawQuery("SELECT title,smsno,smsformat,smsdetails FROM tblhead where id="+Id,null);

        if (cursor.getCount()>0) {

            cursor.moveToFirst();

            myData.setTitle(cursor.getString(0).trim());
            myData.setSmsno(cursor.getString(1).trim());
            myData.setSmsformat(cursor.getString(2).trim());
            myData.setSmsdetails(cursor.getString(3).trim());

        }//if (cursor.getCount()>0)

        return  myData;

    }


    }
