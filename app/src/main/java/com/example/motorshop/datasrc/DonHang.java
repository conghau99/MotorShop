package com.example.motorshop.datasrc;

public class DonHang {
    private String maDH;
    private String ngayDat;
    private String cmnd;
    private String maNV;

    public DonHang() { }

    public DonHang(String maDH, String ngayDat, String cmnd, String maNV) {
        this.maDH = maDH;
        this.ngayDat = ngayDat;
        this.cmnd = cmnd;
        this.maNV = maNV;
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

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }
}
