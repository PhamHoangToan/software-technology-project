package com.manager.appfood.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.manager.appfood.Interface.ItemClickListenener;
import com.manager.appfood.R;
import com.manager.appfood.activity.ChiTietActivity;
import com.manager.appfood.model.EventBus.SuaXoaEvent;
import com.manager.appfood.model.SanPhamMoi;

import org.greenrobot.eventbus.EventBus;

import java.text.DecimalFormat;
import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;

public class SanPhamMoiAdapter extends RecyclerView.Adapter<SanPhamMoiAdapter.MyViewHolder> {
    Context context;
    List<SanPhamMoi> array;

    public SanPhamMoiAdapter(Context context, List<SanPhamMoi> array) {
        this.context = context;
        this.array = array;
    }

    @androidx.annotation.NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@androidx.annotation.NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sp_moi, parent, false);
        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@androidx.annotation.NonNull MyViewHolder holder, int position) {
        if (array == null || position < 0 || position >= array.size()) {
            return;
        }
        SanPhamMoi sanPhamMoi = array.get(position);
        if (sanPhamMoi == null) {
            return;
        }
        holder.txtten.setText(sanPhamMoi.getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,### ");
        //holder.txtgia.setText("Giá: " + decimalFormat.format(Double.parseDouble(sanPhamMoi.getGiasp())) + "Đ");
        if (context != null) {
            Glide.with(context).load(sanPhamMoi.getHinhanh()).into(holder.imghinhanh);
        }

        holder.setItemClickListenener(new ItemClickListenener() {
            @Override
            public void onClick(View view, int pos, boolean isLongClick) {
                if(!isLongClick){
                    Intent intent  =new Intent(context, ChiTietActivity.class);
                    intent.putExtra("chitiet",sanPhamMoi);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }else{
                    EventBus.getDefault().postSticky(new SuaXoaEvent(sanPhamMoi));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return array != null ? array.size() : 0;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener, View.OnLongClickListener {
        TextView txtgia, txtten;
        ImageView imghinhanh;
        private ItemClickListenener itemClickListenener;
    public  MyViewHolder(@NonNull View itemView){
        super(itemView);
        txtgia=itemView.findViewById(R.id.itemsp_gia);
        txtten=itemView.findViewById(R.id.iemsp_ten);
        imghinhanh=itemView.findViewById(R.id.itemsp_image);
        itemView.setOnClickListener(this);
        itemView.setOnCreateContextMenuListener(this);
        itemView.setOnLongClickListener(this);
    }

        public void setItemClickListenener(ItemClickListenener itemClickListenener) {
            this.itemClickListenener = itemClickListenener;
        }

        @Override
        public void onClick(View view) {
            itemClickListenener.onClick(view, getAdapterPosition(), false);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(0,0,getAdapterPosition(), "Sửa");
            menu.add(0,1,getAdapterPosition(), "Xóa");
        }

        @Override
        public boolean onLongClick(View view) {
            itemClickListenener.onClick(view, getAdapterPosition(), true);
            return false;
        }
    }
}
