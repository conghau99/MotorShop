package com.example.motorshop.datasrc;

public class NhanVien {
    private String maNV;
    private String hoTen;
    private String sdt;
    private String tenBP;

    public NhanVien() { }

    public NhanVien(String maNV, String hoTen, String sdt, String maBP) {
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.sdt = sdt;
        this.tenBP = maBP;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getTenBP() {
        return tenBP;
    }

    public void setTenBP(String tenBP) {
        this.tenBP = tenBP;
    }
}
