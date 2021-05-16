package com.example.motorshop.datasrc;

public class ThongSoXe {
    private String tenTS;
    private String noiDungTS;

    public ThongSoXe() { }

    public ThongSoXe(String tenTS, String noiDungTS) {
        this.tenTS = tenTS;
        this.noiDungTS = noiDungTS;
    }

    public String getTenTS() {
        return tenTS;
    }

    public void setTenTS(String tenTS) {
        this.tenTS = tenTS;
    }

    public String getNoiDungTS() {
        return noiDungTS;
    }

    public void setNoiDungTS(String noiDungTS) {
        this.noiDungTS = noiDungTS;
    }
}
