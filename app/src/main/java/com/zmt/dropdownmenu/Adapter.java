package com.zmt.dropdownmenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Dangelo on 2016/7/22.
 */
public class Adapter extends BaseAdapter {

    private Context context;
    private List<String> list;
    private int layout;
    private int selectPos;

    public void setChecked(int pos){
        this.selectPos = pos;
        notifyDataSetChanged();
    }

    public Adapter(Context context, List<String> list, int layout) {
        this.context = context;
        this.list = list;
        this.layout = layout;
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
        ViewHolder viewHolder;
        if(convertView != null){
            viewHolder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(context).inflate(layout, null);
            viewHolder = new ViewHolder();
            convertView.setTag(viewHolder);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.text);
        }
        viewHolder.textView.setText(list.get(position));
        if(position == selectPos){
            viewHolder.textView.setTextColor(context.getResources().getColor(R.color.color_7B1FA2));
            if(layout == R.layout.item_default_drop_down){
                viewHolder.textView.setBackgroundResource(R.color.color_eeeeee);
            } else if(layout == R.layout.item_list_drop_down){
                viewHolder.textView.setCompoundDrawablesWithIntrinsicBounds(null, null, context.getResources().getDrawable(R.mipmap.drop_down_checked), null);
            } else {
                viewHolder.textView.setBackgroundResource(R.drawable.check_bg);
            }
        } else {
            viewHolder.textView.setTextColor(context.getResources().getColor(R.color.color_111111));
            if(layout == R.layout.item_default_drop_down){
                viewHolder.textView.setBackgroundResource(R.color.color_eeeeee);
            } else if(layout == R.layout.item_list_drop_down){
                viewHolder.textView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
            } else {
                viewHolder.textView.setBackgroundResource(R.drawable.uncheck_bg);
            }
        }
        return convertView;
    }

    class ViewHolder{
        TextView textView;
    }
}
