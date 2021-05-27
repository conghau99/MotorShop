package com.example.motorshop.datasrc;

public class ThongSoPhuTung {
    private String maPT;
    private String maXe;
    private int donGia;

    public ThongSoPhuTung() { }

    public ThongSoPhuTung(String maPT, String maXe, int donGia) {
        this.maPT = maPT;
        this.maXe = maXe;
        this.donGia = donGia;
    }

    public String getMaPT() {
        return maPT;
    }

    public void setMaPT(String maPT) {
        this.maPT = maPT;
    }

    public String getMaXe() {
        return maXe;
    }

    public void setMaXe(String maXe) {
        this.maXe = maXe;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

}
