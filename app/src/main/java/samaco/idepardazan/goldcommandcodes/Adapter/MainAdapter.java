package samaco.idepardazan.goldcommandcodes.Adapter;



import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import samaco.idepardazan.goldcommandcodes.R;

public class MainAdapter extends BaseAdapter {

    LayoutInflater inflater ;
    ArrayList<String> DetailsItemList;
    ArrayList<String> ID_List;
    Typeface TextFont;

    public MainAdapter(Context context, ArrayList<String> DetailsItemList,ArrayList<String> ID_List)
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
            convertView = inflater.inflate(R.layout.list_item_main,null);

            holder.MyText = (TextView) convertView.findViewById(R.id.myText);
            holder.MyImg = (ImageView) convertView.findViewById(R.id.MyImg);

            convertView.setTag(holder);
        }
        else
        {
            holder = (MyHolder) convertView.getTag();

        }

        holder.MyText.setText(DetailsItemList.get(position));


        if (ID_List.get(position).equals("0"))
            holder.MyImg.setImageResource(R.mipmap.home);
        else if (ID_List.get(position).equals("1"))
            holder.MyImg.setImageResource(R.mipmap.mci);
        else if (ID_List.get(position).equals("2"))
            holder.MyImg.setImageResource(R.mipmap.smsmci2);
        else if (ID_List.get(position).equals("3"))
            holder.MyImg.setImageResource(R.mipmap.phonemci);
        else if (ID_List.get(position).equals("4"))
            holder.MyImg.setImageResource(R.mipmap.webmci);
        else if (ID_List.get(position).equals("5"))
            holder.MyImg.setImageResource(R.mipmap.emailmci);



        else if (ID_List.get(position).equals("6"))
            holder.MyImg.setImageResource(R.mipmap.irancell);
        else if (ID_List.get(position).equals("7"))
            holder.MyImg.setImageResource(R.mipmap.smsirancell);
        else if (ID_List.get(position).equals("8"))
            holder.MyImg.setImageResource(R.mipmap.phoneirancell);
        else if (ID_List.get(position).equals("9"))
            holder.MyImg.setImageResource(R.mipmap.webirancell);
        else if (ID_List.get(position).equals("10"))
            holder.MyImg.setImageResource(R.mipmap.emailirancell);



        else if (ID_List.get(position).equals("11"))
            holder.MyImg.setImageResource(R.mipmap.rightel);
        else if (ID_List.get(position).equals("12"))
            holder.MyImg.setImageResource(R.mipmap.smsrightel);
        else if (ID_List.get(position).equals("13"))
            holder.MyImg.setImageResource(R.mipmap.phonerightel);
        else if (ID_List.get(position).equals("14"))
            holder.MyImg.setImageResource(R.mipmap.webrightel);
        else if (ID_List.get(position).equals("15"))
            holder.MyImg.setImageResource(R.mipmap.emailrightel2);

        else if (ID_List.get(position).equals("16"))
            holder.MyImg.setImageResource(R.mipmap.logout);


        holder.MyText.setTypeface(TextFont);

        return convertView;



    }

    private class MyHolder
    {
        TextView MyText;
        ImageView MyImg;

    }
}

