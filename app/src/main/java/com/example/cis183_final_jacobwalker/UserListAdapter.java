package com.example.cis183_final_jacobwalker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class UserListAdapter extends BaseAdapter
{
    ArrayList<RequestOrder> listOfOrders;
    Context context;

    public UserListAdapter(Context c,ArrayList<RequestOrder> ol)
    {
        context = c;
        listOfOrders = ol;
    }


    @Override
    public int getCount() {
        return listOfOrders.size();
    }

    @Override
    public Object getItem(int i) {
        return listOfOrders.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null)
        {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(MainActivity.LAYOUT_INFLATER_SERVICE);
            view = mInflater.inflate(R.layout.user_rolist_cell, null);
        }

        TextView roNum   = view.findViewById(R.id.tv_v_ucc_roNum);
        TextView roHours = view.findViewById(R.id.tv_v_ucc_roHours);
        TextView roType  = view.findViewById(R.id.tv_v_ucc_roType);

        RequestOrder ro = listOfOrders.get(i);

        roNum.setText(Integer.toString(ro.getOrderNum()));
        roHours.setText(Float.toString(ro.getHours()));
        roType.setText(Integer.toString(ro.getTypeId()));


        return view;
    }
}
