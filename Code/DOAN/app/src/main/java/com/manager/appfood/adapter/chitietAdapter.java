package com.manager.appfood.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.manager.appfood.R;
import com.manager.appfood.model.item;

import java.util.List;

public class chitietAdapter extends RecyclerView.Adapter<chitietAdapter.MyViewHolder> {
    Context context;
    List<item> itemList;

    public chitietAdapter(Context context, List<item> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chitiet, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    item item=itemList.get(position);
    holder.txtten.setText(item.getTensp()+"");
    holder.txtsoluong.setText("so luong: "+item.getSoluong()+"");
        Glide.with(context).load(item.getHinhanh()).into(holder.imagechitiet);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public  class MyViewHolder extends  RecyclerView.ViewHolder{
    ImageView imagechitiet;
    TextView txtten, txtsoluong;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imagechitiet=itemView.findViewById(R.id.item_imgchitiet);
            txtten=itemView.findViewById(R.id.item_tenspchitiet);
            txtsoluong=itemView.findViewById(R.id.item_soluongchitiet);
        }
    }
}
