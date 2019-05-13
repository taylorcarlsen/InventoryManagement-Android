package com.example.inventoryandroidapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.inventoryandroidapp.models.Category;

import java.util.List;

public class CustomSpinnerAdapter extends BaseAdapter {

    private List<Category> categories;
    private Context context;
    LayoutInflater inflater;

    public CustomSpinnerAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
        this.inflater = (LayoutInflater.from(context));
    }



    @Override
    public int getCount() {
        if (categories!= null) {
            return categories.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=inflater.inflate(R.layout.support_simple_spinner_dropdown_item,null);
        TextView tv=(TextView)convertView.findViewById(R.id.textView2);
        tv.setText(categories.get(position).getDescription());
        return convertView;
    }
}
