package samaco.idepardazan.goldcommandcodes.Business;

import android.content.Context;

import samaco.idepardazan.goldcommandcodes.DataAccess.GetVersionData;

public class GetVersionBu {

    public String GetCurrentVersion(Context context) {

        GetVersionData getVersionData=new GetVersionData();
        return  getVersionData.GetCurrentVersion(context);

    }

}
