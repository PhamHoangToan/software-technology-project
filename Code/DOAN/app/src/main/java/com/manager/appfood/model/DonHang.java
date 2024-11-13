package com.manager.appfood.model;

import java.util.List;

public class DonHang {
    int id;
    int iduser;
    String iachi;
    String sodienthoai;
    String tongtien;
    List<item>item;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getIachi() {
        return iachi;
    }

    public void setIachi(String iachi) {
        this.iachi = iachi;
    }

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public String getTongtien() {
        return tongtien;
    }

    public void setTongtien(String tongtien) {
        this.tongtien = tongtien;
    }

    public List<com.manager.appfood.model.item> getItem() {
        return item;
    }

    public void setItem(List<com.manager.appfood.model.item> item) {
        this.item = item;
    }
}
