package com.manager.appfood.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.manager.appfood.R;
import com.manager.appfood.databinding.ActivityThemspBinding;
import com.manager.appfood.model.SanPhamMoi;
import com.manager.appfood.retrofit.ApiBanHang;
import com.manager.appfood.retrofit.RetrofitClient;
import com.manager.appfood.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ThemSPActivity extends AppCompatActivity {
    Spinner spinner;
    int loai=0;
    ActivityThemspBinding binding;
    ApiBanHang apiBanHang;
    CompositeDisposable compositeDisposable=new CompositeDisposable();
    SanPhamMoi sanPhamSua;
    boolean flag=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding=ActivityThemspBinding.inflate(getLayoutInflater());
        apiBanHang= RetrofitClient.getInstance(Utils.BASE_URL).create(ApiBanHang.class);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initView();
        initData();
        Intent intent=getIntent();
        sanPhamSua= (SanPhamMoi) intent.getSerializableExtra("sua");
        if(sanPhamSua==null){
            //them  moi
            flag=false;
        }else{
            flag=true;
            binding.btnthem.setText("Sau san pham");
            //show data
            binding.mota.setText(sanPhamSua.getMota());
            binding.giasp.setText(sanPhamSua.getGiasp()+"");
            binding.tensp.setText(sanPhamSua.getTensp());
            binding.hinhanh.setText(sanPhamSua.getHinhanh());
            binding.spinnerLoai.setSelection(sanPhamSua.getLoai());
        }


    }

    private void initData() {
        List<String> stringList=new ArrayList<>();
        stringList.add("Vui lòng chọn loại ");
        stringList.add("loai1");
        stringList.add("loai2");
        ArrayAdapter<String>adapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, stringList);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                loai=i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag==false){
                    themsanpham();
                }else{
                    suaSanPham();
                }

            }
        });
        binding.imgcamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // ImagePicker.with(this)
                   //     .crop()	    			//Crop image(Optional), Check Customization for more option
                   //     .compress(1024)			//Final image size will be less than 1 MB(Optional)
                    //    .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                    //    .start();
            }
        });


    }

    private void suaSanPham() {
        String str_ten=binding.tensp.getText().toString().trim();
        String str_gia=binding.giasp.getText().toString().trim();
        String str_mota=binding.mota.getText().toString().trim();
        String str_hinhanh=binding.hinhanh.getText().toString().trim();
        if(TextUtils.isEmpty(str_ten)||TextUtils.isEmpty(str_gia)||TextUtils.isEmpty(str_mota)|| TextUtils.isEmpty(str_hinhanh)||loai==0){
            Toast.makeText(getApplicationContext(), "Vui lòng nhập dủ thông tin", Toast.LENGTH_SHORT).show();
        }else{
            compositeDisposable.add(apiBanHang.updatesp(str_ten,str_gia,str_hinhanh, str_mota,loai,sanPhamSua.getId())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            messageModel->{
                                if(messageModel.isSuccess()){
                                    Toast.makeText(getApplicationContext(), messageModel.getMessage(), Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(getApplicationContext(), messageModel.getMessage(), Toast.LENGTH_SHORT).show();
                                }

                            },
                            throwable -> {
                                Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                    ));
        }
    }

    private void themsanpham() {
        String str_ten=binding.tensp.getText().toString().trim();
        String str_gia=binding.giasp.getText().toString().trim();
        String str_mota=binding.mota.getText().toString().trim();
        String str_hinhanh=binding.hinhanh.getText().toString().trim();
        if(TextUtils.isEmpty(str_ten)||TextUtils.isEmpty(str_gia)||TextUtils.isEmpty(str_mota)|| TextUtils.isEmpty(str_hinhanh)||loai==0){
            Toast.makeText(getApplicationContext(), "Vui lòng nhập dủ thông tin", Toast.LENGTH_SHORT).show();
        }else{
            compositeDisposable.add(apiBanHang.insertSp(str_ten,str_gia,str_hinhanh, str_mota,(loai))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            messageModel->{
                                if(messageModel.isSuccess()){
                                    Toast.makeText(getApplicationContext(), messageModel.getMessage(), Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(getApplicationContext(), messageModel.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            },
                            throwable -> {
                                Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                    ));
        }
    }

    private void initView() {
        spinner=findViewById(R.id.spinner_loai);
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}