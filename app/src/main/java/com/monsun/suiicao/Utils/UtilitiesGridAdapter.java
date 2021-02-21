package com.monsun.suiicao.Utils;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

import com.monsun.suiicao.R;
import com.monsun.suiicao.models.Utilities;

public class



UtilitiesGridAdapter extends BaseAdapter {
    private List<Utilities> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public UtilitiesGridAdapter(List<Utilities> listData, Context context) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    static class ViewHolder {
        ImageView iconView;
        TextView nameView;
    }

    public int getMipmapResIdByName(String resName)  {
        String pkgName = context.getPackageName();

        // Return 0 if not found.
        int resID = context.getResources().getIdentifier(resName , "mipmap", pkgName);
        Log.i("CustomGridView", "Res Name: "+ resName+"==> Res ID = "+ resID);
        return resID;
    }


    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.grid_item_layout, null);
            holder = new ViewHolder();
            holder.iconView = (ImageView) convertView.findViewById(R.id.imageView_icon);
            holder.nameView = (TextView) convertView.findViewById(R.id.textView_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Utilities utilities = this.listData.get(position);
        holder.nameView.setText(utilities.getUtiName());

        int imageId = this.getMipmapResIdByName(utilities.getUtiIcon());

        holder.iconView.setImageResource(imageId);

        return convertView;
    }
}
