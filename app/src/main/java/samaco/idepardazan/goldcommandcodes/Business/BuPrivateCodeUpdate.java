package samaco.idepardazan.goldcommandcodes.Business;

import android.content.Context;

import samaco.idepardazan.goldcommandcodes.DataAccess.PrivateCodeUpdate;
import samaco.idepardazan.goldcommandcodes.Entities.MyData;

/**
 * Created by Alireza on 12/28/2015.
 */
public class BuPrivateCodeUpdate {

    public void TblHead_Insert(Context context,String Title,String Smsno,String Smsformat,String Smsdetails)
    {

        PrivateCodeUpdate  privateCodeUpdate=new PrivateCodeUpdate();
        privateCodeUpdate.TblHead_Insert(context,Title,Smsno,Smsformat,Smsdetails);
    }

    public void TblHead_Update(Context context,String Id,String Title,String Smsno,String Smsformat,String Smsdetails)
    {

        PrivateCodeUpdate  privateCodeUpdate=new PrivateCodeUpdate();
        privateCodeUpdate.TblHead_Update(context, Id, Title, Smsno, Smsformat, Smsdetails);

    }

    public void TblHead_Delete(Context context,String Id)
    {
        PrivateCodeUpdate  privateCodeUpdate=new PrivateCodeUpdate();
        privateCodeUpdate.TblHead_Delete(context, Id);
    }

    public MyData GetCodeData(Context context,String Id) {

        PrivateCodeUpdate  privateCodeUpdate=new PrivateCodeUpdate();
        return privateCodeUpdate.GetCodeData(context, Id);
    }
}
