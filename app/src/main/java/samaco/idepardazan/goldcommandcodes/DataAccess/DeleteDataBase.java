package samaco.idepardazan.goldcommandcodes.DataAccess;

import android.content.Context;

import java.io.File;

/**
 * Created by Alireza on 06/06/2015.
 */
public class DeleteDataBase {

    private String DB_PATH;
    private static String DB_NAME;

    public void DeleteIfExists(Context context) {
        String packageName = context.getPackageName();
        DB_PATH = String.format("//data//data//%s//databases//", packageName);
        DB_NAME = "goldcommanddb.sqlite";
        boolean checkdb = false;

        String myPath = DB_PATH + DB_NAME;
        File dbfile = new File(myPath);
        checkdb = dbfile.exists();

        if (checkdb)
        dbfile.delete();

    }

    }
