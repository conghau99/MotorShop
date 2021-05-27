package com.example.motorshop.datasrc;

public class ChiTietThongSoXe {
    private String maXe;
    private int maTS;
    private String noiDungTS;

    public ChiTietThongSoXe() { }

    public ChiTietThongSoXe(String maXe, int maTS, String noiDungTS) {
        this.maXe = maXe;
        this.maTS = maTS;
        this.noiDungTS = noiDungTS;
    }

    public String getMaXe() {
        return maXe;
    }

    public void setMaXe(String maXe) {
        this.maXe = maXe;
    }

    public int getMaTS() {
        return maTS;
    }

    public void setMaTS(int maTS) {
        this.maTS = maTS;
    }

    public String getNoiDungTS() {
        return noiDungTS;
    }

    public void setNoiDungTS(String noiDungTS) {
        this.noiDungTS = noiDungTS;
    }
}
