package com.example.motorshop.datasrc;

public class DanhSachSanPhamBaoHanh {                   //Thon tin bao hanh cua 1 Don Dat Hang bat ky
    private String tenSP;
    private String noiDungBH;
    private int phiBH;

    public DanhSachSanPhamBaoHanh() { }

    public DanhSachSanPhamBaoHanh(String tenSP, String noiDungBH, int phiBH) {
        this.tenSP = tenSP;
        this.noiDungBH = noiDungBH;
        this.phiBH = phiBH;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
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