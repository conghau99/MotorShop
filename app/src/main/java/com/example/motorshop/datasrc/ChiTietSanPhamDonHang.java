package com.example.motorshop.datasrc;

public class ChiTietSanPhamDonHang extends DonHang {
    private String tenSP;
    private int soLuong;
    private int donGiaBan;

    public ChiTietSanPhamDonHang() {
        super();
    }

    public ChiTietSanPhamDonHang(String maDH, String ngayDat, String sdtKH, String tenNV, String tenSP, int soLuong, int donGiaBan) {
        super(maDH, ngayDat, sdtKH, tenNV);
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.donGiaBan = donGiaBan;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getDonGiaBan() {
        return donGiaBan;
    }

    public void setDonGiaBan(int donGiaBan) {
        this.donGiaBan = donGiaBan;
    }
}