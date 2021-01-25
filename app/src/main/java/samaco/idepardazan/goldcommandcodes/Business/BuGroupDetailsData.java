package samaco.idepardazan.goldcommandcodes.Business;

import android.content.Context;

import java.util.ArrayList;

import samaco.idepardazan.goldcommandcodes.DataAccess.GroupDetailsData;
import samaco.idepardazan.goldcommandcodes.Entities.MyGroupData;

/**
 * Created by Alireza on 06/13/2015.
 */
public class BuGroupDetailsData {

    public ArrayList<MyGroupData> SelectGroupDetails(Context context,String group_id) {

        GroupDetailsData groupDetailsData=new GroupDetailsData();
        return groupDetailsData.SelectGroupDetails(context, group_id);


    }
}
