package samaco.idepardazan.goldcommandcodes.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import samaco.idepardazan.goldcommandcodes.R;

public class DetailsAdapter extends BaseAdapter {

    LayoutInflater inflater ;
    ArrayList<String> DetailsItemList;

    Typeface TextFont;

    public DetailsAdapter(Context context, ArrayList<String> DetailsItemList)
    {
        inflater = LayoutInflater.from(context);
        this.DetailsItemList=DetailsItemList;
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
            convertView = inflater.inflate(R.layout.list_item_details,null);
            holder.MyText = (TextView) convertView.findViewById(R.id.myText);
            convertView.setTag(holder);
        }
        else
        {
            holder = (MyHolder) convertView.getTag();

        }

        holder.MyText.setText(DetailsItemList.get(position));
        holder.MyText.setTypeface(TextFont);

        return convertView;



    }

    private class MyHolder
    {
        TextView MyText;

    }
}
