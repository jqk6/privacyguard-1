package com.PrivacyGuard.Application.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.PrivacyGuard.Application.Database.AppSummary;
import com.PrivacyGuard.Application.Database.CategorySummary;
import com.PrivacyGuard.Application.Logger;

import java.util.List;

/**
 * Created by justinhu on 16-03-11.
 */
public class SummaryListViewAdapter extends BaseAdapter {
    private List<CategorySummary> list;
    private final Context context;

    public SummaryListViewAdapter(Context context, List<CategorySummary> list){
        super();
        this.context = context;
        this.list=list;
    }

    public void updateData(List<CategorySummary> list){
        this.list = list;
        this.notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_summary, null);
            holder = new ViewHolder();

            holder.category=(TextView) convertView.findViewById(R.id.summary_category);
            holder.count=(TextView) convertView.findViewById(R.id.summary_count);
            holder.ignore=(TextView) convertView.findViewById(R.id.summary_ignore);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        CategorySummary category= list.get(position);
        holder.category.setText(category.category);
        holder.count.setText(String.valueOf(category.count));
        if(category.ignore == 0){
            holder.ignore.setVisibility(View.INVISIBLE);
        }else{
            holder.ignore.setVisibility(View.VISIBLE);
        }
        return convertView;
    }

    public static class ViewHolder {
        public TextView category;
        public TextView count;
        public TextView ignore;
    }
}
