package com.manager.appfood.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.manager.appfood.R;
import com.manager.appfood.retrofit.ApiBanHang;
import com.manager.appfood.retrofit.RetrofitClient;
import com.manager.appfood.utils.Utils;

import io.paperdb.Paper;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DangNhapActivity extends AppCompatActivity {
    TextView txtdangky, txtresetpass;
    EditText email, pass;
    AppCompatButton btndangnhap;
    ApiBanHang apiBanHang;
    CompositeDisposable compositeDisposable=new CompositeDisposable();
    boolean isLogin=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        initView();
        initControll();
    }

    private void initControll() {
        txtdangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), DangKyActivity.class);
                startActivity(intent);
            }
        });

        txtresetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), ResetPassActivity.class);
                startActivity(intent);
            }
        });
        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_email=email.getText().toString().trim();
                String str_pass=pass.getText().toString().trim();
                if(TextUtils.isEmpty(str_email)){
                    Toast.makeText(getApplicationContext(), "Bạn chưa nhập email",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(str_pass)){
                    Toast.makeText(getApplicationContext(), "Bạn chưa nhập pass ",Toast.LENGTH_SHORT).show();
                }else{
                    Paper.book().write("email", str_email);
                    Paper.book().write("pass", str_pass);
                    compositeDisposable.add(apiBanHang.dangnhap(str_email,str_pass)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    userModel -> {
                                        if(userModel.isSuccess()){
                                            Utils.user_current=userModel.getResult().get(0);
                                            Paper.book().write("user", userModel.getResult().get(0));
                                            Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    },
                                    throwable -> {
                                        Toast.makeText(getApplicationContext(), throwable.getMessage(),Toast.LENGTH_SHORT).show();
                                    }
                            ));
                }
            }
        });
    }

    private void initView() {
        Paper.init(this);
        apiBanHang= RetrofitClient.getInstance(Utils.BASE_URL).create(ApiBanHang.class);
        txtdangky=findViewById(R.id.txtdangky);
        txtresetpass=findViewById(R.id.txtresetpass);
        email=findViewById(R.id.email);
        pass=findViewById(R.id.pass);
        btndangnhap=findViewById(R.id.btndangnhap);

        //


        if(Paper.book().read("email")!=null && Paper.book().read("pass")!=null ){
            email.setText(Paper.book().read("email"));
            pass.setText(Paper.book().read("pass"));

        }
    }



    @Override
    protected void onResume() {
        super.onResume();
        if(Utils.user_current.getEmail()!=null && Utils.user_current.getPass() !=null){
            pass.setText(Utils.user_current.getEmail());
        }
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}