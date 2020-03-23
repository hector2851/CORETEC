package com.example.coretec.ui.ajustes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.coretec.R;

import java.util.List;

public class AjustesAdapter extends BaseAdapter {
    private Context context;
    private  int layout;
    private List<String> nombre;
    private List<Integer> nombreImg;


    public  AjustesAdapter (Context context, int layout, List<String> nombre, List<Integer> nombreImg){
        this.context = context;
        this.layout = layout;
        this.nombre = nombre;
        this.nombreImg = nombreImg;
    }
    @Override
    public int getCount() {
        return this.nombre.size();
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
            holder.nameTexView = (TextView) convertView.findViewById(R.id.ListTex);
            holder.imageviet= (ImageView) convertView.findViewById(R.id.ListImg);
            convertView.setTag(holder);
        } else{
            holder = (ViewHolder) convertView.getTag();
        }

        String currentName = nombre.get(position);
        Integer currentImg = nombreImg.get(position);
        holder.nameTexView.setText(currentName);
        holder.imageviet.setImageResource(currentImg);

        return convertView;
    }
    static  class  ViewHolder{
        private TextView nameTexView;
        private ImageView imageviet;

    }
}

