package com.example.motorshop.datasrc;

public class ThongSoXe {
    private int maTS;
    private String tenTS;

    public ThongSoXe() { }

    public ThongSoXe(int maTS, String tenTS) {
        this.maTS = maTS;
        this.tenTS = tenTS;
    }

    public int getMaTS() {
        return maTS;
    }

    public void setMaTS(int maTS) {
        this.maTS = maTS;
    }

    public String getTenTS() {
        return tenTS;
    }

    public void setTenTS(String tenTS) {
        this.tenTS = tenTS;
    }
}
