package com.manager.appfood.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.manager.appfood.R;
import com.manager.appfood.adapter.SanPhamMoiAdapter;
import com.manager.appfood.adapter.loaiSpAdapter;
import com.manager.appfood.model.SanPhamMoi;
import com.manager.appfood.model.User;
import com.manager.appfood.model.loaiSp;
import com.manager.appfood.retrofit.ApiBanHang;
import com.manager.appfood.retrofit.RetrofitClient;
import com.manager.appfood.utils.Utils;
import com.google.android.material.navigation.NavigationView;
import com.nex3z.notificationbadge.NotificationBadge;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;




public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerViewmanhinhchinh;
    NavigationView navigationView;
    ListView listView;
    RecyclerView recyclerView;
    DrawerLayout drawerLayout;
    loaiSpAdapter loaiSpAdapter;
    List<loaiSp>mangloaisp;
    CompositeDisposable compositeDisposable=new CompositeDisposable();
    ApiBanHang apiBanHang;
    List<SanPhamMoi> mangSpMoi;
    SanPhamMoiAdapter spAdapter;
    NotificationBadge badge;
    FrameLayout frameLayout;
    ImageView imgsearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        apiBanHang = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiBanHang.class);
        Paper.init(this);
        if(Paper.book().read("user")!=null){
            User user=Paper.book().read("user");
            Utils.user_current=user;
        }
        Anhxa();
        ActionBar();
        ActionViewFlipper();
        if(isConnected(this)){

            ActionViewFlipper();
            getLoaiSanPham();
            getSpMoi();
            getEventClick();
        }else{
            Toast.makeText(getApplicationContext(),"khong co internet", Toast.LENGTH_LONG).show();
        }
    }

    private void getEventClick() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                switch (i){
                    case 0:
                        Intent trangchu=new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(trangchu);
                        break;
                    case 1:
                        Intent dienthoai=new Intent(getApplicationContext(), LoaiDienThoaiActivity.class);
                        dienthoai.putExtra("loai", 1);
                        startActivity(dienthoai);
                        break;
                    case 2:
                        Intent laptop=new Intent(getApplicationContext(), LapTopActivity.class);
                        startActivity(laptop);
                        break;
                    case 5:
                        Intent Donhang=new Intent(getApplicationContext(), XemDonActivity.class);
                        startActivity(Donhang);
                        break;
                    case 6:
                     Intent quanly=new Intent(getApplicationContext(), QuanLyActivity.class);
                     startActivity(quanly);
                     finish();
                        break;
                    case 7:
                        Paper.book().delete("user");
                        Intent dangnhap=new Intent(getApplicationContext(), DangNhapActivity.class);
                        startActivity(dangnhap);
                        finish();
                        break;

                }
            }
        });
    }

    private void getSpMoi() {
        compositeDisposable.add(apiBanHang.getSpMoi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    sanPhamMoiModel -> {
                        if(sanPhamMoiModel.isSuccess()){
                            mangSpMoi=sanPhamMoiModel.getResult();
                            spAdapter=new SanPhamMoiAdapter(getApplicationContext(), mangSpMoi);
                            recyclerViewmanhinhchinh.setAdapter(spAdapter);
                        }
                    },
                        throwable -> {
                            Toast.makeText(getApplicationContext(), "Khong co internet", Toast.LENGTH_SHORT).show();
                        }
                ));
    }

    private void getLoaiSanPham() {
        compositeDisposable.add(apiBanHang.getLoaiSp()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        loaiSpModel->{
                            if (loaiSpModel.isSuccess()) {
                                 mangloaisp=loaiSpModel.getResult();
                                mangloaisp.add(new loaiSp("Quản lý", ""));
                                 mangloaisp.add(new loaiSp("Đăng Xuất", ""));
                                loaiSpAdapter=new loaiSpAdapter(getApplicationContext(),mangloaisp);
                                listView.setAdapter(loaiSpAdapter);
                            }
                        }
                )
        );
    }

    private void ActionViewFlipper() {
        List<String> mangquangcao=new ArrayList<>();
        mangquangcao.add("https://mauweb.monamedia.net/thegioididong/wp-content/uploads/2017/12/banner-Le-hoi-phu-kien-800-300.png");
        mangquangcao.add("https://mauweb.monamedia.net/thegioididong/wp-content/uploads/2017/12/banner-HC-Tra-Gop-800-300.png");
        mangquangcao.add("https://mauweb.monamedia.net/thegioididong/wp-content/uploads/2017/12/banner-big-ky-nguyen-800-300.jpg");
        for(int i=0;i<mangquangcao.size();i++){
            ImageView imageView=new ImageView(getApplicationContext());
            Glide.with(getApplicationContext()).load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        Animation slide_in= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation slide_out= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        viewFlipper.setInAnimation(slide_in);
        viewFlipper.setInAnimation(slide_out);
    }

    private void ActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void Anhxa() {
        imgsearch=findViewById(R.id.imgsearch);
        toolbar=findViewById(R.id.toobarmanhinhchinh);
        viewFlipper=findViewById(R.id.viewlipper);
        recyclerViewmanhinhchinh=findViewById(R.id.Recycleview);
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(this, 2);
        recyclerViewmanhinhchinh.setLayoutManager(layoutManager);
        recyclerViewmanhinhchinh.setHasFixedSize(true);

        listView=findViewById(R.id.listviewmanhinhchinh);
        navigationView=findViewById(R.id.navigationview);
        drawerLayout=findViewById(R.id.drawerlayout);
        badge=findViewById(R.id.menu_sl);
        frameLayout=findViewById(R.id.fragmengiohang);
        //khoi tao list
        mangloaisp=new ArrayList<>();
        mangSpMoi=new ArrayList<>();
        if(Utils.manggiohang==null){
            Utils.manggiohang=new ArrayList<>();
        }else{
            int totalItem=0;
            for(int i=0;i<Utils.manggiohang.size();i++){
                totalItem=totalItem+Utils.manggiohang.get(i).getSoluong();
            }
            badge.setText(String.valueOf(totalItem));
        }
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent giohang=new Intent(getApplicationContext(), GioHangActivity.class);
                startActivity(giohang);
            }
        });
        imgsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),SearchActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        int totalItem=0;
        for(int i=0;i<Utils.manggiohang.size();i++){
            totalItem=totalItem+Utils.manggiohang.get(i).getSoluong();
        }
        badge.setText(String.valueOf(totalItem));
    }

    private boolean isConnected(Context context){
        ConnectivityManager connectivityManager= (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if((wifi!=null && wifi.isConnected())|| (mobile!=null && mobile.isConnected())){
            return  true;
        }else{
            return false;
        }
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}