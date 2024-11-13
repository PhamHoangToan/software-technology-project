package com.manager.appfood.retrofit;

import com.manager.appfood.model.DonHangModel;
import com.manager.appfood.model.MessagerModel;
import com.manager.appfood.model.SanPhamMoiModel;
import com.manager.appfood.model.UserModel;
import com.manager.appfood.model.loaiSpModel;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiBanHang {
    @GET("getloaisp.php")
    Observable<loaiSpModel> getLoaiSp();

    @GET("getspmoi.php")
    Observable<SanPhamMoiModel>getSpMoi();

    @POST ("chitiet.php")
    @FormUrlEncoded
    Observable<SanPhamMoiModel>getsanpham(
            @Field("page") int page,
            @Field("loai") int loai
    );
    @POST ("dangky.php")
    @FormUrlEncoded
    Observable<UserModel>dangky(
            @Field("email") String email,
            @Field("pass") String pass,
            @Field("username") String username,
            @Field("mobile") String mobile
    );

    @POST ("dangnhap.php")
    @FormUrlEncoded
    Observable<UserModel>dangnhap(
            @Field("email") String email,
            @Field("pass") String pass
    );

    @POST ("donhang.php")
    @FormUrlEncoded
    Observable<UserModel>createOder(
            @Field("email") String email,
            @Field("sdt") String sdt,
            @Field("tongtien") String tongtien,
            @Field("iduser") int id,
            @Field("diachi") String diachi,
            @Field("soluong") int soluong,
            @Field("chitiet") String chitiet

    );
    @POST ("xemdonhang.php")
    @FormUrlEncoded
    Observable<DonHangModel>xemDonHang(
            @Field("iduser") int id

    );

    @POST ("timkiem.php")
    @FormUrlEncoded
    Observable<SanPhamMoiModel>search(
            @Field("search") String search

    );
    @POST ("xoa.php")
    @FormUrlEncoded
    Observable<MessagerModel>xoaSanPham(
            @Field("id") int id

    );

    @POST ("insertsp.php")
    @FormUrlEncoded
    Observable<UserModel>insertSp(
            @Field("tensp") String tensp,
            @Field("gia") String gia,
            @Field("hinhanh") String hinhanh,
            @Field("mota") String mota,
            @Field("loai") int id
    );

    @POST ("updatesp.php")
    @FormUrlEncoded
    Observable<UserModel>updatesp(
            @Field("tensp") String tensp,
            @Field("gia") String gia,
            @Field("hinhanh") String hinhanh,
            @Field("mota") String mota,
            @Field("loai") int idloai,
            @Field("id") int id
    );
}
