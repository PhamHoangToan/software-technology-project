package com.manager.appfood.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
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

public class SearchActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    EditText edtsearch;
    ApiBanHang apiBanHang;
    CompositeDisposable compositeDisposable=new CompositeDisposable();
    DienThoaiAdapter adapterdt;
    List<SanPhamMoi> sanPhamMoiList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ActionToolbar();
        initView();
    }

    private void initView() {
        sanPhamMoiList=new ArrayList<>();
        apiBanHang= RetrofitClient.getInstance(Utils.BASE_URL).create(ApiBanHang.class);
        edtsearch=findViewById(R.id.edtsearch);
        toolbar=findViewById(R.id.toolbar);
        recyclerView=findViewById(R.id.recyclerview_search);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        edtsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if(charSequence.length()==0){
                    sanPhamMoiList.clear();
                    adapterdt=new DienThoaiAdapter(getApplicationContext(),sanPhamMoiList);
                    recyclerView.setAdapter(adapterdt);
                }else{
                    getDataSearch(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }


        });
    }
    private void getDataSearch(String s) {
        sanPhamMoiList.clear();
        compositeDisposable.add(apiBanHang.search(s)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) // coi xài đúng thư viện chưa
                .subscribe( // nó chạy đến đây nó chết // die
                        SanPhamMoiModel->{
                            if(SanPhamMoiModel.isSuccess()){
                                sanPhamMoiList=SanPhamMoiModel.getResult();
                                adapterdt=new DienThoaiAdapter(getApplicationContext(),sanPhamMoiList);
                                recyclerView.setAdapter(adapterdt);
                            }
                        },
                        throwable -> {
                            Toast.makeText(getApplicationContext(), throwable.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                ));
    }
    private void ActionToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

}