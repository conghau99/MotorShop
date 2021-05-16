package com.example.motorshop.datasrc;

public class ThongSoPhuTung {
    private String tenXe;
    private int donGia;

    public ThongSoPhuTung() { }

    public ThongSoPhuTung(String tenXe, int donGia) {
        this.tenXe = tenXe;
        this.donGia = donGia;
    }

    public String getTenXe() {
        return tenXe;
    }

    public void setTenXe(String tenXe) {
        this.tenXe = tenXe;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

}
