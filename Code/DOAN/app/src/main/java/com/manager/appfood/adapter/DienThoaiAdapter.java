package com.manager.appfood.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.manager.appfood.Interface.ItemClickListenener;
import com.manager.appfood.R;
import com.manager.appfood.activity.ChiTietActivity;
import com.manager.appfood.model.SanPhamMoi;

import java.text.DecimalFormat;
import java.util.List;

public class DienThoaiAdapter extends RecyclerView.Adapter<DienThoaiAdapter.MyViewHolder> {
    Context context;
    List<SanPhamMoi> array;

    public DienThoaiAdapter(Context context, List<SanPhamMoi> array) {
        this.context = context;
        this.array = array;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dienthoai, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MyViewHolder myViewHolder=( MyViewHolder)holder;
        SanPhamMoi sanPham=array.get(position);
        holder.tensp.setText(sanPham.getTensp());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
       // holder.giasp.setText("Gia: " + decimalFormat.format(sanPham.getGiasp()) + "D");
        holder.mota.setText(sanPham.getMota());
        Glide.with(context).load(sanPham.getHinhanh()).into(holder.hinhanh);
        myViewHolder.setItemClickListenener(new ItemClickListenener() {
            @Override
            public void onClick(View view, int pos, boolean isLongClick) {
                if(!isLongClick){
                    Intent intent=new Intent(context, ChiTietActivity.class);
                    intent.putExtra("chitiet",sanPham);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return array.size();
    }
    public class LoadingViewHolder extends RecyclerView.ViewHolder{
        ProgressBar progressbar;
        public LoadingViewHolder(@androidx.annotation.NonNull View itemView) {
            super(itemView);
            progressbar=itemView.findViewById(R.id.progressbar);
        }
    }

    public  class  MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView tensp, giasp, mota;
    ImageView hinhanh;
    private ItemClickListenener itemClickListenener;
       public MyViewHolder(@NonNull View itemView) {
           super(itemView);
           tensp=itemView.findViewById(R.id.itemdt_ten);
           giasp=itemView.findViewById(R.id.itemdt_gia);
           mota=itemView.findViewById(R.id.itemdt_mota);
           hinhanh=itemView.findViewById(R.id.itemdt_image);
           itemView.setOnClickListener(this);
       }

        public void setItemClickListenener(ItemClickListenener itemClickListenener) {
            this.itemClickListenener = itemClickListenener;
        }

        @Override
        public void onClick(View v) {
            itemClickListenener.onClick(v,getAdapterPosition(),false);
        }
    }
}
