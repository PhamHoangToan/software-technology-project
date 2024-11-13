package com.manager.appfood.model;

import java.util.List;

public class loaiSpModel {
    boolean success;
    String message;
    List<loaiSp> result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<loaiSp> getResult() {
        return result;
    }

    public void setResult(List<loaiSp> result) {
        this.result = result;
    }
}
