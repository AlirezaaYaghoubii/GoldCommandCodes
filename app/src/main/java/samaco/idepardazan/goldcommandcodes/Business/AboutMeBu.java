
package samaco.idepardazan.goldcommandcodes.Business;

import android.content.Context;

import java.util.ArrayList;

import samaco.idepardazan.goldcommandcodes.DataAccess.AboutMeData;
import samaco.idepardazan.goldcommandcodes.Entities.MyGroupData;


public class AboutMeBu {

    public ArrayList<MyGroupData> SelectDetails(Context context) {

        AboutMeData aboutMeData=new AboutMeData();
        return aboutMeData.SelectData(context);

    }
}

