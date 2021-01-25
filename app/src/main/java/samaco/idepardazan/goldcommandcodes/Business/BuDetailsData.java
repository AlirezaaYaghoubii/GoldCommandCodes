package samaco.idepardazan.goldcommandcodes.Business;

import android.content.Context;

import java.util.ArrayList;

import samaco.idepardazan.goldcommandcodes.DataAccess.DetailsData;
import samaco.idepardazan.goldcommandcodes.Entities.MyData;

/**
 * Created by Alireza on 06/06/2015.
 */
public class BuDetailsData {

    public ArrayList<MyData> SelectDetails(Context context,String group_id) {

        DetailsData detailsData=new DetailsData();
        return detailsData.SelectDetails(context,group_id);
    }

    public void SetFavorite(Context context,String Id)
    {
        DetailsData detailsData=new DetailsData();
        detailsData.SetFavorite(context,Id);
    }

    public void SetUnFavorite(Context context,String Id)
    {
        DetailsData detailsData=new DetailsData();
        detailsData.SetUnFavorite(context,Id);
    }
    public Boolean GetFavoriteStatus(Context context,String Id)
    {
        DetailsData detailsData=new DetailsData();
        return detailsData.GetFavoriteStatus(context,Id);
    }

    }
