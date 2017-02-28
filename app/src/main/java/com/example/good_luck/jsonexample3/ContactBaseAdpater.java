package com.example.good_luck.jsonexample3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by admin on 27/02/2017.
 */

public class ContactBaseAdpater extends BaseAdapter {
    private Context mContext;
    private ArrayList<ModelObject> all_Line_itemses = null;
    private LayoutInflater l_Inflater;


    public ContactBaseAdpater(Context context, ArrayList<ModelObject> AllLineitemses) {
        this.mContext = context;
        this.all_Line_itemses = AllLineitemses;
        l_Inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return all_Line_itemses.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return all_Line_itemses.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        final ModelObject lineitems = all_Line_itemses.get(position);

        if (convertView == null) {
            convertView = l_Inflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.email = (TextView) convertView.findViewById(R.id.email);
            holder.mobile = (TextView) convertView.findViewById(R.id.mobile);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText(lineitems.getName());
        holder.email.setText("Email :" + lineitems.getEmail());
        return convertView;

    }

    class ViewHolder {
        private TextView name;
        private TextView email;
        private TextView mobile;


    }

}
