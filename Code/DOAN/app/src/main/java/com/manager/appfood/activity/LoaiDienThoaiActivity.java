package com.manager.appfood.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.manager.appfood.R;
import com.manager.appfood.adapter.DienThoaiAdapter;
import com.manager.appfood.model.SanPhamMoi;
import com.manager.appfood.retrofit.ApiBanHang;
import com.manager.appfood.retrofit.RetrofitClient;
import com.manager.appfood.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LoaiDienThoaiActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    ApiBanHang apiBanHang;
    CompositeDisposable compositeDisposable=new CompositeDisposable();
    int page=1;
    int loai;
    DienThoaiAdapter adapterdt;
    List<SanPhamMoi> sanPhamMoiList;
    LinearLayoutManager linearLayoutManager;
    Handler handler=new Handler();
    boolean isLoading=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_loai_dien_thoai);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        apiBanHang = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiBanHang.class);
        loai = getIntent().getIntExtra("loai", 1);

        anhxa();
        ActionToolbar();
        getdata();
        addEventLoad();

    }
    private void addEventLoad() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(isLoading==false){
                    if(linearLayoutManager.findFirstCompletelyVisibleItemPosition()==sanPhamMoiList.size()-1){
                        isLoading=true;
                        loadMore();
                    }
                }
            }
        });
    }

    private void loadMore() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                sanPhamMoiList.add(null);
                adapterdt.notifyItemInserted(sanPhamMoiList.size()-1);
            }
        });
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                sanPhamMoiList.remove(sanPhamMoiList.size()-1);
                adapterdt.notifyItemRemoved(sanPhamMoiList.size());
                page=page+1;
                adapterdt.notifyDataSetChanged();
                isLoading=false;
            }
        }, 2000);
    }

    private void getdata() {
        compositeDisposable.add(apiBanHang.getsanpham(page, loai)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) // coi xài đúng thư viện chưa
                .subscribe( // nó chạy đến đây nó chết // die
                        sanPhamMoiModel -> {
                            if (sanPhamMoiModel.isSuccess()) {
                                sanPhamMoiList=sanPhamMoiModel.getResult();
                                adapterdt=new DienThoaiAdapter(getApplicationContext(),sanPhamMoiList);
                                recyclerView.setAdapter(adapterdt);

                            }

                        },
                        throwable -> {
                            Toast.makeText(getApplicationContext(), "Khong ket noi sever", Toast.LENGTH_SHORT).show();
                        }
                ));
    }


    private void ActionToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



    private void anhxa() {
        toolbar=findViewById(R.id.toolbar);
        recyclerView=findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        linearLayoutManager=new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        sanPhamMoiList=new ArrayList<>();
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

}