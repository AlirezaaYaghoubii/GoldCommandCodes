package samaco.idepardazan.goldcommandcodes.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import samaco.idepardazan.goldcommandcodes.R;


public class GroupListAdapter extends BaseAdapter {

    LayoutInflater inflater ;
    ArrayList<String> DetailsItemList;
    ArrayList<String> ID_List;
    Typeface TextFont;

    public GroupListAdapter(Context context, ArrayList<String> DetailsItemList,ArrayList<String> ID_List)
    {
        inflater = LayoutInflater.from(context);
        this.DetailsItemList=DetailsItemList;
        this.ID_List=ID_List;
        TextFont=Typeface.createFromAsset(context.getAssets(),"fonts/BYekan.ttf");

    }

    @Override
    public int getCount()
    {
        return DetailsItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        MyHolder holder;

        if(convertView==null)
        {
            holder = new MyHolder();
            convertView = inflater.inflate(R.layout.list_item_grouplist,null);

            holder.MyText = (TextView) convertView.findViewById(R.id.myText);
            holder.MyImg = (ImageView) convertView.findViewById(R.id.MyImg);

            holder.LayerText=(LinearLayout) convertView.findViewById(R.id.LayerText);
            holder.LayerMain=(LinearLayout) convertView.findViewById(R.id.LayerMain);
            holder.LayerImage=(LinearLayout) convertView.findViewById(R.id.LayerImage);

            convertView.setTag(holder);
        }
        else
        {
            holder = (MyHolder) convertView.getTag();

        }

        holder.MyText.setText(DetailsItemList.get(position));


        if (ID_List.get(position).equals("101"))
            holder.MyImg.setImageResource(R.mipmap.mci);
        else if (ID_List.get(position).equals("102"))
            holder.MyImg.setImageResource(R.mipmap.mymoney);
        else if (ID_List.get(position).equals("103"))
            holder.MyImg.setImageResource(R.mipmap.internetblue);

            //سرویس های همراه اول
        else if (ID_List.get(position).equals("104"))
            holder.MyImg.setImageResource(R.mipmap.internetroyal);

            //جیرینگ همراه اول
        else if (ID_List.get(position).equals("105"))
            holder.MyImg.setImageResource(R.mipmap.kifpol2);

            // طرح های مناسبتی
        else if (ID_List.get(position).equals("106"))
            holder.MyImg.setImageResource(R.mipmap.cake);

            //خدمات پرداخت مشترکین دائمی
        else if (ID_List.get(position).equals("107"))
            holder.MyImg.setImageResource(R.mipmap.pardakht);

            //خدمات پرداخت مشترکین اعتباری
        else if (ID_List.get(position).equals("108"))
            holder.MyImg.setImageResource(R.mipmap.payment);

            //خدمات غیرحضوری
        else if (ID_List.get(position).equals("109"))
            holder.MyImg.setImageResource(R.mipmap.internetbold);

            //آوای انتظار
        else if (ID_List.get(position).equals("110"))
            holder.MyImg.setImageResource(R.mipmap.music);

            //اینترنت
        else if (ID_List.get(position).equals("111"))
            holder.MyImg.setImageResource(R.mipmap.internetpack);


            //خدمات مکالمه
        else if (ID_List.get(position).equals("112"))
            holder.MyImg.setImageResource(R.mipmap.chat);


            //خدمات ارزش افزوده
        else if (ID_List.get(position).equals("113"))
            holder.MyImg.setImageResource(R.mipmap.football);


            //چتر اضطراری
        else if (ID_List.get(position).equals("114"))
            holder.MyImg.setImageResource(R.mipmap.emergency);


            //بسته های پیامک
        else if (ID_List.get(position).equals("115"))
            holder.MyImg.setImageResource(R.mipmap.smsmci);


            //بسته های مکالمه
        else if (ID_List.get(position).equals("116"))
            holder.MyImg.setImageResource(R.mipmap.chat2);


        else if (ID_List.get(position).equals("201"))
            holder.MyImg.setImageResource(R.mipmap.smsmci);
        else if (ID_List.get(position).equals("202"))
            holder.MyImg.setImageResource(R.mipmap.chat);
        else if (ID_List.get(position).equals("203"))
            holder.MyImg.setImageResource(R.mipmap.internetpack);
        else if (ID_List.get(position).equals("251"))
            holder.MyImg.setImageResource(R.mipmap.phonemci);

        else if (ID_List.get(position).equals("271"))
            holder.MyImg.setImageResource(R.mipmap.internetbold);
        else if (ID_List.get(position).equals("272"))
            holder.MyImg.setImageResource(R.mipmap.pardakht);
        else if (ID_List.get(position).equals("273"))
            holder.MyImg.setImageResource(R.mipmap.webmci);
        else if (ID_List.get(position).equals("274"))
            holder.MyImg.setImageResource(R.mipmap.internetroyal);

        else if (ID_List.get(position).equals("301"))
            holder.MyImg.setImageResource(R.mipmap.emailmci);


            //کدهای دستوری رایتل
        else if (ID_List.get(position).equals("3"))
            holder.MyImg.setImageResource(R.mipmap.rightel);

            //بسته های مصرفی اینترنت – سرعت پایه
        else if (ID_List.get(position).equals("171"))
            holder.MyImg.setImageResource(R.mipmap.internetgreen);

            //بسته های پیامکی دایمی و اعتباری
        else if (ID_List.get(position).equals("172"))
            holder.MyImg.setImageResource(R.mipmap.smsrightel);

            //بسته های مصرفی اینترنت – سرعت حرفه ای
        else if (ID_List.get(position).equals("173"))
            holder.MyImg.setImageResource(R.mipmap.internetfast);

        else if (ID_List.get(position).equals("174"))
            holder.MyImg.setImageResource(R.mipmap.internetroyal);


            //سیم کارت اعتباری
        else if (ID_List.get(position).equals("151"))
            holder.MyImg.setImageResource(R.mipmap.irancell);

            //سیم کارت دائمی
        else if (ID_List.get(position).equals("152"))
            holder.MyImg.setImageResource(R.mipmap.mysim);

            //بسته های ترکیبی ایرانسل
        else if (ID_List.get(position).equals("153"))
            holder.MyImg.setImageResource(R.mipmap.basteirancell);

            //بسته های اینترنت
        else if (ID_List.get(position).equals("154"))
            holder.MyImg.setImageResource(R.mipmap.internetred);

            //بسته اینترنت همراه هدیه دهید!
        else if (ID_List.get(position).equals("155"))
            holder.MyImg.setImageResource(R.mipmap.gift);

            //پیشنهادهای تشویقی
        else if (ID_List.get(position).equals("156"))
            holder.MyImg.setImageResource(R.mipmap.mymoney);

            //روش‌های شارژ اضطراری
        else if (ID_List.get(position).equals("157"))
            holder.MyImg.setImageResource(R.mipmap.emergency);

            //طرح های تعرفه
        else if (ID_List.get(position).equals("158"))
            holder.MyImg.setImageResource(R.mipmap.mysetting);

            //طرح های تعرفه رنگی دیتا
        else if (ID_List.get(position).equals("159"))
            holder.MyImg.setImageResource(R.mipmap.paint);

            //سایر کدها
        else if (ID_List.get(position).equals("160"))
            holder.MyImg.setImageResource(R.mipmap.bookmarklist);

            //شارژ مستقیم اینترنت همراه
        else if (ID_List.get(position).equals("161"))
            holder.MyImg.setImageResource(R.mipmap.internetpack);

            //بسته‌های ترکیبی آی ‌سیم
        else if (ID_List.get(position).equals("162"))
            holder.MyImg.setImageResource(R.mipmap.smsirancell);

        else if (ID_List.get(position).equals("163"))
            holder.MyImg.setImageResource(R.mipmap.internetred);

        else if (ID_List.get(position).equals("351"))
            holder.MyImg.setImageResource(R.mipmap.smsirancell);

        else if (ID_List.get(position).equals("371"))
            holder.MyImg.setImageResource(R.mipmap.phoneirancell);

        else if (ID_List.get(position).equals("401"))
            holder.MyImg.setImageResource(R.mipmap.webirancell);
        else if (ID_List.get(position).equals("402"))
            holder.MyImg.setImageResource(R.mipmap.internetroyal);

        else if (ID_List.get(position).equals("451"))
            holder.MyImg.setImageResource(R.mipmap.emailirancell);


        else if (ID_List.get(position).equals("501"))
            holder.MyImg.setImageResource(R.mipmap.smsrightel);
        else if (ID_List.get(position).equals("502"))
            holder.MyImg.setImageResource(R.mipmap.phonerightel);
        else if (ID_List.get(position).equals("503"))
            holder.MyImg.setImageResource(R.mipmap.emailrightel2);
        else if (ID_List.get(position).equals("504"))
            holder.MyImg.setImageResource(R.mipmap.rightel);
        else if (ID_List.get(position).equals("505"))
            holder.MyImg.setImageResource(R.mipmap.chat);
        else if (ID_List.get(position).equals("506"))
            holder.MyImg.setImageResource(R.mipmap.internetgreen);
        else if (ID_List.get(position).equals("507"))
            holder.MyImg.setImageResource(R.mipmap.internetroyal);

        else if (ID_List.get(position).equals("551"))
            holder.MyImg.setImageResource(R.mipmap.phonerightel);


        else if (ID_List.get(position).equals("601"))
            holder.MyImg.setImageResource(R.mipmap.payment);

        else if (ID_List.get(position).equals("602"))
            holder.MyImg.setImageResource(R.mipmap.webrightel);

        else if (ID_List.get(position).equals("603"))
            holder.MyImg.setImageResource(R.mipmap.kifpol2);


        else if (ID_List.get(position).equals("651"))
            holder.MyImg.setImageResource(R.mipmap.emailrightel2);





        if (position % 2 == 1) {

            holder.LayerText.setBackgroundResource(R.drawable.newlist_2);
            holder.LayerMain.setBackgroundResource(R.drawable.newlist_2);
            holder.LayerImage.setBackgroundResource(R.drawable.newlist_2);

        } else {

            holder.LayerText.setBackgroundResource(R.drawable.newlistviewbg);
            holder.LayerMain.setBackgroundResource(R.drawable.newlistviewbg);
            holder.LayerImage.setBackgroundResource(R.drawable.newlistviewbg);

        }

        holder.MyText.setTypeface(TextFont);

        return convertView;



    }

    private class MyHolder
    {
        TextView MyText;
        ImageView MyImg;
        LinearLayout LayerText;
        LinearLayout LayerMain;
        LinearLayout LayerImage;
    }
}
