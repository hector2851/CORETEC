package com.example.coretec.ui.reciclar;

import android.content.Context;
import android.os.IInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.coretec.R;

import java.util.List;
import java.util.Optional;

public class RaeeAdapter extends BaseAdapter implements Filter {
    private Context context;
    private  int layout;
    private List<String> raee;
    private List<Integer> raeeImage;
    public  RaeeAdapter (Context context, int layout, List<String> raee, List<Integer> raeeImage){
        this.context = context;
        this.layout = layout;
        this.raee = raee;
        this.raeeImage = raeeImage;
    }

    @Override
    public int getCount() {
        return this.raee.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int id) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView ==null){
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            convertView = layoutInflater.inflate(this.layout, null);

            holder = new ViewHolder();
            holder.nameTexView = (TextView) convertView.findViewById(R.id.text_view1);
            holder.imageviet= (ImageView) convertView.findViewById(R.id.image_view1);
            convertView.setTag(holder);
        } else{
            holder = (ViewHolder) convertView.getTag();
        }

        String currentName = raee.get(position);
        Integer currentImg = raeeImage.get(position);
        holder.nameTexView.setText(currentName);
        holder.imageviet.setImageResource(currentImg);

        return convertView;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        return null;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {

    }

    static  class  ViewHolder{
        private TextView nameTexView;
        private ImageView imageviet;
    }
}
