package samaco.idepardazan.goldcommandcodes.DataAccess;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Alireza on 08/22/2015.
 */
public class GetVersionData{

private static String DB_NAME="goldcommanddb.sqlite";
private SQLiteDatabase database;

        public String GetCurrentVersion(Context context) {

            String LocalVersion="0";

            try {
                ExternalDbOpenHelper dbOpenHelper = new ExternalDbOpenHelper(context, DB_NAME);
                database = dbOpenHelper.openDataBase();

                Cursor cursor;


                cursor = database.rawQuery("SELECT versionname FROM tblversion ", null);


                if (cursor != null && cursor.moveToFirst()) {
                    do {

                        LocalVersion = cursor.getString(0);

                    } while (cursor.moveToNext());

                }//if (cursor != null && cursor.moveToFirst()) {

            }
            catch (Exception Ex)
            {
                LocalVersion="0";
            }

            return  (LocalVersion);

        }
}
