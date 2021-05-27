package com.example.motorshop.datasrc;

public class DanhSachSanPhamBaoHanh {                   //Thon tin bao hanh cua 1 Don Dat Hang bat ky
    private String maSP;
    private String noiDungBH;
    private int phiBH;

    public DanhSachSanPhamBaoHanh() { }

    public DanhSachSanPhamBaoHanh(String maSP, String noiDungBH, int phiBH) {
        this.maSP = maSP;
        this.noiDungBH = noiDungBH;
        this.phiBH = phiBH;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getNoiDungBH() {
        return noiDungBH;
    }

    public void setNoiDungBH(String noiDungBH) {
        this.noiDungBH = noiDungBH;
    }

    public int getPhiBH() {
        return phiBH;
    }

    public void setPhiBH(int phiBH) {
        this.phiBH = phiBH;
    }
}