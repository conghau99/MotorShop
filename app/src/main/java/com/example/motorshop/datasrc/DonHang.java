package com.example.motorshop.datasrc;

public class DonHang {
    private String maDH;
    private String ngayDat;
    private String cmnd;
    private String tenNV;

    public DonHang() { }

    public DonHang(String maDH, String ngayDat, String cmnd, String tenNV) {
        this.maDH = maDH;
        this.ngayDat = ngayDat;
        this.cmnd = cmnd;
        this.tenNV = tenNV;
    }

    public String getMaDH() {
        return maDH;
    }

    public void setMaDH(String maDH) {
        this.maDH = maDH;
    }

    public String getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(String ngayDat) {
        this.ngayDat = ngayDat;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }
}
