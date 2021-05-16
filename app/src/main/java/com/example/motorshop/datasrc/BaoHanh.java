package com.example.motorshop.datasrc;

public class BaoHanh {
    private String maBH;
    private String maDH;
    private String ngayBH;
    private String maNV;

    public BaoHanh() { }

    public BaoHanh(String maBH, String maDH, String ngayBH, String maNV) {
        this.maBH = maBH;
        this.maDH = maDH;
        this.ngayBH = ngayBH;
        this.maNV = maNV;
    }

    public String getMaBH() {
        return maBH;
    }

    public void setMaBH(String maBH) {
        this.maBH = maBH;
    }

    public String getMaDH() {
        return maDH;
    }

    public void setMaDH(String maDH) {
        this.maDH = maDH;
    }

    public String getNgayBH() {
        return ngayBH;
    }

    public void setNgayBH(String ngayBH) {
        this.ngayBH = ngayBH;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }
}
