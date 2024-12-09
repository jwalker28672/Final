package com.example.cis183_final_jacobwalker;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ForemanListAdapter extends BaseAdapter {

   ArrayList<RequestOrder> listOfOrders;
   Context context;

   public ForemanListAdapter(Context c,ArrayList<RequestOrder> ol)
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
          view = mInflater.inflate(R.layout.foreman_rolist_cell, null);
      }

      TextView techNum = view.findViewById(R.id.tv_v_fcc_techAssigned);
      TextView roNum   = view.findViewById(R.id.tv_v_fcc_roNum);
      TextView roHours = view.findViewById(R.id.tv_v_fcc_roHours);

      RequestOrder ro = listOfOrders.get(i);

      techNum.setText(Integer.toString(ro.getTechNum()));
      roNum.setText(Integer.toString(ro.getOrderNum()));
      roHours.setText(Float.toString(ro.getHours()));

       return view;
    }
}
