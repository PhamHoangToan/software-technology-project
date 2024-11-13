package com.manager.appfood.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.manager.appfood.Interface.IImageClickListenner;
import com.manager.appfood.R;
import com.manager.appfood.model.EventBus.TinhTongEvent;
import com.manager.appfood.model.giohang;
import com.manager.appfood.utils.Utils;

import org.greenrobot.eventbus.EventBus;

import java.text.DecimalFormat;
import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.MyViewHoder> {
    Context context;
    List<giohang> gioHangList;

    public GioHangAdapter(Context context, List<giohang> gioHangList) {
        this.context = context;
        this.gioHangList = gioHangList;
    }

    @androidx.annotation.NonNull
    @Override
    public MyViewHoder onCreateViewHolder(@androidx.annotation.NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_giohang,parent,false);

        return new MyViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@androidx.annotation.NonNull MyViewHoder holder, int position) {
    giohang gioHang=gioHangList.get(position);
    holder.item_giohang_tensp.setText(gioHang.getTensp());
    holder.item_giohang_soluong.setText(gioHang.getSoluong()+" ");
    Glide.with(context).load(gioHang.getHinhsp()).into(holder.item_giohang_image);
    DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
    holder.item_giohang_gia.setText(decimalFormat.format((gioHang.getGiasp()))+"D");
    long gia=gioHang.getSoluong()*gioHang.getGiasp();
    holder.getItem_giohang_tensp2.setText(decimalFormat.format(gia));
    holder.setListenner(new IImageClickListenner() {
        @Override
        public void onImageClick(View view, int pos, int giaitri) {
            Log.d("TAG", "onImageClick: "+pos+"..."+giaitri);
            if(giaitri==1){
                if(gioHangList.get(pos).getSoluong()>1){
                    int soluongmoi=gioHangList.get(pos).getSoluong()-1;
                    gioHangList.get(pos).setSoluong(soluongmoi);

                    holder.item_giohang_soluong.setText(gioHangList.get(pos).getSoluong()+"");
                    long gia=gioHangList.get(pos).getSoluong()*gioHangList.get(pos).getGiasp();
                    holder.getItem_giohang_tensp2.setText(decimalFormat.format(gia));
                    EventBus.getDefault().postSticky(new TinhTongEvent());
                }else if(gioHangList.get(pos).getSoluong()==1){
                    AlertDialog.Builder builder=new AlertDialog.Builder(view.getRootView().getContext());
                    builder.setTitle("Thông báo");
                    builder.setMessage("Bạn có muốn xóa sản phẩm này khỏi giỏi hàng không");
                    builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Utils.manggiohang.remove(pos);
                            notifyDataSetChanged();
                        }
                    });
                    builder.setNegativeButton("hủy", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                }
            }else if(giaitri==2){
                if(gioHangList.get(pos).getSoluong()<11){
                    int soluongmoi=gioHangList.get(pos).getSoluong()+1;
                    gioHangList.get(pos).setSoluong(soluongmoi);
                }
                holder.item_giohang_soluong.setText(gioHangList.get(pos).getSoluong()+"");
                long gia=gioHangList.get(pos).getSoluong()*gioHangList.get(pos).getGiasp();
                holder.getItem_giohang_tensp2.setText(decimalFormat.format(gia));
                EventBus.getDefault().postSticky(new TinhTongEvent());
            }

        }
    });
    }

    @Override
    public int getItemCount() {
        return gioHangList.size();
    }


    public class MyViewHoder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView item_giohang_image, imgtru, imgcong;
        TextView item_giohang_tensp, item_giohang_gia, item_giohang_soluong, getItem_giohang_tensp2;
        IImageClickListenner listenner;
        public  MyViewHoder(@NonNull View itemView){
            super(itemView);
                item_giohang_image=itemView.findViewById(R.id.item_giohang_image);
                item_giohang_tensp=itemView.findViewById(R.id.item_giohang_tensp);
                item_giohang_gia=itemView.findViewById(R.id.item_giohang_gia);
                item_giohang_soluong=itemView.findViewById(R.id.item_giohang_soluong);
                getItem_giohang_tensp2=itemView.findViewById(R.id.item_giohang_giasp2);
                imgtru=itemView.findViewById(R.id.item_giohang_tru);
                imgcong=itemView.findViewById(R.id.item_giohang_cong);

                imgcong.setOnClickListener(this);
                imgtru.setOnClickListener(this);
        }
        public  void setListenner(IImageClickListenner listenner){
            this.listenner=listenner;
        }

        @Override
        public void onClick(View view) {
            if(view==imgtru){
                listenner.onImageClick(view,getAdapterPosition(),1);
            }else{
                listenner.onImageClick(view,getAdapterPosition(), 2);
            }

        }
    }
}
