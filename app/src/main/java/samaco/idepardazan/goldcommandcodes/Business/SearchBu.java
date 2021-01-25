package samaco.idepardazan.goldcommandcodes.Business;


import android.content.Context;

import java.util.ArrayList;

import samaco.idepardazan.goldcommandcodes.DataAccess.SearchData;
import samaco.idepardazan.goldcommandcodes.Entities.MyGroupData;

public class SearchBu {

    public ArrayList<MyGroupData> SelectSearchData(Context context,String Title) {

        SearchData searchData=new SearchData();
        return searchData.SelectSearchData(context,Title);

    }
}
