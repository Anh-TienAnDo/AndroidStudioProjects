package com.example.kiemtra1.model;

public class CauThu {
    private String name, ngaysinh, gt, vt;

    public CauThu() {
    }

    public CauThu(String name, String ngaysinh, String gt, String vt) {
        this.name = name;
        this.ngaysinh = ngaysinh;
        this.gt = gt;
        this.vt = vt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getGt() {
        return gt;
    }

    public void setGt(String gt) {
        this.gt = gt;
    }

    public String getVt() {
        return vt;
    }

    public void setVt(String vt) {
        this.vt = vt;
    }
}
